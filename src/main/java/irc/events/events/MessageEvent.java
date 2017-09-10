package irc.events.events;

import irc.Channel;
import irc.DoggyBot;
import irc.User;
import irc.events.Event;

import java.util.Map;

public class MessageEvent extends Event {
    private final Channel channel;
    private final User user;
    private final String message;
    private final Map<String, String> tags;

    public MessageEvent(DoggyBot bot, Channel channel, User user, String message, Map<String, String> tags) {
        super(bot);
        this.channel = channel;
        this.user = user;
        this.message = message;
        this.tags = tags;
    }

    @Override
    public void respond(String response) {
        bot.send("PRIVMSG #" + getChannel().getChannelName() + " :@" + getUser().getUsername() + " " + response);
    }

    public String getMessage() {
        return message;
    }

    public Channel getChannel() {
        return channel;
    }

    public User getUser() {
        return user;
    }

    public Map<String, String> getTags() {
        return tags;
    }
}
