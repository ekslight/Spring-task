package net.semenovs.test.exception;

import net.semenovs.test.model.Account;

import java.math.BigDecimal;

public class NotEnoughFundsException extends ClientException {

    private Account account;

    public NotEnoughFundsException(Account account, BigDecimal requestedAmount) {
        super(String.format("Insufficient funds, requested amount: [%s]", requestedAmount));
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

}
