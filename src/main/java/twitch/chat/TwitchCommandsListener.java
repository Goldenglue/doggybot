package twitch.chat;

import irc.events.ListenerAdapter;
import irc.events.events.MessageEvent;
import twitch.api.Twitch;
import twitch.utils.StringFormatting;

import java.time.Duration;

public class TwitchCommandsListener extends ListenerAdapter {
    @Override
    public void onMessage(MessageEvent event) {
        if (event.getMessage().startsWith("!")) {
            String command = event.getMessage().split(" ")[0];
            checkForAvailableCommands(command, event);
        }
    }

    private void checkForAvailableCommands(String possibleCommand, MessageEvent event) {
        switch (possibleCommand) {
            case "!uptime":
                uptime(event);
                break;
        }
    }

    private void uptime(MessageEvent event) {
        Duration uptime = Twitch.uptime(event.getChannel().getChannelName());
        if (uptime.isZero()) {
            event.respond("Stream is offline! BibleThump");
        } else {
            System.out.println(StringFormatting.timeStringFromDuration(uptime));
            event.respond("Stream is online for " + StringFormatting.timeStringFromDuration(uptime));
        }

    }
}
