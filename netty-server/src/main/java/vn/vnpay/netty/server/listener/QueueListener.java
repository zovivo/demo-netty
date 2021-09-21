package vn.vnpay.netty.server.listener;

import io.netty.channel.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.vnpay.netty.message.TransactionMessageWrap;
import vn.vnpay.netty.server.configuration.ServerConfig;
import vn.vnpay.netty.server.handler.ResponseHandler;
import vn.vnpay.netty.server.server.TCPServer;
import vn.vnpay.netty.util.CommonUtils;

import java.util.concurrent.ThreadPoolExecutor;


/**
 * Project: netty-spring
 * Package: com.server.listener
 * Author: zovivo
 * Date: 9/15/2021
 * Created with IntelliJ IDEA
 */

@Component
public class QueueListener {

    private static final Logger logger = LogManager.getLogger(QueueListener.class);

    @Autowired
    private TCPServer server;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Value("${spring.rabbitmq.reply-queue}")
    private String replyQueue;

    @RabbitListener(queues = "${spring.rabbitmq.reply-queue}")
    public void receivedResponseMessage(String transactionMsgWrapStr, Message message) {
        TransactionMessageWrap transactionMessageWrap = CommonUtils.parseStringToObject(transactionMsgWrapStr, TransactionMessageWrap.class);
        ThreadContext.put(ServerConfig.LOG_TOKEN_KEY, transactionMessageWrap.getMessage().getRequestId());
        logger.info("Received from queue: {} Message: {}", replyQueue, message);
        Channel channel = server.getChannel(transactionMessageWrap.getMessage().getChannelId());
        if (null == channel || !channel.isActive() || !channel.isOpen()) {
            logger.debug("Channel is inactive or closed");
            return;
        }
        logger.info("Response to client channel: {} Message: {}", channel.id().asLongText(), message);
        threadPoolExecutor.execute(new ResponseHandler(transactionMessageWrap, channel));
        ThreadContext.remove(ServerConfig.LOG_TOKEN_KEY);
        return;
    }


}
