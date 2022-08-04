package main.java.com.scheduler.executor;

import main.java.com.scheduler.model.Job;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class JobExecutorImpl implements JobExecutor
{
    private PriorityQueue<Job> pendingJobs;
    private ReentrantLock jobLock;
    private Condition jobAdded;
    private ExecutorService workerPool;

    public JobExecutorImpl(Integer poolSize)
    {
        Comparator<Job> jobComparator = Comparator.comparing(Job::getRunAt);
        pendingJobs = new PriorityQueue<>(jobComparator);
        jobLock = new ReentrantLock(true);
        jobAdded = jobLock.newCondition();
        workerPool = Executors.newFixedThreadPool(poolSize);
    }

    @Override
    public void run()
    {
        while (true)
        {
            jobLock.lock();
            try
            {
                if (!pendingJobs.isEmpty())
                {
                    Job job = pendingJobs.peek();
                    Long currentTime = System.currentTimeMillis();
                    if (currentTime >= job.getRunAt())
                    {
                        job = pendingJobs.remove();

                        workerPool.execute(job);
                    }
                    else
                    {
                        try {
                            jobAdded.await(job.getRunAt() - currentTime, TimeUnit.MILLISECONDS);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                else
                {
                    try {
                        jobAdded.await();
                    }
                    catch (InterruptedException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
            }
            finally {
                jobLock.unlock();
            }
        }

    }

    @Override
    public void addToPendingJobs(Job job)
    {
        jobLock.lock();
        try
        {
            pendingJobs.add(job);
            jobAdded.signal();
        }
        finally
        {
            jobLock.unlock();
        }
    }
}
