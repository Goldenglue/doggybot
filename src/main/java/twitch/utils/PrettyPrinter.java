package twitch.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PrettyPrinter {
    public static void prettyPrintJSonNode(JsonNode node) {
        try {
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(node));
        } catch (JsonProcessingException e) {
            System.out.println("Error printing JsonNode");
            e.printStackTrace();
        }
    }
}
