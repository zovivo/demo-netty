package vn.vnpay.netty.client.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Project: netty-spring
 * Package: com.server.configuration
 * Author: zovivo
 * Date: 9/14/2021
 * Created with IntelliJ IDEA
 */

@Configuration
public class NettyConfiguration {

    @Bean(name = "bootstrap")
    public Bootstrap bootstrap(EventLoopGroup workerGroup) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_REUSEADDR, true);
        return bootstrap;
    }

    @Bean(name = "workerGroup")
    public EventLoopGroup workerGroup() {
        return new NioEventLoopGroup();
    }


}
