package irc.events;

import irc.events.events.PingEvent;

public abstract class ListenerAdapter implements Listener {
    @Override
    public void onEvent(Event event) {
        if (event instanceof PingEvent) {
            onPing(((PingEvent) event));
        }

    }

    public void onPing(PingEvent event) {

    }
}
