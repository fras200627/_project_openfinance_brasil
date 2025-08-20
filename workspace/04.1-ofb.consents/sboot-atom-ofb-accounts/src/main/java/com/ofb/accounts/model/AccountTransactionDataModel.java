package com.ofb.accounts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Entity(name = "AccountTransactionDataView")
@Table(schema = "ofb", name = "VW_ACCOUNT_PERSONAL_DATA_STATEMENT")
@Data @Builder @AllArgsConstructor @RequiredArgsConstructor
public class AccountTransactionDataModel {

    @Id
    @Column(name = "TRANSACTIONID")
    private String id;

    @Column(name = "CPFNUMBER")
    private String CPFNUmber;

    @Column(name = "ACCOUNTID")
    private String accountId;

    @Column(name = "COMPECODE")
    private String compeCode;

    @Column(name = "BRANCHCODE")
    private String branchCode;

    @Column(name = "ACCOUNTNUMBER")
    private String accountNumber;

    @Column(name = "ACCOUNTCHECKDIGIT")
    private String accountCheckDigit;

    @Column(name = "ACCOUNTTYPE")
    private String accountType;

    @Column(name = "PERSONALID")
    private String personalId;

    @Column(name = "CIVILNAME")
    private String civilName;

    @Column(name = "REFERENCETRANSACTIONID")
    private String referenceTransactionId;

    @Column(name = "TRANSACTIONDATETIME")
    private String transactionDateTime;

    @Column(name = "TXDATETIME")
    private Date txDateTime;

    @Column(name = "COMPLETEDAUTHORISEDPAYMENTTYPE")
    private String completeAuthorisedPaymentType;

    @Column(name = "CREDITDEBITTYPE")
    private String creditDebitType;

    @Column(name = "TRANSACTIONNAME")
    private String transactionName;

    @Column(name = "TRANSACTIONTYPE")
    private String transactionType;

    @Column(name = "TRANSACTIONAMOUNT")
    private String transactionAmount;

    @Column(name = "TRANSACTIONCURRENCY")
    private String transactionCurrency;

}
