# doggybot
Chat bot for twitch.tv with own irc implementation speciallyfor this


To call this bot to join your channel you can go to twitch.tv/doggybotthefirst and type !join in chat (when bot is online ofc)
Add new listeners by creating a class and extending it with ListenerAdapter, then simply add this listener to bot's configuration

To set up bot for yourselft you need to create another accuout on twitch.tv (so that you won't get blocked if something goes wrong).
* [Register](dev.twitch.tv) your app. 
* Get an OAuth token [here](twitchapps.com/tmi).
* Create a token.txt file in project folder and put it in here. 
* Last step is to configure setName() in TwitchChat class, it should be the name of your registered app.
* Prob should work, might also want to to download H2 driver but have no idea what to suggest about that.
