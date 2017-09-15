package twitch.database;

import org.hibernate.Session;
import twitch.dataobjects.TwitchChannel;

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
        ChannelDaoImpl channelDao = new ChannelDaoImpl(session);
        return channelDao.addChannel(channel);
    }
}
