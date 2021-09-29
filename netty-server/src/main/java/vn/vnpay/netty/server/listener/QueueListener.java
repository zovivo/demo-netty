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
import vn.vnpay.common.message.TransactionMessageWrap;
import vn.vnpay.common.util.CommonUtils;
import vn.vnpay.netty.server.configuration.ServerConfig;
import vn.vnpay.netty.server.handler.ResponseHandler;
import vn.vnpay.netty.server.server.TCPServer;
import vn.vnpay.netty.server.util.MessagePackager;

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

    @Autowired
    private MessagePackager messagePackager;

    @Value("${spring.rabbitmq.replyQueue}")
    private String replyQueue;

    @RabbitListener(queues = "${spring.rabbitmq.reply-queue}")
    public void receivedResponseMessage(String transactionMsgWrapStr, Message message) {
        logger.info("Received from queue: {} Message: {}", replyQueue, message);
        logger.info("Start convert message");
        TransactionMessageWrap transactionMessageWrap = CommonUtils.parseStringToObject(transactionMsgWrapStr, TransactionMessageWrap.class);
        if (null == transactionMessageWrap){
            logger.info("Convert message failed");
            return;
        }
        ThreadContext.put(ServerConfig.LOG_TOKEN_KEY, transactionMessageWrap.getRequestId());
        logger.info("End convert message");
        logger.info("Start get channel");
        Channel channel = null;
        try {
            channel = server.getChannel(transactionMessageWrap.getChannelId());
        } catch (NullPointerException e) {
            logger.debug("Client has been disconnected", e);
            return;
        }
        if (null == channel || !channel.isActive() || !channel.isOpen()) {
            logger.debug("Channel is inactive or closed");
            return;
        }
        logger.info("End get channel");
        logger.info("Response to client channel: {} Message: {}", channel.id().asLongText(), message);
        ThreadContext.remove(ServerConfig.LOG_TOKEN_KEY);
        threadPoolExecutor.execute(new ResponseHandler(transactionMessageWrap, channel, messagePackager));
        return;
    }


}
