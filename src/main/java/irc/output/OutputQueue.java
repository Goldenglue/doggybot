package irc.output;

import irc.DoggyBot;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OutputQueue {
    private final DoggyBot bot;
    private final Queue<String> queue = new LinkedList<>();
    private final int delay;
    private final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    private final Runnable taskRunnable;
    private long lastSent;


    public OutputQueue(DoggyBot bot) {
        this.bot = bot;
        this.delay = bot.getConfig().getDelay() * 1000;

        taskRunnable = () -> {
            if (queue.size() > 0) {
                System.out.println(queue.size());
                lastSent = Instant.now().toEpochMilli();
                bot.sendLineToServer(queue.poll());
            }
        };
        service.scheduleAtFixedRate(taskRunnable, 0, 1500, TimeUnit.MILLISECONDS);
    }

    public void send(String line) {
        if (queue.size() == 0 && (Instant.now().toEpochMilli() - lastSent) > delay) {
            lastSent = Instant.now().toEpochMilli();
            sendNow(line);
        } else {
            queue.offer(line);
        }
        //bot.sendLineToServer(line);
    }

    public void sendNow(String line) {
        bot.sendLineToServer(line);
    }


}
