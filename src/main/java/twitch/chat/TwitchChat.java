package twitch.chat;

import irc.Channel;
import irc.Configuration;
import irc.ConfigurationBuilder;
import irc.DoggyBot;
import irc.events.GenericEventsListener;
import irc.events.managers.GenericListenerManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TwitchChat {
    private static DoggyBot bot;
    private static final Logger logger = LogManager.getLogger();
    static {
        Configuration config = new ConfigurationBuilder()
                .setDelay(1500)
                .setHost("irc.chat.twitch.tv")
                .setPort(6667)
                .setName("DoggyBotTheFirst")
                .setPassword(getPassword())
                .setListenerManager(new GenericListenerManager())
                .addListener(new GenericEventsListener())
                .addListener(new TwitchCommandsListener())
                .addChannelToAutoJoin(new Channel("doggybotthefirst"))
                //.addChannelToAutoJoin(new Channel("#embertraveller"))
                .createConfiguration();

        bot = new DoggyBot(config);
    }

    private static String getPassword() {
        Path path = Paths.get("token.txt");
        String pass = null;
        if (Files.exists(path)) {
            try {
                pass = Files.readAllLines(path).get(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            logger.error("File with password does not exist.");
        }
        return pass;
    }

    public static void send(String text) {
        bot.send(text);
    }

    public static void start() {
        bot.start();
        //bot.send("PRIVMSG #embertraveller :DoggyBot is ready! PogChamp");
    }
}
