package twitch.database;

import org.hibernate.Session;
import twitch.dataobjects.TwitchChannel;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
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
        return session.createQuery("select t from TwitchChannel t").list();
    }

    @Override
    public void update(TwitchChannel channel) {
        session.merge(channel);
    }

    @Override
    public TwitchChannel get(String name) {
        return (TwitchChannel) session.createQuery("select t from TwitchChannel t where t.name = :name")
                .setParameter("name", name)
                .uniqueResult();
    }


}
