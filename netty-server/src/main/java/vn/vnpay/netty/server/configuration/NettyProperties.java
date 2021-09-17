package vn.vnpay.netty.server.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Project: netty-spring
 * Package: com.server.configuration
 * Author: zovivo
 * Date: 9/14/2021
 * Created with IntelliJ IDEA
 */

@Getter
@Setter
@ConfigurationProperties(prefix = "netty")
public class NettyProperties {

    @NotNull
    @Size(min = 1000, max = 65535)
    private int tcpPort;

    @NotNull
    @Min(1)
    private int bossCount;

    @NotNull
    @Min(2)
    private int workerCount;

    @NotNull
    private boolean keepAlive;

    @NotNull
    private int backlog;

}
