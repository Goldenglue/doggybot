package twitch.database;

import twitch.dataobjects.TwitchChannel;

public interface ChannelDao {
    TwitchChannel get(long id);
    long addChannel(TwitchChannel channel);
}
