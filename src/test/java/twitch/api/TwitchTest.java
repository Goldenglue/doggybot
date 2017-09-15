package twitch.api;

import org.junit.Test;
import twitch.utils.StringFormatting;

import static org.junit.Assert.*;

public class TwitchTest {
    @Test
    public void uptime() throws Exception {
        System.out.println(StringFormatting.timeStringFromDuration(Twitch.uptime("superjj102")));
    }

}