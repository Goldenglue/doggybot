package twitch.database;

import org.junit.Before;
import org.junit.Test;
import twitch.dataobjects.TwitchChannel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ChannelServiceTest {
    private ChannelService service = new ChannelService();
    private Set<TwitchChannel> channels = new HashSet<>();

    @Before
    public void setUp() throws Exception {
        channels.add(new TwitchChannel("one"));
        channels.add(new TwitchChannel("two"));
        channels.add(new TwitchChannel("three"));
        channels.add(new TwitchChannel("four"));
    }

    @Test
    public void getChannel() throws Exception {

    }

    @Test
    public void insertChannel() throws Exception {
        channels.forEach(channel -> service.add(channel));
    }

    @Test
    public void getAll() throws Exception {
        List<TwitchChannel> all = service.getAll();
        System.out.println(all);
        if (all.size() == 4) {
            assertTrue("All channel were found", true);
        } else {
            assertTrue("Not all channels found! " + all.size(), false);
        }
    }

    @Test
    public void findByName() throws Exception {
        if (channels.contains(service.findByName("four"))) {
            assertTrue("Found right channel", true);
        }
    }

}