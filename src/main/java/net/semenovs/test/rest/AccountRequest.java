package net.semenovs.test.rest;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AccountRequest {

    @NotNull
    private String userName;

    private BigDecimal amount;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
