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

    private String messageType;
    private String cardNumber;
    private String processingCode;
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
    private String checkResult;
    private String track1Data;
    private String additionalDataPrivate;
    private String transactionCurrencyCode;
    private String billingCurrencyCode;
    private String pinData;
    private String additionalDataSecurity;
    private String adjustAmount;
    private String chipData;
    private String issuerInformation;
    private String additionalData;
    private String newPin;
    private String messageAuthCode;
    private String networkCode;
    private String replacementAmountData;
    private String receivingCode;
    private String fromAccount;
    private String toAccount;
    private String transactionDescription;
    private String fromAccountBalance;
    private String multiCurrencyData;
    private String RRN;
    private String textMessage;
    private String multiAccountData;
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
        return this.additionalDataPrivate.substring(0,12);
    }

    public String getOtherPANCode(){
        return this.additionalDataPrivate.substring(12);
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
