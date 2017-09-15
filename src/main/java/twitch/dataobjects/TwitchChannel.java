package twitch.dataobjects;

import irc.Channel;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
public class TwitchChannel implements Serializable {
    @Id
    @GeneratedValue
    private long ID;
    private String name;
    @ElementCollection
    private Map<String, String> channelCommands = new HashMap<>();

    @SuppressWarnings("UnusedDeclaration")
    public TwitchChannel(Channel channel) {
        this.name = channel.getChannelName();
    }

    @SuppressWarnings("UnusedDeclaration")
    public TwitchChannel() {
    }

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getChannelCommands() {
        return channelCommands;
    }
}
