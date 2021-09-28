package vn.vnpay.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.vnpay.common.message.TransactionMessage;

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

    private String headerMessage;   // header
    private String startIndicator;  // header field 1
    private String protocolVersion; // header field 2
    private String rejectStatus;    // header field 3

    private String messageType;// MTI

    private String cardNumber;  // field 2

    private String processingCode;  // field 3
    private String transactionCode;  // field 3.1
    private String fromAccountType;  // field 3.2
    private String toAccountType;  // field 3.3

    private String amount;  // field 4
    private String billingAmount;   // field 6
    private String transmissionDateTime;    // field 7
    private String conversionRate;  // field 10
    private String systemTraceNumber;   // field 11
    private String transactionTime; // field 12
    private String transactionDate; // field 13
    private String merchantType;    // field 18
    private String countryCode; // field 19
    private String entryMode;   // field 22
    private String sequenceNumber;  // field 23
    private String conditionCode;   // field 25
    private String reasonCode;  // field 26
    private String transactionFee;  // field 28
    private String acquiringId; // field 32
    private String forwardId;   // field 33
    private String track2Data;  // field 35
    private String retrievalRefNumber;  // field 37
    private String authIdResponse;  // field 38
    private String responseCode;    // field 39
    private String terminalName;    // field 41

    private String terminalIdentification;  // field 43
    private String terminalOwner;  // field 43.1
    private String terminalCity;  // field 43.2
    private String terminalState;  // field 43.3
    private String terminalCountry;  // field 43.4
    private String terminalAddress;  // field 43.5
    private String terminalBranch;  // field 43.6
    private String terminalRegion;  // field 43.7
    private String terminalClass;  // field 43.8
    private String terminalDate;  // field 43.9
    private String terminalPaymentSystem;  // field 43.10
    private String terminalFinancialInstitution;  // field 43.11
    private String terminalRetailer;  // field 43.12
    private String terminalCounty;  // field 43.13
    private String terminalZipCode;  // field 43.14
    private String terminalTimeOffset;  // field 43.15

    private String checkResult;  // field 44
    private String pinCheckResult;  // field 44.1
    private String cvvCheckResult;  // field 44.2

    private String track1Data;  // field 45

    private String otherTransactionReference;  // field 48
    private String otherRRNCode;  // field 48.1
    private String otherPANCode;  // field 48.2

    private String transactionCurrencyCode;  // field 49
    private String billingCurrencyCode;  // field 51
    private String pinData;  // field 52
    private String additionalDataSecurity;  // field 53
    private String adjustAmount;  // field 54
    private String chipData;  // field 55

    private String issuerInformation;  // field 61
    private String issuerOrganizationName;  // field 61.1
    private String issuerPaymentName;  // field 61.2

    private String additionalData;  // field 62
    private String newPin;  // field 63
    private String messageAuthCode;  // field 64
    private String networkCode;  // field 70

    private String replacementAmountData;  // field 95
    private String replacementAmount;  // field 95.1
    private String replacementPrincipalAmount;  // field 95.2

    private String receivingCode;  // field 100
    private String fromAccount;  // field 102
    private String toAccount;  // field 103
    private String hostId;  // field 104

    private String fromAccountBalance;  // field 105
    private String fromAcctAvailableBalance;  // field 105.1
    private String fromAcctActualBalance;  // field 105.2
    private String currencyCodeType;  // field 105.3

    private String multiCurrencyData;  // field 106
    private String toAcctCurrencyCode;  // field 106.1
    private String sourceCurrencyCode;  // field 106.2
    private String toAccountAmount;  // field 106.3
    private String principalAmount;  // field 106.4
    private String fromAcctExchangeRate;  // field 106.5
    private String toAcctExchangeRate;  // field 106.6

    private String RRN;  // field 107
    private String textMessage;  // field 108

    private String multiAccountData;  // field 109
    private String multiAccountLength;  // field 109.1
    private String multiAccountType;  // field 109.2
    private String multiAccountNumber;  // field 109.3
    private String multiAccountTitle;  // field 109.4
    private String multiAccountCurrencyCode;  // field 109.5

    private String messageCode;  // field 110
    private String backupField;  // field 111
    private String statementMiniData;  // field 114
    private String statementData;  // field 115
    private String billingData;  // field 116

    private String additionalPOSData;  // field 121
    private String additional3DS;  // field 122
    private String additionalData1;  // field 123
    private String additionalData2;  // field 124
    private String reservedPrivateUse1;  // field 125
    private String reservedPrivateUse2;  // field 126
    private String reservedPrivateUse3;  // field 127
    private String MAC2;  // field 128

    public Transaction(TransactionMessage transactionMessage) {
        this.headerMessage = transactionMessage.getHeaderMessage();
        this.startIndicator = transactionMessage.getStartIndicator();
        this.protocolVersion = transactionMessage.getProtocolVersion();
        this.rejectStatus = transactionMessage.getRejectStatus();
        this.messageType = transactionMessage.getMessageType();
        this.cardNumber = transactionMessage.getCardNumber();
        this.processingCode = transactionMessage.getProcessingCode();
        if (null != transactionMessage.getProcessingCode() && !transactionMessage.getProcessingCode().isEmpty()) {
            this.transactionCode = transactionMessage.getTransactionCode();
            this.fromAccountType = transactionMessage.getFromAccountType();
            this.toAccountType = transactionMessage.getToAccountType();
        }
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
        if (null != transactionMessage.getTerminalIdentification() && !transactionMessage.getTerminalIdentification().isEmpty()) {
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
        }
        this.checkResult = transactionMessage.getCheckResult();
        if (null != transactionMessage.getCheckResult() && !transactionMessage.getCheckResult().isEmpty()) {
            this.pinCheckResult = transactionMessage.getPinCheckResult();
            this.cvvCheckResult = transactionMessage.getCVVCheckResult();
        }
        this.track1Data = transactionMessage.getTrack1Data();
        this.otherTransactionReference = transactionMessage.getOtherTransactionReference();
        if (null != transactionMessage.getOtherTransactionReference() && !transactionMessage.getOtherTransactionReference().isEmpty()) {
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
        if (null != transactionMessage.getIssuerInformation() && !transactionMessage.getIssuerInformation().isEmpty()) {
            this.issuerOrganizationName = transactionMessage.getIssuerOrganizationName();
            this.issuerPaymentName = transactionMessage.getIssuerPaymentName();
        }
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
        this.hostId = transactionMessage.getHostId();
        this.fromAccountBalance = transactionMessage.getFromAccountBalance();
        if (null != transactionMessage.getFromAccountBalance() && !transactionMessage.getFromAccountBalance().isEmpty()) {
            this.fromAcctAvailableBalance = transactionMessage.getFromAcctAvailableBalance();
            this.fromAcctActualBalance = transactionMessage.getFromAcctActualBalance();
            this.currencyCodeType = transactionMessage.getCurrencyCodeType();
        }
        this.multiCurrencyData = transactionMessage.getMultiCurrencyData();
        if (null != transactionMessage.getMultiCurrencyData() && !transactionMessage.getMultiCurrencyData().isEmpty()) {
            this.toAcctCurrencyCode = transactionMessage.getToAcctCurrencyCode();
            this.sourceCurrencyCode = transactionMessage.getSourceCurrencyCode();
            this.toAccountAmount = transactionMessage.getToAccountAmount();
            this.principalAmount = transactionMessage.getPrincipalAmount();
            this.fromAcctExchangeRate = transactionMessage.getFromAcctExchangeRate();
            this.toAcctExchangeRate = transactionMessage.getToAcctExchangeRate();
        }
        this.RRN = transactionMessage.getRRN();
        this.textMessage = transactionMessage.getTextMessage();
        this.multiAccountData = transactionMessage.getMultiAccountData();
        if (null != transactionMessage.getMultiAccountData() && !transactionMessage.getMultiAccountData().isEmpty()) {
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
