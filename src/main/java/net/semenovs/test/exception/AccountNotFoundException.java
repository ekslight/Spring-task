package net.semenovs.test.exception;

public class AccountNotFoundException extends ClientException {

    public AccountNotFoundException(String id) {
        super(String.format("Cannot find account for the user [%s]", id));
    }
}
