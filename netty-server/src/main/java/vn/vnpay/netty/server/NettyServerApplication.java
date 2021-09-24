package vn.vnpay.netty.server;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import vn.vnpay.netty.server.server.TCPServer;

@RequiredArgsConstructor
@SpringBootApplication
@EnableScheduling
public class NettyServerApplication {

    private final TCPServer tcpServer;

    public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication.class, args);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener() {
        return new ApplicationListener<ApplicationReadyEvent>() {
            @Override
            public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
                tcpServer.start();
            }
        };
    }

}
