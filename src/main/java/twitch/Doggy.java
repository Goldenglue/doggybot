package twitch;

import irc.Channel;
import irc.Configuration;
import irc.ConfigurationBuilder;
import irc.DoggyBot;
import irc.events.GenericEventsListener;
import irc.events.managers.GenericListenerManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import twitch.chat.TwitchChat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Doggy {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Configuration config = new ConfigurationBuilder()
                .setDelay(1500)
                .setHost("irc.chat.twitch.tv")
                .setPort(6667)
                .setName("DoggyBotTheFirst")
                .setPassword(getPassword())
                .setListenerManager(new GenericListenerManager())
                .addListener(new GenericEventsListener())
                .addChannelToAutoJoin(new Channel("#embertraveller"))
                .addChannelToAutoJoin(new Channel("#navioot"))
                .addChannelToAutoJoin(new Channel("#alkaizerx"))
                .createConfiguration();

        DoggyBot bot = new DoggyBot(config);
        bot.start();

        TwitchChat chat =  new TwitchChat(bot);
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
}
