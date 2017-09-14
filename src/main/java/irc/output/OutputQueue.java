package irc.output;

import irc.DoggyBot;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class OutputQueue {
    private final DoggyBot bot;
    private final Queue<String> queue = new LinkedList<>();
    private AtomicInteger queueSize = new AtomicInteger();
    private final int delay;
    private final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    private long lastSent;

    private final Runnable queuePolling = () -> {
        if (queue.size() > 0) {
            queueSize.decrementAndGet();
            lastSent = Instant.now().toEpochMilli();
            sendNow(queue.poll());
        }
    };

    public OutputQueue(DoggyBot bot) {
        this.bot = bot;
        this.delay = bot.getConfig().getDelay() * 1000;
        service.scheduleAtFixedRate(queuePolling, 0, bot.getConfig().getDelay(), TimeUnit.MILLISECONDS);
    }

    public void send(String line) {
        if (queueSize.get() == 0 && (Instant.now().toEpochMilli() - lastSent) < delay) {
            lastSent = Instant.now().toEpochMilli();
            sendNow(line);
        } else if (queueSize.get() < 10) {
            queueSize.incrementAndGet();
            queue.offer(line);
        }
    }

    public void sendNow(String line) {
        bot.sendLineToServer(line);
    }


}
