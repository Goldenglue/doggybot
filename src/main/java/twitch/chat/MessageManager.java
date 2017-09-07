package twitch.chat;

import irc.events.Event;
import irc.events.events.MessageEvent;
import irc.events.managers.GenericListenerManager;

public class MessageManager extends GenericListenerManager {
    @Override
    public void onEvent(Event event) {
        if (event instanceof MessageEvent) {
            String message = ((MessageEvent) event).getMessage();
        }
    }
}
