package io.github.cwt9562.spring.task.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Chen Wentao
 * @date 2018/2/12
 */
public class LogInterceptor implements TaskInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public boolean beforeExecute(TaskContext context) throws Exception {
        log.info("start execute {}#{}", context.getClazz().getSimpleName(), context.getMethod().getName());
        return true;
    }

    @Override
    public void afterCompletion(TaskContext context) throws Exception {
        log.info("finish execute {}#{}", context.getClazz().getSimpleName(), context.getMethod().getName());
    }

    @Override
    public void throwException(TaskContext context, Throwable throwable) {
        log.error("error occur when execute {}#{}, error message: {}.", context.getClazz().getSimpleName(), context.getMethod().getName(), throwable.getMessage(), throwable);
    }
}
