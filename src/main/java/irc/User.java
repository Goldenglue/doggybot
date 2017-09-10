package irc;

public class User {
    private String username;
    private Channel userChannel;

    public User(String username) {
        this.username = username;
        this.userChannel = new Channel(username);
    }

    public String getUsername() {
        return username;
    }

    public Channel getUserChannel() {
        return userChannel;
    }
}
