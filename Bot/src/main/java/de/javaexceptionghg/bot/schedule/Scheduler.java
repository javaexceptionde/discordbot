package de.javaexceptionghg.bot.schedule;

import java.util.concurrent.*;

public class Scheduler {

    ExecutorService executor = Executors.newCachedThreadPool();
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    public <T> Future<?> schedule(Runnable runnable){
        return executor.submit(runnable);
    }

    public <T> Future<?> schedule(Runnable runnable, long delay){
        return scheduledExecutorService.schedule(runnable, delay, TimeUnit.MILLISECONDS);
    }

    public <T> Future<?> schedule(Runnable runnable, long delay, long interval){
        return scheduledExecutorService.scheduleAtFixedRate(runnable, delay, interval, TimeUnit.MILLISECONDS);
    }

    public <T> Future<?> schedule(Callable<T> callable, long delay, long interval){
        try {
            //return scheduledExecutorService.submit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
