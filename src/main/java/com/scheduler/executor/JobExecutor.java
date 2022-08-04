package main.java.com.scheduler.executor;

import main.java.com.scheduler.model.Job;

public interface JobExecutor extends Runnable
{
    void addToPendingJobs(Job job);
}
