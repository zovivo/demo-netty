package vn.vnpay.common.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.vnpay.common.constant.State;
import vn.vnpay.common.model.Transaction;

import java.io.Serializable;

/**
 * Project: demo-netty
 * Package: vn.vnpay.netty.message
 * Author: zovivo
 * Date: 9/21/2021
 * Created with IntelliJ IDEA
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionMessageWrap implements Serializable {

    private Transaction transaction;
    private String requestId;
    private String channelId;
    private State state;
    private String transactionType;

}
