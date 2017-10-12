package twitch.dataobjects;

import irc.Channel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TwitchChannel")
public class TwitchChannel {
    @Id
    @GeneratedValue
    private long ID;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ChannelCommand> commands = new ArrayList<>();

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

    public List<ChannelCommand> getCommands() {
        return commands;
    }

    public void setCommands(List<ChannelCommand> commands) {
        this.commands = commands;
    }

    public void addCommand(ChannelCommand channelCommand) {
        commands.add(channelCommand);
    }

}
