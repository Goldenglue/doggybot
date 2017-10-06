package irc;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Channel {
    private final Set<User> activeUsers = new HashSet<>();
    private String channelName;
    private DoggyBot bot;

    public Channel(String channelName) {
        this.channelName = channelName;
    }

    public Channel(String channelName, DoggyBot bot) {
        this.channelName = channelName;
        this.bot = bot;
    }

    public void addUser(User user) {
        activeUsers.add(user);
    }

    public Set<User> getActiveUsers() {
        return activeUsers;
    }

    public String getChannelName() {
        return channelName;
    }

    public DoggyBot getBot() {
        return bot;
    }
}
