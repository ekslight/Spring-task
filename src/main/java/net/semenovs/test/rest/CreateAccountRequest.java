package net.semenovs.test.rest;

import javax.validation.constraints.NotEmpty;

public class CreateAccountRequest {

    @NotEmpty
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
