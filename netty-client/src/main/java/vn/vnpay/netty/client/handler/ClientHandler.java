package vn.vnpay.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import vn.vnpay.netty.client.constant.ClientConstant;
import vn.vnpay.netty.message.PaymentMessage;
import vn.vnpay.netty.model.Payment;
import vn.vnpay.netty.util.CommonUtils;
import vn.vnpay.netty.util.MessageUtils;

import java.util.UUID;

/**
 * Project: netty-spring
 * Package: com.client.handler
 * Author: zovivo
 * Date: 9/15/2021
 * Created with IntelliJ IDEA
 */
@RequiredArgsConstructor
public class ClientHandler extends SimpleChannelInboundHandler<byte[]> {

    private static final Logger logger = LogManager.getLogger(ClientHandler.class);

    private final Payment payment;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String requestId = UUID.randomUUID().toString();
        PaymentMessage paymentMessage = new PaymentMessage(payment);
        paymentMessage.setRequestId(requestId);
        ThreadContext.put(ClientConstant.LOG_TOKEN_KEY, paymentMessage.getRequestId());
        String message = CommonUtils.parseObjectToString(paymentMessage);
        logger.info("Write to channel: {} message: {}", ctx.channel().id().asLongText(), CommonUtils.parseObjectToString(paymentMessage));
        byte[] data = MessageUtils.pack(paymentMessage);
        logger.info("data: {}", data);
        ctx.writeAndFlush(data);
        ThreadContext.clearMap();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
        String message = CommonUtils.convertBytesToString(msg);
        PaymentMessage paymentMessage = CommonUtils.parseStringToObject(message, PaymentMessage.class);
        ThreadContext.put(ClientConstant.LOG_TOKEN_KEY, paymentMessage.getRequestId());
        logger.info("Read from channel: {} message: {}", ctx.channel().id().asLongText(), CommonUtils.parseObjectToString(paymentMessage));
        ctx.close();
        ThreadContext.remove(ClientConstant.LOG_TOKEN_KEY);
    }

}
