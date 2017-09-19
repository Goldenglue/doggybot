package twitch;

import irc.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import twitch.chat.TwitchChat;
import twitch.database.ChannelService;
import twitch.database.DatabaseService;
import twitch.database.Service;
import twitch.dataobjects.TwitchChannel;


//TODO create a database for all this thing so that commands are saved by channel and stuff (maybe actually user Hibernate?)
//TODO maybe also create special users commands!
public class Doggy {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        ChannelService channelService = new ChannelService();
        TwitchChannel jej = new TwitchChannel(new Channel("jej"));
        jej.addCommand("dai","mne");
        channelService.insertChannel(jej);
        TwitchChannel heh = new TwitchChannel(new Channel("heh"));
        channelService.insertChannel(heh);
        TwitchChannel hoh = new TwitchChannel(new Channel("hoh"));
        channelService.insertChannel(hoh);
        TwitchChannel hah = new TwitchChannel(new Channel("hah"));
        channelService.insertChannel(hah);

        System.out.println(channelService.getAll());

        //TwitchChat.start();
    }

}
