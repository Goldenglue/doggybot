package irc;


//TODO implement input parser in form of <command> :tmi.twitch.tv \s somenumberswtf \s <user> :<message>
//TODO process events

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

        logger.info("Received message --> " + input);
        if (input.contains("PING :tmi.twitch.tv")) {
            configuration.getListenerManager().onEvent(new PingEvent(bot));
        }


        //List<String> splittedLines = split(input);
    }

    private List<String> split(String input) {
        List<String> lines = new ArrayList<>();
        String[] split = input.split("\\s");
        lines.add(split[1]);
        lines.add(split[0]);
        return lines;

    }
}
