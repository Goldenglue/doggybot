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
        return session.createQuery("select t from TwitchChannel t").list();
    }

    @Override
    public void update(TwitchChannel channel) {
        /*int i = session.createQuery("update TwitchChannel t set t.commands = :commands where t.id = :id")
                .setParameter("commands", channel.getCommands())
                .setParameter("id", channel.getID()).executeUpdate();*/
        session.update(channel);
    }

    @Override
    public TwitchChannel get(String name) {
        return (TwitchChannel) session.createQuery("select t from TwitchChannel t where t.name = :name")
                .setParameter("name", name)
                .uniqueResult();
    }


}
