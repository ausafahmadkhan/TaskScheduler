package main.java.com.scheduler.handler;

import main.java.com.scheduler.model.Job;
import main.java.com.scheduler.service.ExecutorServiceImpl;

import java.util.Objects;

public class FixedRateJobHandlerImpl implements JobHandler
{
    private static FixedRateJobHandlerImpl INSTANCE;

    private FixedRateJobHandlerImpl() {
    }

    public static  FixedRateJobHandlerImpl getINSTANCE()
    {
        if (Objects.isNull(INSTANCE))
            INSTANCE = new FixedRateJobHandlerImpl();

        return INSTANCE;
    }

    @Override
    public void handle(Job job)
    {
        job.setRunAt(job.getRunAt() + job.getFixedRate());
        ExecutorServiceImpl.getINSTANCE(null).scheduleAtFixedInterval(job, job.getFixedRate());
        job.getTask().execute();
    }
}
