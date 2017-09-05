package twitch;

import irc.Configuration;
import irc.ConfigurationBuilder;
import irc.DoggyBot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Doggy {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Configuration config = new ConfigurationBuilder()
                .setDelay(2000)
                .setHost("irc.chat.twitch.tv")
                .setPort(6667)
                .setName("DoggyBotTheThird")
                .setPassword(getPassword())
                .createConfiguration();
        DoggyBot bot = new DoggyBot(config);
        bot.start();
    }

    private static String getPassword() {
        Path path = Paths.get("oauthToken");
        String pass = null;
        if (Files.exists(path)) {
            try {
                pass = Files.readAllLines(path).get(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            logger.error("Files with password does not exist.");
        }
        return pass;
    }
}
