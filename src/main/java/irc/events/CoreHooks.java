package irc.events;

import irc.events.events.PingEvent;

public class CoreHooks extends ListenerAdapter {
    @Override
    public void onPing(PingEvent event) {
        event.getBot().send("PONG :tmi.twitch.tv");
    }
}
