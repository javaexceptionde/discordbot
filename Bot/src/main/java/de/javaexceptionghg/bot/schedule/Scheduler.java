/*
 *
 *  *     Copyright (C) 2022  Jonathan Benedikt Bull<jonathan@jbull.dev>
 *  *
 *  *     This program is free software: you can redistribute it and/or modify
 *  *     it under the terms of the GNU General Public License as published by
 *  *     the Free Software Foundation, either version 3 of the License, or
 *  *     (at your option) any later version.
 *  *
 *  *     This program is distributed in the hope that it will be useful,
 *  *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  *     GNU General Public License for more details.
 *  *
 *  *     You should have received a copy of the GNU General Public License
 *  *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

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
