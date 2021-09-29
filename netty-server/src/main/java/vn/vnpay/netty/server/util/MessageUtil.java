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

    /**
     * tạo object TransactionMessageWrap
     *
     * @param transaction     {@link Transaction}
     * @param requestId       {@link String}
     * @param channelId       {@link String}
     * @param transactionType {@link String}
     * @return transactionMessageWrap {@link TransactionMessageWrap}
     */
    public static TransactionMessageWrap createTransactionMessageWrap(Transaction transaction, String requestId, String channelId, String transactionType) {
        TransactionMessageWrap transactionMessageWrap = new TransactionMessageWrap();
        transactionMessageWrap.setTransaction(transaction);
        transactionMessageWrap.setRequestId(requestId);
        transactionMessageWrap.setChannelId(channelId);
        transactionMessageWrap.setState(State.IN_PROGRESS);
        transactionMessageWrap.setTransactionType(transactionType);
        return transactionMessageWrap;
    }

    /**
     * cắt header của message bytes
     *
     * @param messageByte {@link byte[]}
     * @param numChar     {@link int} - số ký tự cắt
     * @return bytes {@link byte[]}
     */
    public static byte[] removeHeaderMessage(byte[] messageByte, int numChar) {
        String message = CommonUtils.convertBytesToString(messageByte);
        return CommonUtils.convertStringToBytes(message.substring(numChar));
    }

    /**
     * lấy header của message bytes
     *
     * @param messageByte {@link byte[]}
     * @param numChar     {@link int} - số ký tự lấy
     * @return header {@link String}
     */
    public static String getHeaderMessage(byte[] messageByte, int numChar) {
        String message = CommonUtils.convertBytesToString(messageByte);
        return message.substring(0, numChar);
    }

    /**
     * thêm header vào message bytes
     *
     * @param messageByte {@link byte[]}
     * @param header      {@link String} - header field
     * @return messageByte {@link byte[]}
     */
    public static byte[] addHeaderMessage(byte[] messageByte, String header) {
        StringBuilder message = new StringBuilder(header);
        message.append(CommonUtils.convertBytesToString(messageByte));
        return CommonUtils.convertStringToBytes(message.toString());
    }

}
