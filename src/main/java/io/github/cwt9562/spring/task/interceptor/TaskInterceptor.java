package io.github.cwt9562.spring.task.interceptor;

/**
 * @author Chen Wentao
 * @date 2018/2/12
 */
public interface TaskInterceptor {

    boolean beforeExecute(TaskContext context) throws Exception;

    void afterCompletion(TaskContext context) throws Exception;

    void throwException(TaskContext context, Throwable throwable);

}
