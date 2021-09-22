package vn.vnpay.netty.server.handler;

import io.netty.channel.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.jpos.iso.ISOException;
import vn.vnpay.netty.message.TransactionMessageWrap;
import vn.vnpay.netty.server.configuration.ServerConfig;
import vn.vnpay.netty.util.CommonUtils;
import vn.vnpay.netty.util.MessageUtils;

/**
 * Project: netty-spring
 * Package: com.server.handler
 * Author: zovivo
 * Date: 9/15/2021
 * Created with IntelliJ IDEA
 */
public class ResponseHandler implements Runnable {

    private static final Logger logger = LogManager.getLogger(RequestHandler.class);

    private final TransactionMessageWrap transactionMessageWrap;
    private final Channel channel;

    public ResponseHandler(TransactionMessageWrap msgWrap, Channel channel) {
        this.transactionMessageWrap = msgWrap;
        this.channel = channel;
    }

    @Override
    public void run() {
        ThreadContext.put(ServerConfig.LOG_TOKEN_KEY, transactionMessageWrap.getMessage().getRequestId());
        logger.info("Begin write response to channel");
        if (null == channel || !channel.isActive() || !channel.isOpen()) {
            logger.debug("Channel is inactive or closed");
            return;
        }
        if (!channel.isWritable()) {
            logger.debug("Channel is not writable");
            return;
        }
        byte[] response = new byte[0];
        try {
            response = MessageUtils.packMsg(transactionMessageWrap);
        } catch (ISOException e) {
            logger.debug("Packing fail: {}", e);
        }
        channel.writeAndFlush(response);
        logger.info("End write response to channel");
        ThreadContext.clearAll();
    }
}
