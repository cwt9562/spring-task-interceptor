# spring-task-interceptor

针对Spring task的@Scheduled注解的拓展, 为其加上拦截器

## 拓展原理

Spring task底层使用ConcurrentTaskScheduler来执行每个任务, 而ConcurrentTaskScheduler内部每次都会新建一个Runnable对象.
拓展的原理便是针对Runnable对象写了一个代理类, 在代理类上z做文章

## 使用方式

写一个继承TaskInterceptor的Interceptor类.

```
public class MyInterceptor implements TaskInterceptor {

    @Override
    public boolean beforeExecute(TaskContext context) throws Exception {
        ...
        return true;
    }

    @Override
    public void afterCompletion(TaskContext context) throws Exception {
        ...
    }

    @Override
    public void throwException(TaskContext context, Throwable throwable) {
        ...
    }
}
```

配置Spring的@Configuration

```
@Configuration
@EnableScheduling
public class ScheduleConfig extends ExtendedSchedulingConfigurer {

    @Override
    protected List<TaskInterceptor> registerTaskInterceptor() {
        List<TaskInterceptor> interceptors = super.registerTaskInterceptor();
        interceptors.add(myInterceptor());
        return interceptors;
    }

    @Bean
    public MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }
}
```
