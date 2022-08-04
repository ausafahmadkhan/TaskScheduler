package main.java.com.scheduler.service;

import main.java.com.scheduler.enums.JobType;
import main.java.com.scheduler.executor.JobExecutor;
import main.java.com.scheduler.executor.JobExecutorImpl;
import main.java.com.scheduler.model.Job;

import java.util.Objects;

public class ExecutorServiceImpl implements ExecutorService
{
    private static ExecutorServiceImpl INSTANCE;

    private final JobExecutor jobExecutor;

    public static ExecutorServiceImpl getINSTANCE(Integer poolSize)
    {
        if (Objects.isNull(INSTANCE))
            INSTANCE = new ExecutorServiceImpl(poolSize);

        return INSTANCE;
    }

    private ExecutorServiceImpl(Integer poolSize)
    {
        jobExecutor = new JobExecutorImpl(poolSize);
        //separate thread for poller
        Thread executorThread = new Thread(jobExecutor);
        executorThread.start();
    }

    @Override
    public void schedule(Job job, Long time)
    {
        job.setRunAt(time);
        job.setJobType(JobType.ONETIME);
        jobExecutor.addToPendingJobs(job);

    }

    @Override
    public void scheduleAtFixedInterval(Job job, Long fixedRate)
    {
        if (Objects.isNull(job.getRunAt()))
            job.setRunAt(System.currentTimeMillis());

        job.setFixedRate(fixedRate);
        job.setJobType(JobType.FIXED_RATE);
        jobExecutor.addToPendingJobs(job);
    }

    @Override
    public void scheduleAtFixedIntervalWithDelay(Job job, Long fixedDelay)
    {
        if (Objects.isNull(job.getRunAt()))
            job.setRunAt(System.currentTimeMillis());

        job.setJobType(JobType.FIXED_DELAY);
        job.setFixedDelay(fixedDelay);
        jobExecutor.addToPendingJobs(job);
    }
}
