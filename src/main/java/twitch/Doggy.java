package twitch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import twitch.chat.TwitchChat;


//TODO create a database for all this thing so that commands are saved by channel and stuff (maybe actually user Hibernate?)
//TODO maybe also create special users commands!
//TODO move to maven so modules actually work
public class Doggy {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        TwitchChat.start();
    }

}
