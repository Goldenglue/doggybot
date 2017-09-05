package irc;



//TODO implement input parser in form of <command> :tmi.twitch.tv \s somenumberswtf \s <user> :<message>
//TODO process events

import java.util.ArrayList;
import java.util.List;

public class InputHandler {
    private final DoggyBot bot;



    public InputHandler(DoggyBot bot) {
        this.bot = bot;
    }

    public void handle(String input) {
        List<String> splittedLines = split(input);
    }

    private List<String> split(String input) {
        List<String> lines = new ArrayList<>();
        String[] split = input.split("\\s");
        lines.add(split[1]);
        lines.add(split[0]);
        return lines;

    }
}
