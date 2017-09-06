package irc.events;

import irc.events.events.PingEvent;

public class GenericEventsListener extends ListenerAdapter {
    @Override
    public void onPing(PingEvent event) {
        event.getBot().send("PONG :tmi.twitch.tv");
    }
}
