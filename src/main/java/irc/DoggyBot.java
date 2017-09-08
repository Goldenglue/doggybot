package irc;

import irc.output.Output;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class DoggyBot {
    //general
    //irc stuff
    private Configuration config;
    private InputHandler inputHandler;
    private Output output;
    //logger
    private Logger logger = LogManager.getLogger();
    private Socket socket;

    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    //runnable
    private Runnable messageProcessing = this::processLine;

    public DoggyBot(Configuration config) {
        this.config = config;
        inputHandler = new InputHandler(this);
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
        inputHandler.handle(input);
        return true;
    }

    private void sendLineToServer(String line) {
        printWriter.println(line);
    }

    public void send(String line) {
        logger.info("Sending: --> " + line);
        sendLineToServer(line);
    }

    public void joinChannel(String channelName) {
        getConfig().addChannel(new Channel(channelName));
        sendLineToServer("JOIN " + channelName);
    }

    private void shutdown() {
        logger.info("Shutdowning");
    }

    public Configuration getConfig() {
        return config;
    }
}
