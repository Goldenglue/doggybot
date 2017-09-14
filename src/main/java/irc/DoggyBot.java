package irc;

import irc.output.OutputQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DoggyBot {

    private final Configuration config;
    private final InputHandler inputHandler;
    private final OutputQueue outputQueue;

    private final Logger logger = LogManager.getLogger();
    private Socket socket;

    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private final Runnable messageProcessing = this::processLine;

    public DoggyBot(Configuration config) {
        this.config = config;
        this.outputQueue = new OutputQueue(this);
        this.inputHandler = new InputHandler(this);
    }

    public boolean start() {
        try {
            return connect();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Connection interrupted");
            logger.error("Start failed");
            return false;
        }
    }

    private boolean connect() {
        try {
            InetAddress address = InetAddress.getByName(config.getHost());
            socket = new Socket(address, config.getPort());
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Failed to connect");
            return false;
        }
        new Thread(messageProcessing).start();

        sendLineToServer("PASS oauth:" + getConfig().getPassword());
        sendLineToServer("NICK " + getConfig().getName());
        sendLineToServer("CAP REQ :twitch.tv/membership");
        sendLineToServer("CAP REQ :twitch.tv/tags");
        sendLineToServer("CAP REQ :twitch.tv/commands");

        if (!getConfig().getChannelsToAutoJoin().isEmpty()) {
            getConfig().getChannelsToAutoJoin().forEach(channel -> joinChannel(channel.getChannelName()));
        }

        return true;

    }

    private void processLine() {
        while (processingLine()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("Processing is done, shutdowning");
        shutdown();
    }

    private boolean processingLine() {
        String input;
        try {
            input = bufferedReader.readLine();
        } catch (IOException e) {
            logger.error("Reading message from " + socket.getInetAddress() + " failed");
            e.printStackTrace();
            return false;
        }
        executorService.submit(() -> inputHandler.handle(input));

        return true;
    }

    public void sendLineToServer(String line) {
        logger.info("Sending: --> " + line);
        printWriter.println(line);
    }

    public void send(String line) {
        outputQueue.send(line);
    }

    public void joinChannel(String channelName) {
        getConfig().addChannel(new Channel(channelName));
        send("JOIN #" + channelName);
    }

    public void partFromChannel(Channel userChannel) {
        if (getConfig().getChannels().contains(userChannel)) {
            send("PART #" + userChannel.getChannelName());
            getConfig().getChannels().remove(userChannel);
        }
    }

    private void shutdown() {
        logger.info("Shutdowning");
    }

    public Configuration getConfig() {
        return config;
    }


}
