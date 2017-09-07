package irc.events;

import irc.events.events.MessageEvent;
import irc.events.events.PingEvent;

public class GenericEventsListener extends ListenerAdapter {
    @Override
    public void onPing(PingEvent event) {
        event.getBot().send(event.getResponse());
    }

    @Override
    public void onMessage(MessageEvent event) {

    }
}
