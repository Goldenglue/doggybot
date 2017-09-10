package irc.events.events;

import irc.DoggyBot;
import irc.events.Event;

public class UserNoticeEvent extends Event {

    public UserNoticeEvent(DoggyBot bot) {
        super(bot);
    }

    @Override
    public void respond(String response) {

    }
}
