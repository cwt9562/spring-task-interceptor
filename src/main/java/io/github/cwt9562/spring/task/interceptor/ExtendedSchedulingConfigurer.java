package io.github.cwt9562.spring.task.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * @author Chen Wentao
 * @date 2018/2/12
 */
@EnableScheduling
public class ExtendedSchedulingConfigurer implements SchedulingConfigurer {

    public static final String BEAN_NAME_TASK_INTERCEPTOR_REGISTER = "taskInterceptorRegister";
    public static final String BEAN_NAME_CONCURRENT_TASK_SCHEDULER = "concurrentTaskScheduler";

    @Bean(name = BEAN_NAME_TASK_INTERCEPTOR_REGISTER)
    public TaskInterceptorRegister taskInterceptorRegister() {
        TaskInterceptorRegister taskInterceptorRegister = new TaskInterceptorRegister();
        List<TaskInterceptor> interceptors = registerTaskInterceptor();
        if (interceptors != null && interceptors.size() > 0) {
            for (TaskInterceptor interceptor : interceptors) {
                taskInterceptorRegister.addInterceptor(interceptor);
            }
        }
        return taskInterceptorRegister;
    }

    protected List<TaskInterceptor> registerTaskInterceptor() {
        return new ArrayList<>();
    }

    @Bean(name = BEAN_NAME_CONCURRENT_TASK_SCHEDULER)
    public ConcurrentTaskScheduler concurrentTaskScheduler() {
        // see: org.springframework.scheduling.config.ScheduledTaskRegistrar.scheduleTasks()
        return new ExtendedConcurrentTaskScheduler(Executors.newSingleThreadScheduledExecutor(), taskInterceptorRegister());
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(concurrentTaskScheduler());
    }
}
