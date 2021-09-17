package vn.vnpay.process.consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import vn.vnpay.process.configuration.realoadable.ReloadablePropertySourceFactory;
import vn.vnpay.process.exception.CustomException;
import vn.vnpay.process.model.PaymentModel;
import vn.vnpay.process.service.PaymentService;
import vn.vnpay.process.util.CommonUtils;
import vn.vnpay.process.util.ResponsePreProcessor;
import vn.vnpay.netty.constant.State;
import vn.vnpay.netty.message.PaymentMessage;

/**
 * Project: demo-payment
 * Package: vn.vnpay.process.configuration
 * Author: zovivo
 * Date: 8/10/2021
 * Time: 4:11 PM
 * Created with IntelliJ IDEA
 */
@Component
@RefreshScope
@PropertySource(value = "${spring.rabbitmq-config.url}", factory = ReloadablePropertySourceFactory.class)
public class RabbitMQConsumer {

    private static final Logger logger = LogManager.getLogger(RabbitMQConsumer.class);

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ResponsePreProcessor responsePreProcessor;

    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public String receivedMessageAndReply(String paymentMessageStr, Message message) {
        PaymentMessage paymentMessage = CommonUtils.parseStringToObject(paymentMessageStr, PaymentMessage.class);
        PaymentModel paymentModel = CommonUtils.convertData(paymentMessage.getPayment(), PaymentModel.class);
        ThreadContext.put("tokenKey", paymentModel.getTokenKey());
        logger.info("received from queue: {} Message: {}", queue, message);
        try {
            paymentService.executePayment(paymentModel);
        } catch (CustomException e) {
            logger.warn("custom exception: ", e);
            paymentMessage.setState(State.FAIL);
        } catch (RuntimeException e) {
            logger.error("runtime exception: ", e);
            paymentMessage.setState(State.FAIL);
        }
        ThreadContext.remove("tokenKey");
        paymentMessage.setState(State.SUCCESS);
        String responseMessage = CommonUtils.parseObjectToString(paymentMessage);
        logger.info("return response: {}", responseMessage);
        return responseMessage;
    }

}
