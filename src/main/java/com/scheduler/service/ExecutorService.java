package main.java.com.scheduler.service;

import main.java.com.scheduler.model.Job;

public interface ExecutorService
{
    void schedule(Job job, Long time);

    void scheduleAtFixedInterval(Job job, Long fixedRate);

    void scheduleAtFixedIntervalWithDelay(Job job, Long fixedDelay);
}
