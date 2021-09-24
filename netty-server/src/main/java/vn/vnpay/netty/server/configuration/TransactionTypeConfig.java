package vn.vnpay.netty.server.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

/**
 * Project: demo-netty
 * Package: vn.vnpay.netty.server.configuration
 * Author: zovivo
 * Date: 9/24/2021
 * Created with IntelliJ IDEA
 */

@Configuration
public class TransactionTypeConfig {

    @Value("${spring.transaction-config.location}")
    private String path;

    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    public TransactionTypeComponent transactionTypeComponent() throws IOException {
        Resource resource = resourceLoader.getResource(path);
        return new TransactionTypeComponent(resource);
    }

}
