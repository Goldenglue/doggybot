package irc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Channel {
    private final Set<User> activeUsers = new HashSet<>();
    private String channelName;
    private Map<String, String> commands = new HashMap<>();
    private DoggyBot bot;

    public Channel(String channelName) {
        this.channelName = channelName;
    }

    public Channel(String channelName, Map<String, String> commands, DoggyBot bot) {
        this.channelName = channelName;
        this.commands = commands;
        this.bot = bot;
    }

    public void addUser(User user) {
        activeUsers.add(user);
    }

    public void addCommand(String command, String response) {
        commands.put(command, response);
    }

    public Set<User> getActiveUsers() {
        return activeUsers;
    }

    public String getChannelName() {
        return channelName;
    }

    public Map<String, String> getCommands() {
        return commands;
    }

    public DoggyBot getBot() {
        return bot;
    }
}
