package vn.vnpay.netty.server.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;
import vn.vnpay.common.constant.State;
import vn.vnpay.common.message.TransactionMessage;
import vn.vnpay.common.message.TransactionMessageWrap;
import vn.vnpay.common.model.Transaction;

/**
 * Project: demo-netty
 * Package: vn.vnpay.netty.util
 * Author: zovivo
 * Date: 9/17/2021
 * Created with IntelliJ IDEA
 */


public class MessagePackager {

    private static final Logger logger = LogManager.getLogger(MessagePackager.class);

    private final GenericPackager packager;

    public MessagePackager(GenericPackager packager) {
        this.packager = packager;
    }

    public byte[] pack(TransactionMessageWrap transactionMessageWrap) throws ISOException {
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(packager);
        isoMsg.setMTI("0200");
        isoMsg.set(2, transactionMessageWrap.getTransaction().getCardNumber());
        isoMsg.set(3, transactionMessageWrap.getTransaction().getProcessingCode());
        isoMsg.set(4, transactionMessageWrap.getTransaction().getAmount());
        isoMsg.set(6, transactionMessageWrap.getTransaction().getBillingAmount());
        isoMsg.set(7, transactionMessageWrap.getTransaction().getTransmissionDateTime());
        isoMsg.set(10, transactionMessageWrap.getTransaction().getConversionRate());
        isoMsg.set(11, transactionMessageWrap.getTransaction().getSystemTraceNumber());
        isoMsg.set(12, transactionMessageWrap.getTransaction().getTransactionTime());
        isoMsg.set(13, transactionMessageWrap.getTransaction().getTransactionDate());
        isoMsg.set(18, transactionMessageWrap.getTransaction().getMerchantType());
        isoMsg.set(19, transactionMessageWrap.getTransaction().getCountryCode());
        isoMsg.set(22, transactionMessageWrap.getTransaction().getEntryMode());
        isoMsg.set(23, transactionMessageWrap.getTransaction().getSequenceNumber());
        isoMsg.set(25, transactionMessageWrap.getTransaction().getConditionCode());
        isoMsg.set(26, transactionMessageWrap.getTransaction().getReasonCode());
        isoMsg.set(28, transactionMessageWrap.getTransaction().getTransactionFee());
        isoMsg.set(32, transactionMessageWrap.getTransaction().getAcquiringId());
        isoMsg.set(33, transactionMessageWrap.getTransaction().getForwardId());
        isoMsg.set(35, transactionMessageWrap.getTransaction().getTrack2Data());
        isoMsg.set(37, transactionMessageWrap.getTransaction().getRetrievalRefNumber());
        isoMsg.set(38, transactionMessageWrap.getTransaction().getAuthIdResponse());
        isoMsg.set(39, transactionMessageWrap.getState() == State.SUCCESS ? "00" : "01");
        isoMsg.set(41, transactionMessageWrap.getTransaction().getTerminalName());
        isoMsg.set(43, transactionMessageWrap.getTransaction().getTerminalIdentification());
        isoMsg.set(44, transactionMessageWrap.getTransaction().getCheckResult());
        isoMsg.set(45, transactionMessageWrap.getTransaction().getTrack1Data());
        isoMsg.set(48, transactionMessageWrap.getTransaction().getAdditionalDataPrivate());
        isoMsg.set(49, transactionMessageWrap.getTransaction().getTransactionCurrencyCode());
        isoMsg.set(51, transactionMessageWrap.getTransaction().getBillingCurrencyCode());
        isoMsg.set(52, transactionMessageWrap.getTransaction().getPinData());
        isoMsg.set(53, transactionMessageWrap.getTransaction().getAdditionalDataSecurity());
        isoMsg.set(54, transactionMessageWrap.getTransaction().getAdjustAmount());
        isoMsg.set(55, transactionMessageWrap.getTransaction().getChipData());
        isoMsg.set(61, transactionMessageWrap.getTransaction().getIssuerInformation());
        isoMsg.set(62, transactionMessageWrap.getTransaction().getAdditionalData());
        isoMsg.set(63, transactionMessageWrap.getTransaction().getNewPin());
        isoMsg.set(64, transactionMessageWrap.getTransaction().getMessageAuthCode());
        isoMsg.set(70, transactionMessageWrap.getTransaction().getNetworkCode());
        isoMsg.set(95, transactionMessageWrap.getTransaction().getReplacementAmountData());
        isoMsg.set(100, transactionMessageWrap.getTransaction().getReceivingCode());
        isoMsg.set(102, transactionMessageWrap.getTransaction().getFromAccount());
        isoMsg.set(103, transactionMessageWrap.getTransaction().getToAccount());
        isoMsg.set(104, transactionMessageWrap.getTransaction().getTransactionDescription());
        isoMsg.set(105, transactionMessageWrap.getTransaction().getFromAccountBalance());
        isoMsg.set(106, transactionMessageWrap.getTransaction().getMultiCurrencyData());
        isoMsg.set(107, transactionMessageWrap.getTransaction().getRRN());
        isoMsg.set(108, transactionMessageWrap.getTransaction().getTextMessage());
        isoMsg.set(109, transactionMessageWrap.getTransaction().getMultiAccountData());
        isoMsg.set(110, transactionMessageWrap.getTransaction().getMessageCode());
        isoMsg.set(111, transactionMessageWrap.getTransaction().getBackupField());
        isoMsg.set(114, transactionMessageWrap.getTransaction().getStatementMiniData());
        isoMsg.set(115, transactionMessageWrap.getTransaction().getStatementData());
        isoMsg.set(116, transactionMessageWrap.getTransaction().getBillingData());
        isoMsg.set(121, transactionMessageWrap.getTransaction().getAdditionalPOSData());
        isoMsg.set(122, transactionMessageWrap.getTransaction().getAdditional3DS());
        isoMsg.set(123, transactionMessageWrap.getTransaction().getAdditionalData1());
        isoMsg.set(124, transactionMessageWrap.getTransaction().getAdditionalData2());
        isoMsg.set(125, transactionMessageWrap.getTransaction().getReservedPrivateUse1());
        isoMsg.set(126, transactionMessageWrap.getTransaction().getReservedPrivateUse2());
        isoMsg.set(127, transactionMessageWrap.getTransaction().getReservedPrivateUse3());
        isoMsg.set(128, transactionMessageWrap.getTransaction().getMAC2());
        byte[] packedMessage = isoMsg.pack();
        return packedMessage;
    }

