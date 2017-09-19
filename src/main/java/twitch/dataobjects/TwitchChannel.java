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
    private Map<String, String> channelCommands = new HashMap<>();

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

    public Map<String, String> getChannelCommands() {
        return channelCommands;
    }

    public void setChannelCommands(Map<String, String> channelCommands) {
        this.channelCommands = channelCommands;
    }

    @Override
    public String toString() {
        return "TwitchChannel{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", channelCommands=" + channelCommands +
                '}';
    }

    public void addCommand(String command, String response) {
        channelCommands.put(command, response);
    }
}
