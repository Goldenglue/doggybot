package irc.events.managers;

import irc.events.Event;
import irc.events.Listener;

public abstract class AbstractListenerManager implements ListenerManager {

    @Override
    public void executeListener(Listener listener, Event event) {
        listener.onEvent(event);
    }
}
