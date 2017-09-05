package twitch.chat;

import irc.DoggyBot;

public class TwitchChat {
    DoggyBot bot;

    public TwitchChat(DoggyBot bot) {
        this.bot = bot;
    }

    public void connect() {
        bot.send("PASS oauth:" + bot.getConfig().getPassword());
        bot.send("NICK" + bot.getConfig().getName());
    }
}
