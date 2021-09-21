package vn.vnpay.netty.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.vnpay.netty.model.Transaction;

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
public class TransactionMessageWrap {

    private Transaction transaction;
    private Message message;

}
