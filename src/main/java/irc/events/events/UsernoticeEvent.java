package irc.events.events;

import irc.DoggyBot;
import irc.events.Event;

public class UsernoticeEvent extends Event {

    public UsernoticeEvent(DoggyBot bot) {
        super(bot);
    }

    @Override
    public void respond(String response) {

    }
}
