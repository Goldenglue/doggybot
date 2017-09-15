package twitch.database;

import org.hibernate.Session;
import twitch.dataobjects.TwitchChannel;

public class ChannelDaoImpl implements ChannelDao {
    private Session session;

    public ChannelDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public TwitchChannel get(long id) {
        return null;
    }

    @Override
    public long addChannel(TwitchChannel channel) {
        return 0;
    }
}
