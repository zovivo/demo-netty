package vn.vnpay.netty.server.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import vn.vnpay.netty.message.PaymentMessage;
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

    private final PaymentMessage paymentMessage;
    private final QueueSender queueSender;

    public RequestHandler(PaymentMessage msg, QueueSender queueSender) {
        this.paymentMessage = msg;
        this.queueSender = queueSender;
    }

    @Override
    public void run() {
        ThreadContext.put(ServerConfig.LOG_TOKEN_KEY, paymentMessage.getRequestId());
        logger.info("Begin send queue");
        queueSender.send2Queue(paymentMessage);
        logger.info("End send queue");
        ThreadContext.clearAll();
    }
}
