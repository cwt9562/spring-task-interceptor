package io.github.cwt9562.spring.task.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Chen Wentao
 * @date 2018/2/12
 */
@Component
public class SayHelloTask {
    private static final Logger log = LoggerFactory.getLogger(SayHelloTask.class);

    private Integer count = 0;

    @Scheduled(cron = "*/5 * * * * * ")
    public void sayHello() throws Exception {
        log.info("start sayHello");
        Thread.currentThread().join(2000);
        log.info("finish sayHello");
        count++;
    }

    public Integer getCount() {
        return count;
    }
}
