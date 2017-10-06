package irc.events.events;

import irc.Channel;
import irc.DoggyBot;
import irc.User;
import irc.events.Event;
import irc.events.types.ChannelEvent;
import irc.events.types.UserEvent;

import java.util.Map;

public class MessageEvent extends Event implements ChannelEvent, UserEvent {
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

    public void respondToUserChannel(String response) {
        bot.send("PRIVMSG #" + getUser().getUserChannel().getChannelName() + " :@" + getUser().getUsername() + " " + response);
    }

    public String getMessage() {
        return message;
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public User getUser() {
        return user;
    }

    public Map<String, String> getTags() {
        return tags;
    }
}
