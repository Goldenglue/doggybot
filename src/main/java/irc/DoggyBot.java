package irc;

import irc.output.Output;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

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
            connect();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Connection interrupted");
            logger.error("Start failed");
            return false;
        }
        return true;
    }

    private boolean connect() {
        try {
            InetAddress  address =  InetAddress.getByName(config.getHost());
            socket = new Socket(address, config.getPort());
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Failed to connect");
            return false;
        }
        Thread thread = new Thread(messageProcessing);
        thread.start();
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
        logger.info("Received message: " + input);
        inputHandler.handle(input);
        return true;
    }

    private void sendLineToServer(String line) {
        printWriter.println(line);
    }

    public void send(String line) {
        sendLineToServer(line);
    }

    private void shutdown() {
       logger.info("Shutdowning");
    }

}
