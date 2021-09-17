package vn.vnpay.netty.server.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Project: netty-spring
 * Package: com.server.configuration
 * Author: zovivo
 * Date: 9/15/2021
 * Created with IntelliJ IDEA
 */
@Configuration
public class ThreadPoolConfiguration {

    @Value("${spring.thread.corePoolSize}")
    private int corePoolSize;

    @Value("${spring.thread.maxPoolSize}")
    private int maximumPoolSize;

    @Value("${spring.thread.keep-alive}")
    private int alive;

    @Value("${spring.thread.queueCapacity}")
    private int queueCapacity;

    @Bean("threadPoolExecutor")
    public ThreadPoolExecutor executor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, alive, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueCapacity));
        return executor;

    }

}
