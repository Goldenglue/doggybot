package twitch.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

public class Twitch {
    private static String clientId;
    private static String token;
    private static final OkHttpClient okHttp = new OkHttpClient();

    static {
        try {
            clientId = Files.readAllLines(Paths.get("client_id.txt")).get(0);
            token = Files.readAllLines(Paths.get("token.txt")).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Duration uptime(String channel) {
        JsonNode root = executeHttpGet("https://api.twitch.tv/kraken/streams/" + getChannelId(channel));

        if (root != null && root.has("stream") && root.get("stream").has("created_at")) {
            Instant stat = Instant.parse(root.get("stream").get("created_at").asText(Instant.now().toString()));
            return Duration.between(stat, Instant.now());
        }

        return Duration.ZERO;
    }

    private static int getChannelId(String channel) {
        JsonNode root = executeHttpGet("https://api.twitch.tv/kraken/users?login=" + channel);
        if (root != null && root.has("_total") && root.get("_total").asInt() == 1) {
            JsonNode users = root.get("users");
            return users.get(0).get("_id").asInt();
        }
        return 0;
    }

    public static JsonNode executeHttpGet(String request) {
        try {
            Request getRequest = new Request.Builder()
                    .url(request)
                    .header("Accept", "application/vnd.twitchtv.v5+json")
                    .header("Client-ID", clientId)
                    .header("Authorization", "OAuth " + token)
                    .get().build();
            Response response = okHttp.newCall(getRequest).execute();

            if (response.code() == 200) {
                return new ObjectMapper().readTree(response.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
