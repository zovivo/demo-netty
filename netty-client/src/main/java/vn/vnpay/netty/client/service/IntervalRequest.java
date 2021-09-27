package vn.vnpay.netty.client.service;

import io.netty.channel.ChannelHandlerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jpos.iso.ISOException;
import vn.vnpay.common.util.MessageUtils;
import vn.vnpay.netty.client.handler.ClientHandler;

import java.nio.charset.StandardCharsets;

/**
 * Project: demo-netty
 * Package: vn.vnpay.netty.client.service
 * Author: zovivo
 * Date: 9/24/2021
 * Created with IntelliJ IDEA
 */

public class IntervalRequest implements Runnable {

    private static final Logger logger = LogManager.getLogger(IntervalRequest.class);

    private final ChannelHandlerContext ctx;
    private final String isoMessage;

    public IntervalRequest(ChannelHandlerContext ctx, String isoMessage) {
        this.ctx = ctx;
        this.isoMessage = isoMessage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            logger.info("Write to channel: {} message: {}", ctx.channel().id().asLongText(), isoMessage);
            byte[] data = new byte[0];
            try {
                data = MessageUtils.pack(isoMessage);
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ctx.writeAndFlush(data);
        }
    }
}
