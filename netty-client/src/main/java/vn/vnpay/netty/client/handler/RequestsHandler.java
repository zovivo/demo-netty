package vn.vnpay.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.vnpay.common.message.PaymentMessage;
import vn.vnpay.common.model.Payment;
import vn.vnpay.common.util.CommonUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Project: demo-payment
 * Package: vn.vnpay.nettyclient.handler
 * Author: zovivo
 * Date: 9/16/2021
 * Created with IntelliJ IDEA
 */
@RequiredArgsConstructor
public class RequestsHandler extends SimpleChannelInboundHandler<byte[]> {

    private static final Logger logger = LogManager.getLogger(RequestsHandler.class);

    private final List<Payment> payments;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        List<String> requests = createRequestsMessage(payments);
        for (String message : requests) {
            ctx.writeAndFlush(message.getBytes(StandardCharsets.UTF_8));
            Thread.sleep(150);
        }
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
        String message = CommonUtils.convertBytesToString(msg);
        PaymentMessage paymentMessage = CommonUtils.parseStringToObject(message, PaymentMessage.class);
        logger.info("Read from channel {} Message: {}", ctx.channel().id().asLongText(), CommonUtils.parseObjectToString(paymentMessage));
//        ctx.close();
    }

    private List<String> createRequestsMessage(List<Payment> payments) {
        List<String> requests = new ArrayList<>();
        for (Payment payment : payments) {
            String requestId = UUID.randomUUID().toString();
            PaymentMessage paymentMessage = new PaymentMessage(payment);
            paymentMessage.setRequestId(requestId);
            requests.add(CommonUtils.parseObjectToString(paymentMessage));
        }
        return requests;
    }

}
