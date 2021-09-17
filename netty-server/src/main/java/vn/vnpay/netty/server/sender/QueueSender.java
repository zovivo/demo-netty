package vn.vnpay.netty.server.sender;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.vnpay.netty.message.PaymentMessage;
import vn.vnpay.netty.util.CommonUtils;

/**
 * Project: netty-spring
 * Package: com.server.sender
 * Author: zovivo
 * Date: 9/15/2021
 * Created with IntelliJ IDEA
 */
@Component
@RequiredArgsConstructor
public class QueueSender {

    private static final Logger logger = LogManager.getLogger(QueueSender.class);

    private final RabbitTemplate rabbitTemplate;


    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingKey;

    @Value("${spring.rabbitmq.reply-queue}")
    private String replyQueue;

    public void send2Queue(PaymentMessage paymentMessage) {
        String data = CommonUtils.parseObjectToString(paymentMessage);
        logger.info("Begin send to exchange {} message: {}", exchange, CommonUtils.parseObjectToString(paymentMessage));
        rabbitTemplate.convertAndSend(exchange, routingKey, data, message -> {
            message.getMessageProperties().setReplyTo(replyQueue);
            return message;
        });
        logger.info("Send message: {}", CommonUtils.parseObjectToString(paymentMessage));
    }

}