package io.github.cwt9562.spring.task.interceptor;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Chen Wentao
 * @date 2018/2/12
 */
@ComponentScan
public class TestApplication extends ExtendedSchedulingConfigurer {

    @Override
    protected List<TaskInterceptor> registerTaskInterceptor() {
        List<TaskInterceptor> interceptors = super.registerTaskInterceptor();
        interceptors.add(logInterceptor());
        return interceptors;
    }

    @Bean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }

}
