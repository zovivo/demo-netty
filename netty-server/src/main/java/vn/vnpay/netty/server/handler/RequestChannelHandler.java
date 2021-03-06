package vn.vnpay.netty.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import vn.vnpay.common.message.TransactionMessage;
import vn.vnpay.common.message.TransactionMessageWrap;
import vn.vnpay.common.model.Transaction;
import vn.vnpay.common.util.CommonUtils;
import vn.vnpay.netty.server.configuration.ServerConfig;
import vn.vnpay.netty.server.configuration.TransactionTypeComponent;
import vn.vnpay.netty.server.sender.QueueSender;
import vn.vnpay.netty.server.util.MessagePackager;
import vn.vnpay.netty.server.util.MessageUtil;

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
    private final TransactionTypeComponent transactionTypeComponent;
    private final MessagePackager messagePackager;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
        String requestId = CommonUtils.randomID();
        ThreadContext.put(ServerConfig.LOG_TOKEN_KEY, requestId);

        Channel channel = ctx.channel();
        logger.debug("Server receive new message from client IP: {}", channel.remoteAddress().toString());
        logger.info("Server received message bytes: [{}]", CommonUtils.convertBytesToString(msg));
        if (null == channel || !channel.isActive() || !channel.isOpen()) {
            logger.debug("Channel is inactive or closed");
            return;
        }
        logger.info("Start convert message");
        // cut the first 8 characters
        String headerMessage = MessageUtil.getHeaderMessage(msg,ServerConfig.NUM_HEADER_CHARACTERS_MESSAGE);
        msg = MessageUtil.removeHeaderMessage(msg, ServerConfig.NUM_HEADER_CHARACTERS_MESSAGE);
        TransactionMessage transactionMessage = messagePackager.unpack(msg);
        transactionMessage.setHeaderMessage(headerMessage);

        // business logic
        logger.info("Read transaction message : {}", CommonUtils.parseObjectToString(transactionMessage));
        Transaction transaction = new Transaction(transactionMessage);
        logger.info("Transaction : {}", CommonUtils.parseObjectToString(transaction));
        String transactionType = transactionTypeComponent.getTransactionType(transaction.getTransactionCode(), transactionMessage.getMessageType());
        logger.info("Transaction Type: {}", transactionType);
        TransactionMessageWrap transactionMessageWrap = MessageUtil.createTransactionMessageWrap(transaction, requestId, channel.id().asLongText(), transactionType);
        logger.info("Handle message by RequestHandler");

        threadPoolExecutor.execute(new RequestHandler(transactionMessageWrap, queueSender));
        ThreadContext.clearAll();
    }
}
