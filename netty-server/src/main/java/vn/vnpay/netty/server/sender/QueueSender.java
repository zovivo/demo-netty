package vn.vnpay.netty.server.sender;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.vnpay.common.message.TransactionMessageWrap;
import vn.vnpay.common.util.CommonUtils;

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

    @Value("${spring.rabbitmq.routingKey}")
    private String routingKey;

    @Value("${spring.rabbitmq.replyQueue}")
    private String replyQueue;

    public void send2Queue(TransactionMessageWrap transactionMessageWrap) {
        String data = CommonUtils.parseObjectToString(transactionMessageWrap);
        logger.info("Begin send to exchange {} message: {}", exchange, data);
        transactionMessageWrap = CommonUtils.parseStringToObject(data, TransactionMessageWrap.class);
        rabbitTemplate.convertAndSend(exchange, routingKey, data, message -> {
            message.getMessageProperties().setReplyTo(replyQueue);
            return message;
        });
        logger.info("Send message: {}", CommonUtils.parseObjectToString(transactionMessageWrap));
    }

}
