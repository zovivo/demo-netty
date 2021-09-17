package vn.vnpay.netty.server.handler;

import io.netty.channel.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import vn.vnpay.netty.message.PaymentMessage;
import vn.vnpay.netty.server.configuration.ServerConfig;
import vn.vnpay.netty.util.CommonUtils;

import java.nio.charset.StandardCharsets;

/**
 * Project: netty-spring
 * Package: com.server.handler
 * Author: zovivo
 * Date: 9/15/2021
 * Created with IntelliJ IDEA
 */
public class ResponseHandler implements Runnable {

    private static final Logger logger = LogManager.getLogger(RequestHandler.class);

    private final PaymentMessage paymentMessage;
    private final Channel channel;

    public ResponseHandler(PaymentMessage msg, Channel channel) {
        this.paymentMessage = msg;
        this.channel = channel;
    }

    @Override
    public void run() {
        ThreadContext.put(ServerConfig.LOG_TOKEN_KEY, paymentMessage.getRequestId());
        logger.info("Begin write response");
        String message = CommonUtils.parseObjectToString(paymentMessage);
        channel.writeAndFlush(message.getBytes(StandardCharsets.UTF_8));
        logger.info("End write response");
        ThreadContext.clearAll();
    }
}
