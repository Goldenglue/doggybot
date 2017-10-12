package irc;

import irc.events.Listener;
import irc.events.managers.ListenerManager;

import java.util.HashSet;
import java.util.Set;

public class ConfigurationBuilder {
    private String name;
    private int delay;
    private String host;
    private int port;
    private String password;
    private ListenerManager listenerManager;
    private Set<Channel> channelsToAutoJoin = new HashSet<>();

    public ConfigurationBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ConfigurationBuilder setDelay(int delay) {
        this.delay = delay;
        return this;
    }

    public ConfigurationBuilder setHost(String host) {
        this.host = host;
        return this;
    }

    public ConfigurationBuilder setPort(int port) {
        this.port = port;
        return this;
    }

    public ConfigurationBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public ConfigurationBuilder setListenerManager(ListenerManager listenerManager) {
        this.listenerManager = listenerManager;
        return this;
    }

    public ConfigurationBuilder setChannelsToAutoJoin(Set<Channel> channelsToAutoJoin) {
        this.channelsToAutoJoin = channelsToAutoJoin;
        return this;
    }

    public ConfigurationBuilder addListener(Listener listener) {
        listenerManager.addListener(listener);
        return this;
    }

    public ConfigurationBuilder addChannelToAutoJoin(Channel channel) {
        channelsToAutoJoin.add(channel);
        return this;
    }

    public ConfigurationBuilder addAllChannelsToAutoJoin(Set<Channel> channels) {
        channelsToAutoJoin.addAll(channels);
        return this;
    }

    public Configuration createConfiguration() {
        return new Configuration(name, delay, host, port, password, listenerManager, channelsToAutoJoin);
    }
}