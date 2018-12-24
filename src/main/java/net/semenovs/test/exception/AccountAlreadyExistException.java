package net.semenovs.test.exception;

public class AccountAlreadyExistException extends ClientException {

    public AccountAlreadyExistException(String accountId) {
        super(String.format("Account already exist for the user [%s]", accountId));
    }
}
