package main.java.com.scheduler.handler;

import main.java.com.scheduler.model.Job;

import java.util.Objects;

public class OneTimeJobHandlerImpl implements JobHandler
{
    private static OneTimeJobHandlerImpl INSTANCE;

    private OneTimeJobHandlerImpl() {
    }

    public static OneTimeJobHandlerImpl getINSTANCE()
    {
        if (Objects.isNull(INSTANCE))
            INSTANCE = new OneTimeJobHandlerImpl();

        return INSTANCE;
    }

    @Override
    public void handle(Job job)
    {
        job.getTask().execute();
    }
}
