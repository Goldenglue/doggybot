package twitch.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    public void scheduleAtFixedRate(Runnable task, long rate) {
        executorService.scheduleAtFixedRate(task, rate, rate, TimeUnit.MINUTES);
    }

}
