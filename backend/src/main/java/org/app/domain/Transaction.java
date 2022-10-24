package org.app.domain;

import java.util.Date;

public class Transaction extends DomainEntity{
    final Date dateRecord;

    // Buy or Sell
    final String transactionType;
    // Same as the one in the Portfolio
    final Long cryptocurrencyID;

    // Money amount + and -
    final Long transactionAmount;
    final Long currencyQuantity;

    //Account and portfolio used for the transaction
    final Account account;
    final Portfolio portfolio;

    public Transaction(Date dateRecord, String transactionType, Long cryptocurrencyID, Long transactionAmount, Long currencyQuantity, Account account, Portfolio portfolio) {
        this.dateRecord = dateRecord;
        this.transactionType = transactionType;
        this.cryptocurrencyID = cryptocurrencyID;
        this.transactionAmount = transactionAmount;
        this.currencyQuantity = currencyQuantity;
        this.account = account;
        this.portfolio = portfolio;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "account='" + account + '\'' +
                ", cryptocurrencyID='" + cryptocurrencyID + '\'' +
                ", transactionAmount='" + transactionAmount + '\'' +
                ", currencyQuantity='" + currencyQuantity + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", dateRecord='" + dateRecord + '\'' +
                '}';
    }

    /* GETTERS */
    public Date getDatePurchased() {
        return dateRecord;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Long getCryptocurrencyID() {
        return cryptocurrencyID;
    }

    public Long getTransactionAmount() {
        return transactionAmount;
    }

    public Long getCurrencyQuantity() {
        return currencyQuantity;
    }

    public Account getAccount() {
        return account;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
}
