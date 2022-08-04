package main.java.com.scheduler.handler;

import main.java.com.scheduler.model.Job;
import main.java.com.scheduler.service.ExecutorServiceImpl;

import java.util.Objects;

public class FixedDelayJobHandlerImpl implements JobHandler
{
    private static FixedDelayJobHandlerImpl INSTANCE;

    private FixedDelayJobHandlerImpl() {
    }

    public static FixedDelayJobHandlerImpl getINSTANCE()
    {
        if (Objects.isNull(INSTANCE))
            INSTANCE = new FixedDelayJobHandlerImpl();

        return INSTANCE;
    }

    @Override
    public void handle(Job job)
    {
        job.getTask().execute();
        job.setRunAt(System.currentTimeMillis() + job.getFixedDelay());
        ExecutorServiceImpl.getINSTANCE(null).scheduleAtFixedIntervalWithDelay(job, job.getFixedDelay());
    }
}
