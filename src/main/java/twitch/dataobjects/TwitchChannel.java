package twitch.dataobjects;

import irc.Channel;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class TwitchChannel {
    @Id
    @GeneratedValue
    private long ID;
    private String name;
    @ElementCollection
    @CollectionTable(name = "commands_table")
    @Column(name = "commands")
    private Map<String, String> commands = new HashMap<>();
    private String test;

    public TwitchChannel(Channel channel) {
        this.name = channel.getChannelName();
    }

    public TwitchChannel() {
    }

    public TwitchChannel(long id, String name) {
        this.ID = id;
        this.name = name;
    }

    public TwitchChannel(String name) {
        this.name = name;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getCommands() {
        return commands;
    }

    public void setCommands(Map<String, String> commands) {
        this.commands = commands;
    }

    @Override
    public String toString() {
        return "TwitchChannel{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", commands=" + commands +
                '}';
    }

    public void addCommand(String command, String response) {
        commands.put(command, response);
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
