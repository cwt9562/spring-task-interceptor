package io.github.cwt9562.spring.task.interceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Chen Wentao
 * @date 2018/2/12
 */
public class TaskContext {

    private Class<?> clazz;
    private Method method;
    private Map<String, Object> map = new HashMap<>();

    public Class<?> getClazz() {
        return clazz;
    }

    public TaskContext setClazz(Class<?> clazz) {
        this.clazz = clazz;
        return this;
    }

    public Method getMethod() {
        return method;
    }

    public TaskContext setMethod(Method method) {
        this.method = method;
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) map.get(key);
    }

    public <T> void put(String key, T value) {
        map.put(key, value);
    }
}