    public TransactionMessage unpack(byte[] packedMessage) throws ISOException {
        TransactionMessage transactionMessage = new TransactionMessage();
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(packager);
        isoMsg.unpack(packedMessage);
        transactionMessage.setMessageType(isoMsg.getMTI());
        transactionMessage.setCardNumber(isoMsg.getString(2));
        transactionMessage.setProcessingCode(isoMsg.getString(3));
        transactionMessage.setAmount(isoMsg.getString(4));
        transactionMessage.setBillingAmount(isoMsg.getString(6));
        transactionMessage.setTransmissionDateTime(isoMsg.getString(7));
        transactionMessage.setConversionRate(isoMsg.getString(10));
        transactionMessage.setSystemTraceNumber(isoMsg.getString(11));
        transactionMessage.setTransactionTime(isoMsg.getString(12));
        transactionMessage.setTransactionDate(isoMsg.getString(13));
        transactionMessage.setMerchantType(isoMsg.getString(18));
        transactionMessage.setCountryCode(isoMsg.getString(19));
        transactionMessage.setEntryMode(isoMsg.getString(22));
        transactionMessage.setSequenceNumber(isoMsg.getString(23));
        transactionMessage.setConditionCode(isoMsg.getString(25));
        transactionMessage.setReasonCode(isoMsg.getString(26));
        transactionMessage.setTransactionFee(isoMsg.getString(28));
        transactionMessage.setAcquiringId(isoMsg.getString(32));
        transactionMessage.setForwardId(isoMsg.getString(33));
        transactionMessage.setTrack2Data(isoMsg.getString(35));
        transactionMessage.setRetrievalRefNumber(isoMsg.getString(37));
        transactionMessage.setAuthIdResponse(isoMsg.getString(38));
        transactionMessage.setResponseCode(isoMsg.getString(39));
        transactionMessage.setTerminalName(isoMsg.getString(41));
        transactionMessage.setTerminalIdentification(isoMsg.getString(43));
        transactionMessage.setCheckResult(isoMsg.getString(44));
        transactionMessage.setTrack1Data(isoMsg.getString(45));
        transactionMessage.setAdditionalDataPrivate(isoMsg.getString(48));
        transactionMessage.setTransactionCurrencyCode(isoMsg.getString(49));
        transactionMessage.setBillingCurrencyCode(isoMsg.getString(51));
        transactionMessage.setPinData(isoMsg.getString(52));
        transactionMessage.setAdditionalDataSecurity(isoMsg.getString(53));
        transactionMessage.setAdjustAmount(isoMsg.getString(54));
        transactionMessage.setChipData(isoMsg.getString(55));
        transactionMessage.setIssuerInformation(isoMsg.getString(61));
        transactionMessage.setAdditionalData(isoMsg.getString(62));
        transactionMessage.setNewPin(isoMsg.getString(63));
        transactionMessage.setMessageAuthCode(isoMsg.getString(64));
        transactionMessage.setNetworkCode(isoMsg.getString(70));
        transactionMessage.setReplacementAmountData(isoMsg.getString(95));
        transactionMessage.setReceivingCode(isoMsg.getString(100));
        transactionMessage.setFromAccount(isoMsg.getString(102));
        transactionMessage.setToAccount(isoMsg.getString(103));
        transactionMessage.setTransactionDescription(isoMsg.getString(104));
        transactionMessage.setFromAccountBalance(isoMsg.getString(105));
        transactionMessage.setMultiCurrencyData(isoMsg.getString(106));
        transactionMessage.setRRN(isoMsg.getString(107));
        transactionMessage.setTextMessage(isoMsg.getString(108));
        transactionMessage.setMultiAccountData(isoMsg.getString(109));
        transactionMessage.setMessageCode(isoMsg.getString(110));
        transactionMessage.setBackupField(isoMsg.getString(111));
        transactionMessage.setStatementMiniData(isoMsg.getString(114));
        transactionMessage.setStatementData(isoMsg.getString(115));
        transactionMessage.setBillingData(isoMsg.getString(116));
        transactionMessage.setAdditionalPOSData(isoMsg.getString(121));
        transactionMessage.setAdditional3DS(isoMsg.getString(122));
        transactionMessage.setAdditionalData1(isoMsg.getString(123));
        transactionMessage.setAdditionalData2(isoMsg.getString(124));
        transactionMessage.setReservedPrivateUse1(isoMsg.getString(125));
        transactionMessage.setReservedPrivateUse2(isoMsg.getString(126));
        transactionMessage.setReservedPrivateUse3(isoMsg.getString(127));
        transactionMessage.setMAC2(isoMsg.getString(128));
        return transactionMessage;
    }

    public TransactionMessageWrap createTransactionMessageWrap(Transaction transaction, String requestId, String channelId, String transactionType) {
        TransactionMessageWrap transactionMessageWrap = new TransactionMessageWrap();
        transactionMessageWrap.setTransaction(transaction);
        transactionMessageWrap.setRequestId(requestId);
        transactionMessageWrap.setChannelId(channelId);
        transactionMessageWrap.setState(State.IN_PROGRESS);
        transactionMessageWrap.setTransactionType(transactionType);
        return transactionMessageWrap;
    }

}
