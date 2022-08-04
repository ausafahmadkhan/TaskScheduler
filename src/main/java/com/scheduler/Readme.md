# LLD

## DbSchema

### Task

    execute();


### Job
    jobId
    jobName
    task
    jobType
    runAt
    fixedRate
    fixedDelay

### ExecutorService
    schedule(Job job, Long time);
    scheduleAtFixedInterval(Job job, Long interval);
    scheduleAtFixedIntervalWithDelay(Job job, Long interval, Long delay);

### TaskExecutor
    execute();
    addToPendingJobs(Job job);