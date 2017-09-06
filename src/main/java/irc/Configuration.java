package irc;

import irc.events.GenericEventsListener;
import irc.events.managers.GenericListenerManager;
import irc.events.managers.ListenerManager;

public class Configuration {
    private String name;
    private int delay;
    private String host;
    private int port;
    private String password;
    private ListenerManager listenerManager;

    public Configuration(String name, int delay, String host, int port, String password) {
        this.name = name;
        this.delay = delay;
        this.host = host;
        this.port = port;
        this.password = password;
        this.listenerManager = new GenericListenerManager();
        this.listenerManager.addListener(new GenericEventsListener());
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
}
