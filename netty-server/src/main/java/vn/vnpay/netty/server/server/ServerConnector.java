package vn.vnpay.netty.server.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import vn.vnpay.netty.server.configuration.TransactionTypeComponent;
import vn.vnpay.netty.server.handler.RequestChannelHandler;
import vn.vnpay.netty.server.sender.QueueSender;
import vn.vnpay.netty.server.util.MessagePackager;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Project: netty-spring
 * Package: com.server.server
 * Author: zovivo
 * Date: 9/14/2021
 * Created with IntelliJ IDEA
 */

@Component
@ChannelHandler.Sharable
public class ServerConnector extends ChannelInboundHandlerAdapter {

    private static final Logger log = LogManager.getLogger(ServerConnector.class);

    private final TCPServer server;
    private final ThreadPoolExecutor threadPoolExecutor;
    private final QueueSender queueSender;
    private final TransactionTypeComponent transactionTypeComponent;
    private final MessagePackager messagePackager;

    public ServerConnector(@Lazy TCPServer server, ThreadPoolExecutor threadPoolExecutor, QueueSender queueSender, TransactionTypeComponent transactionTypeComponent, MessagePackager messagePackager) {
        this.server = server;
        this.threadPoolExecutor = threadPoolExecutor;
        this.queueSender = queueSender;
        this.transactionTypeComponent = transactionTypeComponent;
        this.messagePackager = messagePackager;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().config().setAutoRead(true);
        Channel channel = ctx.channel();
        server.addChannel(channel);
        log.debug("Server receive new connection from client IP: {}", channel.remoteAddress().toString());
        this.server.getCounter().incrementChannelConnectsAndGet();
        log.debug("Server accept new connection. Counter: {}.", this.server.getCounter().toString());
        channel.pipeline().addLast("bytesDecoder",
                new ByteArrayDecoder());

        // Encoder
        channel.pipeline().addLast("bytesEncoder", new ByteArrayEncoder());

        //add business logic handler
        channel.pipeline().addLast("requestChannelHandler", new RequestChannelHandler(threadPoolExecutor, queueSender, transactionTypeComponent, messagePackager));
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        this.server.removeChannel(ctx.channel());
        this.server.getCounter().incrementChannelDisconnectsAndGet();
        log.debug("Server Inactive connections. Counter: {}", this.server.getCounter().toString());
        super.channelInactive(ctx);
    }
}