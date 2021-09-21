package vn.vnpay.netty.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.vnpay.netty.message.TransactionMessage;

/**
 * Project: demo-netty
 * Package: vn.vnpay.netty.model
 * Author: zovivo
 * Date: 9/21/2021
 * Created with IntelliJ IDEA
 */

@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    private String cardNumber;

    private String processingCode;
    private String transactionCode;
    private String fromAccountType;
    private String toAccountType;

    private String amount;
    private String billingAmount;
    private String transmissionDateTime;
    private String conversionRate;
    private String systemTraceNumber;
    private String transactionTime;
    private String transactionDate;
    private String merchantType;
    private String countryCode;
    private String entryMode;
    private String sequenceNumber;
    private String conditionCode;
    private String reasonCode;
    private String transactionFee;
    private String acquiringId;
    private String forwardId;
    private String track2Data;
    private String retrievalRefNumber;
    private String authIdResponse;
    private String responseCode;
    private String terminalName;

    private String terminalIdentification;
    private String terminalOwner;
    private String terminalCity;
    private String terminalState;
    private String terminalCountry;
    private String terminalAddress;
    private String terminalBranch;
    private String terminalRegion;
    private String terminalClass;
    private String terminalDate;
    private String terminalPaymentSystem;
    private String terminalFinancialInstitution;
    private String terminalRetailer;
    private String terminalCounty;
    private String terminalZipCode;
    private String terminalTimeOffset;

    private String checkResult;
    private String pinCheckResult;
    private String cvvCheckResult;

    private String track1Data;

    private String additionalDataPrivate;
    private String otherRRNCode;
    private String otherPANCode;

    private String transactionCurrencyCode;
    private String billingCurrencyCode;
    private String pinData;
    private String additionalDataSecurity;
    private String adjustAmount;
    private String chipData;

    private String issuerInformation;
    private String issuerOrganizationName;
    private String issuerPaymentName;

    private String additionalData;
    private String newPin;
    private String messageAuthCode;
    private String networkCode;

    private String replacementAmountData;
    private String replacementAmount;
    private String replacementPrincipalAmount;

    private String receivingCode;
    private String fromAccount;
    private String toAccount;
    private String transactionDescription;

    private String fromAccountBalance;
    private String fromAcctAvailableBalance;
    private String fromAcctActualBalance;
    private String currencyCodeType;

    private String multiCurrencyData;
    private String toAcctCurrencyCode;
    private String sourceCurrencyCode;
    private String toAccountAmount;
    private String principalAmount;
    private String fromAcctExchangeRate;
    private String toAcctExchangeRate;

    private String RRN;
    private String textMessage;

    private String multiAccountData;
    private String multiAccountLength;
    private String multiAccountType;
    private String multiAccountNumber;
    private String multiAccountTitle;
    private String multiAccountCurrencyCode;

    private String messageCode;
    private String backupField;
    private String statementMiniData;
    private String statementData;
    private String billingData;

    private String additionalPOSData;
    private String additional3DS;
    private String additionalData1;
    private String additionalData2;
    private String reservedPrivateUse1;
    private String reservedPrivateUse2;
    private String reservedPrivateUse3;
    private String MAC2;

    public Transaction(TransactionMessage transactionMessage) {
        this.cardNumber = transactionMessage.getCardNumber();
        this.processingCode = transactionMessage.getProcessingCode();
        this.transactionCode = transactionMessage.getTransactionCode();
        this.fromAccountType = transactionMessage.getFromAccountType();
        this.toAccountType = transactionMessage.getToAccountType();
        this.amount = transactionMessage.getAmount();
        this.billingAmount = transactionMessage.getBillingAmount();
        this.transmissionDateTime = transactionMessage.getTransmissionDateTime();
        this.conversionRate = transactionMessage.getConversionRate();
        this.systemTraceNumber = transactionMessage.getSystemTraceNumber();
        this.transactionTime = transactionMessage.getTransactionTime();
        this.transactionDate = transactionMessage.getTransactionDate();
        this.merchantType = transactionMessage.getMerchantType();
        this.countryCode = transactionMessage.getCountryCode();
        this.entryMode = transactionMessage.getEntryMode();
        this.sequenceNumber = transactionMessage.getSequenceNumber();
        this.conditionCode = transactionMessage.getConditionCode();
        this.reasonCode = transactionMessage.getReasonCode();
        this.transactionFee = transactionMessage.getTransactionFee();
        this.acquiringId = transactionMessage.getAcquiringId();
        this.forwardId = transactionMessage.getForwardId();
        this.track2Data = transactionMessage.getTrack2Data();
        this.retrievalRefNumber = transactionMessage.getRetrievalRefNumber();
        this.authIdResponse = transactionMessage.getAuthIdResponse();
        this.responseCode = transactionMessage.getResponseCode();
        this.terminalName = transactionMessage.getTerminalName();
        this.terminalIdentification = transactionMessage.getTerminalIdentification();
        this.terminalOwner = transactionMessage.getTerminalOwner();
        this.terminalCity = transactionMessage.getTerminalCity();
        this.terminalState = transactionMessage.getTerminalState();
        this.terminalCountry = transactionMessage.getTerminalCountry();
        this.terminalAddress = transactionMessage.getTerminalAddress();
        this.terminalBranch = transactionMessage.getTerminalBranch();
        this.terminalRegion = transactionMessage.getTerminalRegion();
        this.terminalClass = transactionMessage.getTerminalClass();
        this.terminalDate = transactionMessage.getTerminalDate();
        this.terminalPaymentSystem = transactionMessage.getTerminalPaymentSystem();
        this.terminalFinancialInstitution = transactionMessage.getTerminalFinancialInstitution();
        this.terminalRetailer = transactionMessage.getTerminalRetailer();
        this.terminalCounty = transactionMessage.getTerminalCounty();
        this.terminalZipCode = transactionMessage.getTerminalZipCode();
        this.terminalTimeOffset = transactionMessage.getTerminalTimeOffset();
        this.checkResult = transactionMessage.getCheckResult();
        if (null != transactionMessage.getCheckResult() && !transactionMessage.getCheckResult().isEmpty()) {
            this.pinCheckResult = transactionMessage.getPinCheckResult();
            this.cvvCheckResult = transactionMessage.getCVVCheckResult();
        }
        this.track1Data = transactionMessage.getTrack1Data();
        this.additionalDataPrivate = transactionMessage.getAdditionalDataPrivate();
        if (null != transactionMessage.getAdditionalDataPrivate() && !transactionMessage.getAdditionalDataPrivate().isEmpty()) {
            this.otherRRNCode = transactionMessage.getOtherRRNCode();
            this.otherPANCode = transactionMessage.getOtherPANCode();
        }
        this.transactionCurrencyCode = transactionMessage.getTransactionCurrencyCode();
        this.billingCurrencyCode = transactionMessage.getBillingCurrencyCode();
        this.pinData = transactionMessage.getPinData();
        this.additionalDataSecurity = transactionMessage.getAdditionalDataSecurity();
        this.adjustAmount = transactionMessage.getAdjustAmount();
        this.chipData = transactionMessage.getChipData();
        this.issuerInformation = transactionMessage.getIssuerInformation();
        this.issuerOrganizationName = transactionMessage.getIssuerOrganizationName();
        this.issuerPaymentName = transactionMessage.getIssuerPaymentName();
        this.additionalData = transactionMessage.getAdditionalData();
        this.newPin = transactionMessage.getNewPin();
        this.messageAuthCode = transactionMessage.getMessageAuthCode();
        this.networkCode = transactionMessage.getNetworkCode();
        this.replacementAmountData = transactionMessage.getReplacementAmountData();
        if (null != transactionMessage.getReplacementAmountData() && !transactionMessage.getReplacementAmountData().isEmpty()) {
            this.replacementAmount = transactionMessage.getReplacementAmount();
            this.replacementPrincipalAmount = transactionMessage.getReplacementPrincipalAmount();
        }
        this.receivingCode = transactionMessage.getReceivingCode();
        this.fromAccount = transactionMessage.getFromAccount();
        this.toAccount = transactionMessage.getToAccount();
        this.transactionDescription = transactionMessage.getTransactionDescription();
        this.fromAccountBalance = transactionMessage.getFromAccountBalance();
        if (null != transactionMessage.getFromAccountBalance() && !transactionMessage.getFromAccountBalance().isEmpty()) {
            this.fromAcctAvailableBalance = transactionMessage.getFromAcctAvailableBalance();
            this.fromAcctActualBalance = transactionMessage.getFromAcctActualBalance();
            this.currencyCodeType = transactionMessage.getCurrencyCodeType();
        }
        this.multiCurrencyData = transactionMessage.getMultiCurrencyData();
        this.toAcctCurrencyCode = transactionMessage.getToAcctCurrencyCode();
        this.sourceCurrencyCode = transactionMessage.getSourceCurrencyCode();
        this.toAccountAmount = transactionMessage.getToAccountAmount();
        this.principalAmount = transactionMessage.getPrincipalAmount();
        this.fromAcctExchangeRate = transactionMessage.getFromAcctExchangeRate();
        this.toAcctExchangeRate = transactionMessage.getToAcctExchangeRate();
        this.RRN = transactionMessage.getRRN();
        this.textMessage = transactionMessage.getTextMessage();
        this.multiAccountData = transactionMessage.getMultiAccountData();
        if (null != transactionMessage.getMultiAccountData() && !transactionMessage.getMultiAccountData().isEmpty()){
            this.multiAccountLength = transactionMessage.getMultiAccountLength();
            this.multiAccountType = transactionMessage.getMultiAccountType();
            this.multiAccountNumber = transactionMessage.getMultiAccountNumber();
            this.multiAccountTitle = transactionMessage.getMultiAccountTitle();
            this.multiAccountCurrencyCode = transactionMessage.getMultiAccountCurrencyCode();
        }
        this.messageCode = transactionMessage.getMessageCode();
        this.backupField = transactionMessage.getBackupField();
        this.statementMiniData = transactionMessage.getStatementMiniData();
        this.statementData = transactionMessage.getStatementData();
        this.billingData = transactionMessage.getBillingData();
        this.additionalPOSData = transactionMessage.getAdditionalPOSData();
        this.additional3DS = transactionMessage.getAdditional3DS();
        this.additionalData1 = transactionMessage.getAdditionalData1();
        this.additionalData2 = transactionMessage.getAdditionalData2();
        this.reservedPrivateUse1 = transactionMessage.getReservedPrivateUse1();
        this.reservedPrivateUse2 = transactionMessage.getReservedPrivateUse2();
        this.reservedPrivateUse3 = transactionMessage.getReservedPrivateUse3();
        this.MAC2 = transactionMessage.getMAC2();
    }
}
