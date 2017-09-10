package twitch.chat;

import irc.events.ListenerAdapter;
import irc.events.events.MessageEvent;

public class TwitchCommandsListener extends ListenerAdapter {
    @Override
    public void onMessage(MessageEvent event) {
        if (event.getMessage().startsWith("!")) {
            String command  = event.getMessage().split(" ")[0];

        }
    }

    private void checkForAvailableCommands(String possibleCommand) {
        switch (possibleCommand) {
            case "!uptime": uptime(); break;
        }
    }

    private void uptime() {

    }
}
