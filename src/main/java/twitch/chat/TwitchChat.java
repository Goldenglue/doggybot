package twitch.chat;

import irc.DoggyBot;

public class TwitchChat {
    private DoggyBot bot;

    public TwitchChat(DoggyBot bot) {
        this.bot = bot;
    }

    public void connect() {
        bot.send("PASS oauth:" + bot.getConfig().getPassword());
        bot.send("NICK " + bot.getConfig().getName());
        bot.send("CAP REQ :twitch.tv/membership");
        bot.send("CAP REQ :twitch.tv/tags");
        bot.send("CAP REQ :twitch.tv/commands");

        bot.joinChannel("#embertraveller");

    }
}
