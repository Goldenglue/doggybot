package twitch;

import twitch.chat.TwitchChat;


//TODO create a database for all this thing so that commands are saved by channel and stuff
//TODO maybe also create special users commands!
//TODO move to maven so modules actually work
public class Doggy {

    public static void main(String[] args) {
        TwitchChat.start();
    }


}
