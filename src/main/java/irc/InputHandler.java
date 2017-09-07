package irc;

import irc.events.events.PingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class InputHandler {
    private final DoggyBot bot;
    private final Logger logger = LogManager.getLogger();
    private final Configuration configuration;

    public InputHandler(DoggyBot bot) {
        this.bot = bot;
        this.configuration = bot.getConfig();
    }

    public void handle(String input) {

        List<String> split = split(input);
        logger.info(input);
        if (split.get(0).equals("PING")) {
            configuration.getListenerManager().onEvent(new PingEvent(bot));
        }
        /*if (input.contains("PRIVMSG")) {
            logger.info(split.get(0));
            logger.info(split.get(1));
            configuration.getListenerManager().onEvent(new MessageEvent(bot));
        }*/
    }

    private List<String> split(String input) {
        List<String> lines = new ArrayList<>();
        String[] split = input.split("\\s", 2);
        lines.add(split[0]);
        lines.add(split[1]);
        return lines;

    }
}
