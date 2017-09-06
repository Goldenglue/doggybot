package irc.events.managers;

import irc.events.Event;
import irc.events.Listener;

public interface ListenerManager {
    void onEvent(Event event);

    void executeListener(Listener listener, Event event);

    void addListener(Listener listener);

    void removeListener(Listener listener);
}
