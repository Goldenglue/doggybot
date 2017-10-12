package twitch.chat;

import irc.Channel;
import irc.events.ListenerAdapter;
import irc.events.events.MessageEvent;
import twitch.api.Twitch;
import twitch.dataobjects.ChannelCommand;
import twitch.dataobjects.TwitchChannel;
import twitch.utils.StringFormatting;

import java.time.Duration;
import java.util.Optional;
import java.util.Random;

public class TwitchCommandsListener extends ListenerAdapter {
    @Override
    public void onMessage(MessageEvent event) {
        if (event.getMessage().startsWith("!")) {
            String command = event.getMessage().split(" ")[0];
            checkForAvailableCommands(command, event);
        }
        if (event.getMessage().toLowerCase().contains("ember")) {
            onEmber(event);
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
            case "!clap":
                clap(event);
                break;
            case "!viewers":
                viewers(event);
                break;
        }
        checkForUserCommand(possibleCommand, event);
    }

    private void onEmber(MessageEvent event) {
        event.respond("Who is this ember dude? Maybe you meant DestroyerOfUniverses? DansGame");
    }

    private void viewers(MessageEvent event) {
        int viewers = Twitch.getViewers(event.getChannel().getChannelName());
        if (viewers != -1) {
            event.respond(viewers + " people are watching right now! ");
        } else {
            event.respond("Y u try to get offline stream viewers? DansGame");
        }
    }

    private void clap(MessageEvent event) {
        StringBuilder clapBuilder = new StringBuilder();
        Random random = new Random();
        switch (random.nextInt(6)) {
            case 0: clapBuilder.append("KappaPride ");break;
            case 1: clapBuilder.append("Kappa ");break;
            case 2: clapBuilder.append("PogChamp ");break;
            case 3: clapBuilder.append("FrankerZ ");break;
            case 4: clapBuilder.append("CoolCat ");break;
            case 5: clapBuilder.append("RalpherZ ");
        }
        clapBuilder.append(" //");
        event.respond(clapBuilder.toString());
    }

    private void checkForUserCommand(String possibleCommand, MessageEvent event) {
        TwitchChannel channel = event.getBot().getConfig().getChannelService().findByName(event.getChannel().getChannelName());

        Optional<ChannelCommand> channelCommand = channel.getCommands()
                .stream()
                .filter(command -> command.getExecutor().equals(possibleCommand))
                .findFirst();
        channelCommand.ifPresent(command -> event.respond(command.getCommand()));


    }

    //man that's bullshit
    private void addCommand(MessageEvent event) {
        System.out.println("Starting adding command");
        TwitchChannel channel = event.getBot().getConfig().getChannelService().findByName(event.getChannel().getChannelName());
        System.out.println("Got channel");

        String[] messageParts = event.getMessage().split("\\s", 3);
        ChannelCommand channelCommand = new ChannelCommand(messageParts[1], messageParts[2]);
        channel.addCommand(channelCommand);

        System.out.println("Creating command " + messageParts[1] + " which means " + messageParts[2]);

        try {
            event.getBot().getConfig().getChannelService().update(channel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        event.respond("Command " + messageParts[1] + " created!");
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
            Channel channel = new Channel(event.getUser().getUsername());
            event.getBot().joinChannel(channel);

            TwitchChannel twitchChannel = new TwitchChannel(channel);
            event.getBot().getConfig().getChannelService().add(twitchChannel);

            event.respondToUserChannel("Hello "  + event.getUser().getUsername() + " PogChamp");
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
