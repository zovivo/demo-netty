package vn.vnpay.netty.server.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import vn.vnpay.netty.message.TransactionMessageWrap;
import vn.vnpay.netty.server.configuration.ServerConfig;
import vn.vnpay.netty.server.sender.QueueSender;

/**
 * Project: netty-spring
 * Package: com.server.model
 * Author: zovivo
 * Date: 9/15/2021
 * Created with IntelliJ IDEA
 */
public class RequestHandler implements Runnable {

    private static final Logger logger = LogManager.getLogger(RequestHandler.class);

    private final TransactionMessageWrap transactionMessageWrap;
    private final QueueSender queueSender;

    public RequestHandler(TransactionMessageWrap msgWrap, QueueSender queueSender) {
        this.transactionMessageWrap = msgWrap;
        this.queueSender = queueSender;
    }

    @Override
    public void run() {
        ThreadContext.put(ServerConfig.LOG_TOKEN_KEY, transactionMessageWrap.getMessage().getRequestId());
        logger.info("Begin send queue");
        queueSender.send2Queue(transactionMessageWrap);
        logger.info("End send queue");
        ThreadContext.clearAll();
    }
}
