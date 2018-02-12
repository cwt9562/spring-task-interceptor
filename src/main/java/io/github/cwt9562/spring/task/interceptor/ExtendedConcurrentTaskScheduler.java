package io.github.cwt9562.spring.task.interceptor;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

/**
 * @author Chen Wentao
 * @date 2018/2/12
 */
public class ExtendedConcurrentTaskScheduler extends ConcurrentTaskScheduler {
    private TaskInterceptorRegister taskInterceptorRegister;

    public ExtendedConcurrentTaskScheduler(ScheduledExecutorService scheduledExecutor, TaskInterceptorRegister taskInterceptorRegister) {
        super(scheduledExecutor);
        this.taskInterceptorRegister = taskInterceptorRegister;
    }

    public ExtendedConcurrentTaskScheduler(Executor concurrentExecutor,
                                           ScheduledExecutorService scheduledExecutor) {
        super(concurrentExecutor, scheduledExecutor);
    }

    protected Runnable getDelegateTask(Runnable task) {
        return new ExtendedSpringTaskRunnable(task, taskInterceptorRegister.getInterceptors());
    }

    @Override
    public ScheduledFuture<?> schedule(Runnable task, Trigger trigger) {
        return super.schedule(this.getDelegateTask(task), trigger);
    }

    @Override
    public ScheduledFuture<?> schedule(Runnable task, Date startTime) {
        return super.schedule(this.getDelegateTask(task), startTime);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, Date startTime, long period) {
        return super.scheduleAtFixedRate(this.getDelegateTask(task), startTime, period);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long period) {
        return super.scheduleAtFixedRate(this.getDelegateTask(task), period);
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, Date startTime, long delay) {
        return super.scheduleWithFixedDelay(this.getDelegateTask(task), startTime, delay);
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long delay) {
        return super.scheduleWithFixedDelay(this.getDelegateTask(task), delay);
    }
}
