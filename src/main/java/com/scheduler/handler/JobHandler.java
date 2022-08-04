package main.java.com.scheduler.handler;

import main.java.com.scheduler.model.Job;

public interface JobHandler
{
    void handle(Job job);
}
