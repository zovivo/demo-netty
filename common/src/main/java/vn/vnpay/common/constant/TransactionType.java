package vn.vnpay.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Project: demo-netty
 * Package: vn.vnpay.netty.constant
 * Author: zovivo
 * Date: 9/23/2021
 * Created with IntelliJ IDEA
 */

@Getter
@AllArgsConstructor
public enum TransactionType {

    SIGN_ON(""),
    DEDUCTION_ORDER(""),
    REVERSE_TRANSACTION_DEDUCTION_ORDER(""),
    LOOKUP_BENEFICIARY_INFORMATION(""),
    BENEFICIARY_CONFIRM(""),
    ;

    private final String value;
}
