package irc.events.managers;

import irc.events.Event;
import irc.events.Listener;

import java.util.HashSet;
import java.util.Set;

public class GenericListenerManager extends AbstractListenerManager {
    protected final Set<Listener> listeners = new HashSet<>();

    @Override
    public void onEvent(Event event) {
        listeners.forEach(listener -> executeListener(listener, event));
    }

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {

    }
}
