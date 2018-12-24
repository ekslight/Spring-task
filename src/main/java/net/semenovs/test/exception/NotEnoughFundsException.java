package net.semenovs.test.exception;

import java.math.BigDecimal;

public class NotEnoughFundsException extends ClientException {

    public NotEnoughFundsException(BigDecimal requestedAmount, BigDecimal residue) {
        super(String.format("Insufficient funds, requested amount: [%s], residue [%s]", requestedAmount, residue));
    }
}
