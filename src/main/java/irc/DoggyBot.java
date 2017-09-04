package irc;

import irc.output.Output;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DoggyBot {
    //general
    private
    //irc stuff
    private Configuration config;
    private InputHandler inputHandler;
    private Output output;
    //logger
    private Logger logger = LogManager.getLogger();

    public DoggyBot(Configuration config) {
        this.config = config;
        inputHandler = new InputHandler(this);



    }

    public boolean start() {

    }

    private boolean connect() {

    }

    private void processLine() {
        while (processingLine()) {

        }
    }

    private boolean processingLine() {

    }

    private void sendLineToServer(String line) {

    }

    public void send(String line) {
        sendLineToServer(line);
    }

}
