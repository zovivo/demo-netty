package vn.vnpay.netty.server.configuration;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import vn.vnpay.netty.server.server.ServerConnector;

import java.net.InetSocketAddress;

/**
 * Project: netty-spring
 * Package: com.server.configuration
 * Author: zovivo
 * Date: 9/14/2021
 * Created with IntelliJ IDEA
 */

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(NettyProperties.class)
public class NettyConfiguration {

    private final NettyProperties nettyProperties;

    @Bean(name = "serverBootstrap")
    public ServerBootstrap bootstrap(@Lazy ServerConnector serverConnector) {
        ServerBootstrap b = new ServerBootstrap();
        EventLoopGroup bossGroup = bossGroup();
        EventLoopGroup workerGroup = workerGroup();
        Class<? extends ServerChannel> channelClass;
        if (Epoll.isAvailable())
            channelClass = EpollServerSocketChannel.class;
         else
            channelClass = NioServerSocketChannel.class;
        b.group(bossGroup, workerGroup)
                .channel(channelClass)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(ServerConfig.PIPELINE_SERVER_CONNECTOR_NAME, serverConnector);
                    }
                })
                .option(ChannelOption.SO_BACKLOG, nettyProperties.getBacklog())
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        return b;
    }

    @Bean(destroyMethod = "shutdownGracefully")
    public EventLoopGroup bossGroup() {
        EventLoopGroup bossGroup;
        if (Epoll.isAvailable())
            bossGroup = new EpollEventLoopGroup(nettyProperties.getBossCount());
        else
            bossGroup = new NioEventLoopGroup(nettyProperties.getBossCount());
        return bossGroup;
    }

    @Bean(destroyMethod = "shutdownGracefully")
    public EventLoopGroup workerGroup() {
        EventLoopGroup workerGroup;
        if (Epoll.isAvailable())
            workerGroup = new EpollEventLoopGroup(nettyProperties.getWorkerCount());
        else
            workerGroup = new NioEventLoopGroup(nettyProperties.getWorkerCount());
        return workerGroup;
    }

    @Bean
    public InetSocketAddress tcpSocketAddress() {
        return new InetSocketAddress(nettyProperties.getTcpPort());
    }

    @Bean("channelGroup")
    public ChannelGroup ChannelGroup() {
        return new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }

}
