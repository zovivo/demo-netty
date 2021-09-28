package vn.vnpay.common.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Project: demo-netty
 * Package: vn.vnpay.netty.model
 * Author: zovivo
 * Date: 9/20/2021
 * Created with IntelliJ IDEA
 */

@Getter
@Setter
@NoArgsConstructor
public class TransactionMessage {

    private String headerMessage;   // header
    private String messageType; // MTI
    private String cardNumber;  // field 2
    private String processingCode;  // field 3
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
    private String checkResult; // field 44
    private String track1Data;  // field 45
    private String otherTransactionReference;   // field 48
    private String transactionCurrencyCode; // field 49
    private String billingCurrencyCode; // field 51
    private String pinData; // field 52
    private String additionalDataSecurity;  // field 53
    private String adjustAmount;    // field 54
    private String chipData;    // field 55
    private String issuerInformation;   // field 61
    private String additionalData;  // field 62
    private String newPin;  // field 63
    private String messageAuthCode; // field 64
    private String networkCode; // field 70
    private String replacementAmountData;   // field 95
    private String receivingCode;   // field 100
    private String fromAccount; // field 102
    private String toAccount;   // field 103
    private String hostId;  // field 104
    private String fromAccountBalance;  // field 105
    private String multiCurrencyData;   // field 106
    private String RRN; // field 107
    private String textMessage; // field 108
    private String multiAccountData;    // field 109
    private String messageCode; // field 110
    private String backupField; // field 111
    private String statementMiniData;   // field 114
    private String statementData;   // field 115
    private String billingData; // field 116
    private String additionalPOSData;   // field 121
    private String additional3DS;   // field 122
    private String additionalData1; // field 123
    private String additionalData2; // field 124
    private String reservedPrivateUse1; // field 125
    private String reservedPrivateUse2; // field 126
    private String reservedPrivateUse3; // field 127
    private String MAC2;    // field 128

    public String getStartIndicator(){
        return this.headerMessage.substring(0,3);
    }
    public String getProtocolVersion(){
        return this.headerMessage.substring(3,5);
    }
    public String getRejectStatus(){
        return this.headerMessage.substring(5);
    }

    public String getTransactionCode(){
        return this.processingCode.substring(0,3);
    }

    public String getFromAccountType(){
        return this.processingCode.substring(3,5);
    }

    public String getToAccountType(){
        return this.processingCode.substring(5);
    }

    public String getTerminalOwner(){
        return this.terminalIdentification.substring(0,30);
    }

    public String getTerminalCity(){
        return this.terminalIdentification.substring(30,60);
    }

    public String getTerminalState(){
        return this.terminalIdentification.substring(60,63);
    }

    public String getTerminalCountry(){
        return this.terminalIdentification.substring(63,66);
    }

    public String getTerminalAddress(){
        return this.terminalIdentification.substring(66,96);
    }

    public String getTerminalBranch(){
        return this.terminalIdentification.substring(96,126);
    }

    public String getTerminalRegion(){
        return this.terminalIdentification.substring(126,156);
    }

    public String getTerminalClass(){
        return this.terminalIdentification.substring(156,159);
    }

    public String getTerminalDate(){
        return this.terminalIdentification.substring(159,167);
    }

    public String getTerminalPaymentSystem(){
        return this.terminalIdentification.substring(167,177);
    }

    public String getTerminalFinancialInstitution(){
        return this.terminalIdentification.substring(177,181);
    }

    public String getTerminalRetailer(){
        return this.terminalIdentification.substring(181,206);
    }

    public String getTerminalCounty(){
        return this.terminalIdentification.substring(206,209);
    }

    public String getTerminalZipCode(){
        return this.terminalIdentification.substring(209,218);
    }

    public String getTerminalTimeOffset(){
        return this.terminalIdentification.substring(218);
    }

    public String getPinCheckResult(){
        return this.checkResult.substring(0,1);
    }

    public String getCVVCheckResult(){
        return this.checkResult.substring(1);
    }

    public String getOtherRRNCode(){
        return this.otherTransactionReference.substring(0,12);
    }

    public String getOtherPANCode(){
        return this.otherTransactionReference.substring(12);
    }

    public String getIssuerOrganizationName(){
        return this.issuerInformation.substring(0,4);
    }

    public String getIssuerPaymentName(){
        return this.issuerInformation.substring(4);
    }

    public String getReplacementAmount(){
        return this.replacementAmountData.substring(0,12);
    }

    public String getReplacementPrincipalAmount(){
        return this.replacementAmountData.substring(12);
    }

    public String getFromAcctAvailableBalance(){
        return this.fromAccountBalance.substring(0,12);
    }

    public String getFromAcctActualBalance(){
        return this.fromAccountBalance.substring(12,24);
    }

    public String getCurrencyCodeType(){
        return this.fromAccountBalance.substring(24);
    }

    public String getToAcctCurrencyCode(){
        return this.multiCurrencyData.substring(0,3);
    }

    public String getSourceCurrencyCode(){
        return this.multiCurrencyData.substring(3,6);
    }

    public String getToAccountAmount(){
        return this.multiCurrencyData.substring(6,18);
    }

    public String getPrincipalAmount(){
        return this.multiCurrencyData.substring(18,30);
    }

    public String getFromAcctExchangeRate(){
        return this.multiCurrencyData.substring(30,38);
    }

    public String getToAcctExchangeRate(){
        return this.multiCurrencyData.substring(38);
    }

    public String getMultiAccountLength(){
        return this.multiAccountData.substring(0,3);
    }

    public String getMultiAccountType(){
        return this.multiAccountData.substring(3,4);
    }

    public String getMultiAccountNumber(){
        return this.multiAccountData.substring(4,34);
    }

    public String getMultiAccountTitle(){
        return this.multiAccountData.substring(34,59);
    }

    public String getMultiAccountCurrencyCode(){
        return this.multiAccountData.substring(59,62);
    }

}
