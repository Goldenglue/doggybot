package twitch.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

public class Twitch {
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static String clientId;
    private static String token;

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
        if (root != null) {
            Instant stat = Instant.parse(root.get("srream").get("created_at").asText(Instant.now().toString()));
            return Duration.between(stat, Instant.now());
        }
        return Duration.ZERO;
    }

    private static int getChannelId(String channel) {
        JsonNode root = executeHttpGet("https://api.twitch.tv/kraken/users?login=" + channel);
        if (root != null && root.get("_total").asInt() == 1) {
            JsonNode users = root.get("users");
            return users.get(0).get("_id").asInt();
        }
        return 0;
    }

    public static JsonNode executeHttpGet(String request) {
        try {
            HttpRequest getRequest = HttpRequest.newBuilder(new URI(request))
                    .header("Accept", "application/vnd.twitchtv.v5+json")
                    .header("Client-ID", clientId)
                    .header("Authorization","OAuth " + token)
                    .GET().build();
            HttpResponse<String> response = httpClient.send(getRequest,HttpResponse.BodyHandler.asString());

            if (response.statusCode() == 200) {
                return new ObjectMapper().readTree(response.body());
            }
        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
