package irc;

import irc.events.Listener;
import irc.events.managers.GenericListenerManager;
import irc.events.managers.ListenerManager;

public class ConfigurationBuilder {
    private String name;
    private int delay;
    private String host;
    private int port;
    private String password;
    private ListenerManager listenerManager;

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

    public ConfigurationBuilder addListener(Listener listener) {
        if (listenerManager != null) {
            listenerManager.addListener(listener);
        } else {
            listenerManager = new GenericListenerManager();
            listenerManager.addListener(listener);
        }
        return this;
    }

    public Configuration createConfiguration() {
        return new Configuration(name, delay, host, port, password, listenerManager);
    }
}