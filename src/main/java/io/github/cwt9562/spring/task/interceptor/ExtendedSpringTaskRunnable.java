package io.github.cwt9562.spring.task.interceptor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.util.Assert;

/**
 * @author Chen Wentao
 * @date 2018/2/12
 */
public class ExtendedSpringTaskRunnable implements Runnable {

    private final Runnable delegate;
    private final List<TaskInterceptor> interceptors = new ArrayList<>();

    public ExtendedSpringTaskRunnable(Runnable delegate, List<TaskInterceptor> interceptors) {
        Assert.notNull(delegate, "");
        Assert.notNull(interceptors, "");
        this.delegate = delegate;
        this.interceptors.addAll(interceptors);
    }

    @Override
    public void run() {
        TaskContext context = new TaskContext();
        if (delegate instanceof ScheduledMethodRunnable) {
            ScheduledMethodRunnable scheduledMethodRunnable = (ScheduledMethodRunnable) delegate;
            context.setClazz(scheduledMethodRunnable.getTarget().getClass());
            context.setMethod(scheduledMethodRunnable.getMethod());
        }
        int i = 0;
        try {
            for (; i < interceptors.size(); i++) {
                TaskInterceptor interceptor = interceptors.get(i);
                interceptor.beforeExecute(context);
            }

            this.delegate.run();

            if (i == interceptors.size()) {
                i = interceptors.size() - 1;
            }
            for (; i >= 0; i--) {
                TaskInterceptor interceptor = interceptors.get(i);
                interceptor.afterCompletion(context);
            }
        } catch (Exception ex) {
            if (i == interceptors.size()) {
                i = interceptors.size() - 1;
            }
            for (; i >= 0; i--) {
                TaskInterceptor interceptor = interceptors.get(i);
                interceptor.throwException(context, ex);
            }

        }
    }

}
