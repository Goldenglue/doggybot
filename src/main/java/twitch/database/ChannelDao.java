package twitch.database;

import twitch.dataobjects.TwitchChannel;

import java.util.List;

public interface ChannelDao {
    TwitchChannel get(long id);

    long addChannel(TwitchChannel channel);

    List<TwitchChannel> getAll();

    void update(TwitchChannel channel);
}
