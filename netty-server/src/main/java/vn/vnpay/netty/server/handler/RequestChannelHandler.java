package vn.vnpay.netty.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import vn.vnpay.netty.message.PaymentMessage;
import vn.vnpay.netty.message.TransactionMessage;
import vn.vnpay.netty.model.Transaction;
import vn.vnpay.netty.server.configuration.ServerConfig;
import vn.vnpay.netty.server.sender.QueueSender;
import vn.vnpay.netty.util.CommonUtils;
import vn.vnpay.netty.util.MessageUtils;

import java.util.UUID;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Project: netty-spring
 * Package: com.server.handler
 * Author: zovivo
 * Date: 9/15/2021
 * Created with IntelliJ IDEA
 */
@RequiredArgsConstructor
public class RequestChannelHandler extends SimpleChannelInboundHandler<byte[]> {

    private static final Logger logger = LogManager.getLogger(RequestChannelHandler.class);

    private final ThreadPoolExecutor threadPoolExecutor;
    private final QueueSender queueSender;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
        Channel channel = ctx.channel();
        if (null == channel || !channel.isActive() || !channel.isOpen()) {
            logger.debug("Channel is inactive or closed");
            return;
        }
        String message = CommonUtils.convertBytesToString(msg);
        TransactionMessage transactionMessage = MessageUtils.unpackMsg(msg);
        String randomRequestId = UUID.randomUUID().toString();
        ThreadContext.put(ServerConfig.LOG_TOKEN_KEY, randomRequestId);
        logger.info("Read transaction message : {}", CommonUtils.parseObjectToString(transactionMessage));
        Transaction transaction = new Transaction(transactionMessage);
        logger.info("Transaction : {}", CommonUtils.parseObjectToString(transaction));
        logger.info("Read from channel {} message : {}", channel.id().asLongText(), message);
        PaymentMessage paymentMessage = new PaymentMessage(transaction);
        paymentMessage.setChannelId(channel.id().asLongText());
        paymentMessage.setRequestId(randomRequestId);
        logger.info("Handle message by RequestHandler");
        threadPoolExecutor.execute(new RequestHandler(paymentMessage, queueSender));
    }
}
