package net.semenovs.test.rest;

import net.semenovs.test.model.Account;

public class CommonResponse {

    private String message;
    private final Status status;
    private final Account account;

    public CommonResponse(Status status, Account account) {
        this.status = status;
        this.account = account;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public Account getAccount() {
        return account;
    }

    public enum Status {
        SUCCESS, ERROR
    }
}
