package io.github.cwt9562.spring.task.interceptor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Chen Wentao
 * @date 2018/2/12
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestApplication.class)
public class LogInterceptorTest {

    @Autowired
    private SayHelloTask task;

    @Test
    public void test() {
        for (int i = 0; i < 10 && task.getCount() <= 0; i++) {
            try {
                Thread.currentThread().join(2000);
            } catch (InterruptedException ignore) {

            }
        }
    }
}
