package twitch;

import irc.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import twitch.chat.TwitchChat;
import twitch.database.ChannelService;
import twitch.database.DatabaseService;
import twitch.dataobjects.TwitchChannel;


//TODO create a database for all this thing so that commands are saved by channel and stuff (maybe actually user Hibernate?)
//TODO maybe also create special users commands!
//TODO move to maven so modules actually work
public class Doggy {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        DatabaseService service = new DatabaseService();
        ChannelService channelService = new ChannelService(service);
        TwitchChannel channel = new TwitchChannel(new Channel("kek"));
        TwitchChannel channel1 = new TwitchChannel(new Channel("kok"));
        System.out.println(channelService.insertChannel(channel));
        System.out.println(channelService.insertChannel(channel1));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(channelService.getAll());
        //TwitchChat.start();
    }

}
