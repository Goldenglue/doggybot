package irc;

import irc.events.Listener;
import irc.events.managers.ListenerManager;
import twitch.database.ChannelService;

import java.util.HashSet;
import java.util.Set;

public class Configuration {
    private String name;
    private int delay;
    private String host;
    private int port;
    private String password;
    private ListenerManager listenerManager;
    private final Set<Channel> channels = new HashSet<>();
    private final Set<Channel> channelsToAutoJoin;
    private ChannelService channelService = new ChannelService();

    public Configuration(String name, int delay, String host, int port, String password, ListenerManager listenerManager, Set<Channel> channelsToAutoJoin) {
        this.name = name;
        this.delay = delay;
        this.host = host;
        this.port = port;
        this.password = password;
        this.listenerManager = listenerManager;
        this.channelsToAutoJoin = channelsToAutoJoin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ListenerManager getListenerManager() {
        return listenerManager;
    }

    public void addListener(Listener listener) {
        listenerManager.addListener(listener);
    }

    public Set<Channel> getChannels() {
        return channels;
    }

    public void addChannel(Channel channel) {
        channels.add(channel);
    }

    public void addChannelToAutoJoin(String channel) {
        channelsToAutoJoin.add(new Channel(channel));
    }

    public void addAllChannelsToAutoJoin(Set<Channel> channels) {
        channelsToAutoJoin.addAll(channels);
    }

    public Set<Channel> getChannelsToAutoJoin() {
        return channelsToAutoJoin;
    }

    public ChannelService getChannelService() {
        return channelService;
    }

    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }
}
