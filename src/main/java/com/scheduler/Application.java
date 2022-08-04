package main.java.com.scheduler;

import main.java.com.scheduler.model.Job;
import main.java.com.scheduler.model.Task;
import main.java.com.scheduler.service.ExecutorService;
import main.java.com.scheduler.service.ExecutorServiceImpl;

import java.util.UUID;

public class Application
{
    public static void main(String[] args)
    {
        ExecutorService executorService = ExecutorServiceImpl.getINSTANCE(Runtime.getRuntime().availableProcessors() - 1);
        Task t1 = () -> System.out.println("Running task 1!");
        Job j1 = new Job(UUID.randomUUID().toString(), "Job 1", t1);
        executorService.schedule(j1, System.currentTimeMillis() + 10 * 1000);


        Task t2 = () -> System.out.println("Running task 2!");
        Job j2 = new Job(UUID.randomUUID().toString(), "Job 2", t2);
        executorService.scheduleAtFixedInterval(j2, 2 * 1000L);

        Task t3 = () ->
        {
            try {
                Thread.sleep(10 * 1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Running task 3!");
        };
        Job j3 = new Job(UUID.randomUUID().toString(), "Job 3", t3);
        executorService.scheduleAtFixedIntervalWithDelay(j3, 5 * 1000L);

    }
}
