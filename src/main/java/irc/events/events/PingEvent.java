package irc.events.events;

import irc.DoggyBot;
import irc.events.Event;

public class PingEvent extends Event{

    public PingEvent(DoggyBot bot) {
        super(bot);
    }

    @Override
    public void response(String response) {
        bot.send(response);
    }
}
