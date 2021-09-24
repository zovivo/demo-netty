package vn.vnpay.netty.server.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Project: netty-spring
 * Package: com.server.server
 * Author: zovivo
 * Date: 9/14/2021
 * Created with IntelliJ IDEA
 */

@RequiredArgsConstructor
@Component
@Getter
public class TCPServer {

    private static final Logger log = LogManager.getLogger(TCPServer.class);

    private final ServerBootstrap serverBootstrap;

    private final InetSocketAddress tcpPort;

    private final ChannelGroup channelGroup;

    private final ServerCounter counter;

    private Channel serverChannel;

    private ConcurrentMap<String, ChannelId> channelCache = new ConcurrentHashMap<>();

    public void start() {
        try {
            ChannelFuture serverChannelFuture = serverBootstrap.bind(tcpPort).sync();
            log.info("Server is started : port {}", tcpPort.getPort());
            serverChannel = serverChannelFuture.channel().closeFuture().sync().channel();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @PreDestroy
    public void stop() {
        if (serverChannel != null) {
            serverChannel.close();
            serverChannel.parent().close();
        }
    }

    public Channel addChannel(Channel channel) {
        channelGroup.add(channel);
        channelCache.put(channel.id().asLongText(), channel.id());
        return null;
    }

    public Channel removeChannel(Channel channel) {
        channelGroup.remove(channel);
        channelCache.remove(channel.id().asLongText());
        return null;
    }

    public Channel getChannel(String longTextId) {
        return channelGroup.find(channelCache.get(longTextId));
    }

}
