package twitch.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class StringFormatting {

    public static String timeStringFromDuration(Duration duration) {
        List<String> timeStrings = new ArrayList<>(4);
        long days = duration.toDays();
        int hours = (int) duration.toHours();
        int minutes = (int) duration.toMinutes();
        int seconds = (int) (duration.toMillis() / 1000);

        if (days > 0) {
            timeStrings.add(days + ((days > 1) ? " Days" : " Day"));
        }
        if (hours > 0) {
            timeStrings.add(hours + ((hours > 1) ? " Hours" : " Hour"));
        }
        if (minutes > 0) {
            timeStrings.add(minutes + ((minutes > 1) ? " Minutes" : " Minute"));
        }
        if (seconds > 0) {
            timeStrings.add(seconds + ((seconds > 1) ? " Seconds" : " Second"));
        }

        return String.join(", ", timeStrings);
    }
}
