package irc.events;

import irc.DoggyBot;
import irc.events.types.GenericEvent;

import java.time.Instant;

public abstract class Event implements GenericEvent{
    private final DoggyBot bot;
    protected final Instant timestamp;

    public Event(DoggyBot bot) {
        this.bot = bot;
        this.timestamp = Instant.now();
    }

    public DoggyBot getBot() {
        return bot;
    }
}
