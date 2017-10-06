package twitch.database;

import org.hibernate.Session;
import org.hibernate.Transaction;
import twitch.dataobjects.TwitchChannel;

import java.util.List;

public class ChannelService implements Service {

    public TwitchChannel getChannel(long id) {
        Session session = databaseService.getFactory().openSession();
        ChannelDao channelDao = new ChannelDaoImpl(session);
        return channelDao.get(id);
    }

    public long add(TwitchChannel channel) {
        Session session = databaseService.getFactory().openSession();
        Transaction transaction = session.beginTransaction();
        ChannelDao channelDao = new ChannelDaoImpl(session);
        long l = channelDao.addChannel(channel);
        transaction.commit();
        return l;
    }

    public List<TwitchChannel> getAll() {
        Session session = databaseService.getFactory().openSession();
        ChannelDao channelDao = new ChannelDaoImpl(session);
        return channelDao.getAll();
    }

    public TwitchChannel findByName(String name) {
        Session session = databaseService.getFactory().openSession();
        ChannelDao channelDao = new ChannelDaoImpl(session);
        return channelDao.get(name);
    }

    public void update(TwitchChannel channel) {
        Session session = databaseService.getFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        ChannelDao channelDao = new ChannelDaoImpl(session);
        channelDao.update(channel);
        transaction.commit();
    }
}
