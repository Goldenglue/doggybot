package irc;

public class ConfigurationBuilder {
    private String name;
    private int delay;
    private String host;
    private int port;
    private String password;

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

    public Configuration createConfiguration() {
        return new Configuration(name, delay, host, port, password);
    }
}