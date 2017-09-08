package irc;

import java.util.HashSet;
import java.util.Set;

public class Channel {
    private final Set<User> activeUsers = new HashSet<>();
    private String channelName;

    public Channel(String channelName) {
        this.channelName = channelName;
    }

    private void addUser(User user) {
        activeUsers.add(user);
    }

    public Set<User> getActiveUsers() {
        return activeUsers;
    }

    public String getChannelName() {
        return channelName;
    }
}
