package irc.events.events;

import irc.Channel;
import irc.DoggyBot;
import irc.User;
import irc.events.Event;

import java.util.Map;

public class MessageEvent extends Event {
    private String message;
    private Channel channel;
    private User user;
    private Map<String, String> tags;

    public MessageEvent(DoggyBot bot, String message, Channel channel, User user, Map<String, String> tags) {
        super(bot);
        this.message = message;
        this.channel = channel;
        this.user = user;
        this.tags = tags;
    }

    @Override
    public void respond(String response) {

    }

    public String getMessage() {
        return message;
    }
}
