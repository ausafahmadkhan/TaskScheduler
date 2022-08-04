package main.java.com.scheduler.model;

import main.java.com.scheduler.enums.JobType;
import main.java.com.scheduler.factory.JobHandlerFactory;

public class Job implements Runnable
{
    private String jobId;
    private String jobName;
    private Long runAt;
    private Task task;
    private Long fixedRate;
    private Long fixedDelay;
    private JobType jobType;

    public Long getFixedRate() {
        return fixedRate;
    }

    public void setFixedRate(Long fixedRate) {
        this.fixedRate = fixedRate;
    }

    public Long getFixedDelay() {
        return fixedDelay;
    }

    public void setFixedDelay(Long fixedDelay) {
        this.fixedDelay = fixedDelay;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public Job(String jobId, String jobName, Task task)
    {
        this.jobId = jobId;
        this.jobName = jobName;
        this.task = task;
    }

    public String getJobId()
    {
        return jobId;
    }

    public void setJobId(String jobId)
    {
        this.jobId = jobId;
    }

    public String getJobName()
    {
        return jobName;
    }

    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    public Long getRunAt()
    {
        return runAt;
    }

    public void setRunAt(Long runAt)
    {
        this.runAt = runAt;
    }

    public Task getTask()
    {
        return task;
    }

    public void setTask(Task task)
    {
        this.task = task;
    }

    @Override
    public void run()
    {
        JobHandlerFactory.getInstance().getHandler(this.getJobType()).handle(this);
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId='" + jobId + '\'' +
                ", jobName='" + jobName + '\'' +
                ", runAt=" + runAt +
                ", fixedRate=" + fixedRate +
                ", fixedDelay=" + fixedDelay +
                ", jobType=" + jobType +
                '}';
    }
}
