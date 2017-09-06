package irc.events.events;

import irc.DoggyBot;
import irc.events.Event;

public class PingEvent extends Event {

    private final String response;

    public PingEvent(DoggyBot bot) {
        super(bot);
        this.response = "PONG :tmi.twitch.tv";
    }

    @Override
    public void respond(String response) {
        getBot().send(response);
    }

    public String getResponse() {
        return response;
    }
}
