package main.java.com.scheduler.factory;

import main.java.com.scheduler.enums.JobType;
import main.java.com.scheduler.handler.FixedDelayJobHandlerImpl;
import main.java.com.scheduler.handler.FixedRateJobHandlerImpl;
import main.java.com.scheduler.handler.JobHandler;
import main.java.com.scheduler.handler.OneTimeJobHandlerImpl;

import java.util.Objects;

public class JobHandlerFactory
{
    private JobHandlerFactory() {
    }

    private static JobHandlerFactory INSTANCE;

    public static JobHandlerFactory getInstance()
    {
        if (Objects.isNull(INSTANCE))
            INSTANCE = new JobHandlerFactory();

        return INSTANCE;
    }

    public JobHandler getHandler(JobType jobType)
    {
        switch (jobType)
        {
            case ONETIME -> {
                return OneTimeJobHandlerImpl.getINSTANCE();
            }
            case FIXED_RATE -> {
                return FixedRateJobHandlerImpl.getINSTANCE();
            }
            case FIXED_DELAY -> {
                return FixedDelayJobHandlerImpl.getINSTANCE();
            }
            default -> {
                throw new RuntimeException("Invalid job type");
            }
        }
    }
}
