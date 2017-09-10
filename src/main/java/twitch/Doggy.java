package twitch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import twitch.chat.TwitchChat;

public class Doggy {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        TwitchChat.start();
    }


}
