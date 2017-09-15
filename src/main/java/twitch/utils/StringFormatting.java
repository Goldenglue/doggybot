package twitch.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * So Java 9 has a very nice method for this, gonna wait
 */
public class StringFormatting {

    public static String timeStringFromDuration(Duration duration) {
        List<String> timeStrings = new ArrayList<>(4);
        long days = duration.toDays();
        long hours = duration.toHours() - days * 24;
        long minutes = duration.toMinutes() - hours * 60;
        long seconds = duration.getSeconds() - (hours * 60 * 60 + minutes * 60);

        if (days > 0) timeStrings.add(days + ((days > 1) ? " Days" : " Day"));
        if (hours > 0) timeStrings.add(hours + ((hours > 1) ? " Hours" : " Hour"));
        if (minutes > 0) timeStrings.add(minutes + ((minutes > 1) ? " Minutes" : " Minute"));
        if (seconds > 0) timeStrings.add(seconds + ((seconds > 1) ? " Seconds" : " Second"));

        return String.join(", ", timeStrings);
    }
}
