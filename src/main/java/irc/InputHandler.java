package irc;

import irc.events.events.MessageEvent;
import irc.events.events.PingEvent;
import irc.events.events.UsernoticeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputHandler {
    private final DoggyBot bot;
    private final Logger logger = LogManager.getLogger();
    private final Configuration configuration;

    public InputHandler(DoggyBot bot) {
        this.bot = bot;
        this.configuration = bot.getConfig();
    }

    public void handle(String input) {

        List<String> split = splitIntoTagsAndMessage(input);
        logger.info(input);
        //System.out.println(input);
        Map<String, String> tags = parseTags(split.get(0));

        if (split.get(0).equals("PING")) {
            configuration.getListenerManager().onEvent(new PingEvent(bot));
            return;
        }

        if (split.get(1).startsWith(":")) {
            String[] tagsCommand = split.get(1).split(" ", 3);
            String command = tagsCommand[1];
            String rest = tagsCommand[2];
            processCommand(command, rest, tags, split.get(1));
        }
    }

    private void processCommand(String command, String rest, Map<String, String> tags, String potentialUser) {
        if (command.equals("PRIVMSG")) {
            List<String> messageDetails = parseUserInfo(potentialUser);
            configuration.getListenerManager().onEvent(new MessageEvent(bot, new Channel(messageDetails.get(0)), new User(messageDetails.get(1)), messageDetails.get(2), tags));
        } else if (command.equals("CLEARCHAT")) {

        } else if (command.equals("ROOMSTATE")) {

        } else if (command.equals("USERSTATE")) {

        } else if (command.equals("USERNOTICE")) {
            configuration.getListenerManager().onEvent(new UsernoticeEvent(bot));
        }
    }


    private List<String> splitIntoTagsAndMessage(String input) {
        List<String> lines = new ArrayList<>();
        String[] split = input.split("\\s", 2);
        lines.add(split[0]);
        lines.add(split[1]);
        return lines;
    }

    private List<String> parseUserInfo(String userMessage) {
        String[] split = userMessage.substring(1).split(":", 2);
        String message = split[1];

        String username = split[0].split("!")[0].trim();
        String channel = split[0].split("#")[1].trim();

        return Stream.of(channel, username, message).collect(Collectors.toList());
    }

    private Map<String, String> parseTags(String input) {
        Map<String, String> tags = new HashMap<>();

        String[] split = input.split(";");
        Stream.of(split).forEach(string -> {
            String[] tag = string.split("=");
            if (tag.length > 1) {
                tags.put(tag[0], tag[1]);
            } else {
                tags.put(tag[0], "");
            }
        });
        return tags;
    }
}
