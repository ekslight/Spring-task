package net.semenovs.test.rest;

import java.math.BigDecimal;

public class TransferRequest {

    private String senderUserName;
    private String receiverUserName;
    private BigDecimal amount;

    public String getReceiverUserName() {
        return receiverUserName;
    }

    public void setReceiverUserName(String receiverUserName) {
        this.receiverUserName = receiverUserName;
    }

    public String getSenderUserName() {

        return senderUserName;
    }

    public void setSenderUserName(String senderUserName) {
        this.senderUserName = senderUserName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
