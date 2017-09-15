package twitch.database;

import org.hibernate.Session;
import org.hibernate.Transaction;
import twitch.dataobjects.TwitchChannel;

import java.util.List;

public class ChannelService {
    private final DatabaseService databaseService;

    public ChannelService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public TwitchChannel getChannel(long id) {
        Session session = databaseService.getFactory().openSession();
        ChannelDaoImpl channelDao = new ChannelDaoImpl(session);
        return channelDao.get(id);
    }

    public long insertChannel(TwitchChannel channel) {
        Session session = databaseService.getFactory().openSession();
        Transaction transaction = session.beginTransaction();
        ChannelDaoImpl channelDao = new ChannelDaoImpl(session);
        long l = channelDao.addChannel(channel);
        transaction.commit();
        return l;
    }

    public List<TwitchChannel> getAll() {
        Session session = databaseService.getFactory().openSession();
        ChannelDaoImpl channelDao = new ChannelDaoImpl(session);
        return channelDao.getAll();
    }
}
