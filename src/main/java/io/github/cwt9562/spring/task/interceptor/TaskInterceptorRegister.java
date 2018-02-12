package io.github.cwt9562.spring.task.interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Wentao
 * @date 2018/2/12
 */
public class TaskInterceptorRegister {
    private final List<TaskInterceptor> interceptors = new ArrayList<>();

    public void addInterceptor(TaskInterceptor interceptor) {
        interceptors.add(interceptor);
    }

    protected List<TaskInterceptor> getInterceptors() {
        return new ArrayList<>(interceptors);
    }
}
