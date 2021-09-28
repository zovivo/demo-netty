package vn.vnpay.netty.server.util;

import vn.vnpay.common.constant.State;
import vn.vnpay.common.message.TransactionMessageWrap;
import vn.vnpay.common.model.Transaction;
import vn.vnpay.common.util.CommonUtils;

/**
 * Project: demo-netty
 * Package: vn.vnpay.netty.server.util
 * Author: zovivo
 * Date: 9/28/2021
 * Created with IntelliJ IDEA
 */
public class MessageUtil {

    public static TransactionMessageWrap createTransactionMessageWrap(Transaction transaction, String requestId, String channelId, String transactionType) {
        TransactionMessageWrap transactionMessageWrap = new TransactionMessageWrap();
        transactionMessageWrap.setTransaction(transaction);
        transactionMessageWrap.setRequestId(requestId);
        transactionMessageWrap.setChannelId(channelId);
        transactionMessageWrap.setState(State.IN_PROGRESS);
        transactionMessageWrap.setTransactionType(transactionType);
        return transactionMessageWrap;
    }

    public static byte[] removeHeaderMessage(byte[] messageByte, int numChar) {
        String message = CommonUtils.convertBytesToString(messageByte);
        return CommonUtils.convertStringToBytes(message.substring(numChar));
    }

    public static String getHeaderMessage(byte[] messageByte, int numChar) {
        String message = CommonUtils.convertBytesToString(messageByte);
        return message.substring(0,numChar);
    }

    public static byte[] addHeaderMessage(byte[] messageByte, String header) {
        StringBuilder message = new StringBuilder(header);
        message.append(CommonUtils.convertBytesToString(messageByte));
        return CommonUtils.convertStringToBytes(message.toString());
    }

}
