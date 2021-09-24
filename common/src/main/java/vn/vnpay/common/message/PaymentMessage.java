package vn.vnpay.common.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.vnpay.common.model.Payment;
import vn.vnpay.common.model.Transaction;

/**
 * Project: netty-spring
 * Package: com.server.model
 * Author: zovivo
 * Date: 9/15/2021
 * Created with IntelliJ IDEA
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentMessage extends Message {

    private Payment payment;

    public PaymentMessage(Transaction transaction) {
        Payment payment = new Payment();
        payment.setRealAmount(transaction.getAmount());
        payment.setPayDate(transaction.getTransactionDate());
        payment.setAdditionalData(transaction.getAdditionalData());
        this.payment = payment;
    }

}
