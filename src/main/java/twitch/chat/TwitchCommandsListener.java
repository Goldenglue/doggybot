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
            case "!join":
                join(event);
                break;
            case "!part":
                part(event);
                break;
            case "!addCommand":
                addCommand(event);
                break;
        }
    }

    private void addCommand(MessageEvent event) {

    }

    private void part(MessageEvent event) {
        if (event.getBot().getConfig().getChannels().contains(event.getUser().getUserChannel())) {
            event.getBot().partFromChannel(event.getUser().getUserChannel());
        }
    }

    /**
     * Prevents user to call bot to join his channel twice
     * Also only callable from bot's main channel
     *
     * @param event
     */
    private void join(MessageEvent event) {
        if (event.getChannel().getChannelName().equals("doggybotthefirst") && !event.getBot().getConfig().getChannels().contains(event.getUser().getUserChannel())) {
            event.getBot().joinChannel(event.getUser().getUsername());
        }

    }

    private void uptime(MessageEvent event) {
        Duration uptime = Twitch.uptime(event.getChannel().getChannelName());
        if (uptime.isZero()) {
            event.respond("Stream is offline! BibleThump");
        } else {
            System.out.println(StringFormatting.timeStringFromDuration(uptime));
            event.respond("Stream is online for " + StringFormatting.timeStringFromDuration(uptime) + " PogChamp");
        }

    }
}
