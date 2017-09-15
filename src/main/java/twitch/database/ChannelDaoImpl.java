package twitch.database;

import org.hibernate.Session;
import twitch.dataobjects.TwitchChannel;

import java.util.List;

public class ChannelDaoImpl implements ChannelDao {
    private Session session;

    public ChannelDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public TwitchChannel get(long id) {
        return session.get(TwitchChannel.class, id);
    }

    @Override
    public long addChannel(TwitchChannel channel) {
        return (long) session.save(channel);
    }

    @Override
    public List<TwitchChannel> getAll() {
        return session.createQuery("from TwitchChannel").list();
    }

    @Override
    public void update(TwitchChannel channel) {
        session.update(channel);
    }


}
