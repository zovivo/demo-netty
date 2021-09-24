package vn.vnpay.process.consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.vnpay.common.constant.State;
import vn.vnpay.common.message.PaymentMessage;
import vn.vnpay.common.message.TransactionMessageWrap;
import vn.vnpay.common.model.Transaction;
import vn.vnpay.process.exception.CustomException;
import vn.vnpay.process.model.PaymentModel;
import vn.vnpay.process.service.PaymentService;
import vn.vnpay.process.util.CommonUtils;

/**
 * Project: demo-payment
 * Package: vn.vnpay.process.configuration
 * Author: zovivo
 * Date: 8/10/2021
 * Time: 4:11 PM
 * Created with IntelliJ IDEA
 */
@Component
public class RabbitMQConsumer {

    private static final Logger logger = LogManager.getLogger(RabbitMQConsumer.class);

    @Autowired
    private PaymentService paymentService;

    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public String receivedMessageAndReply(String transactionMsgWrapStr, Message message) {
        TransactionMessageWrap transactionMessageWrap = CommonUtils.parseStringToObject(transactionMsgWrapStr, TransactionMessageWrap.class);
        Transaction transaction = transactionMessageWrap.getTransaction();
        PaymentMessage paymentMessage = new PaymentMessage(transaction);
        PaymentModel paymentModel = CommonUtils.convertData(paymentMessage.getPayment(), PaymentModel.class);
        ThreadContext.put("tokenKey", transactionMessageWrap.getRequestId());
        logger.info("received from queue: {} Message: {}", queue, message);
        try {
            paymentService.executePayment(paymentModel);
        } catch (CustomException e) {
            logger.warn("custom exception: ", e);
            transactionMessageWrap.setState(State.FAIL);
        } catch (RuntimeException e) {
            logger.error("runtime exception: ", e);
            transactionMessageWrap.setState(State.FAIL);
        }
        transactionMessageWrap.setState(State.SUCCESS);
        String responseMessage = CommonUtils.parseObjectToString(transactionMessageWrap);
        logger.info("return response: {}", responseMessage);
        ThreadContext.remove("tokenKey");
        return responseMessage;
    }

}
