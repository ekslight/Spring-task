package net.semenovs.test.service;

import net.semenovs.test.exception.AccountAlreadyExistException;
import net.semenovs.test.exception.AccountNotFoundException;
import net.semenovs.test.exception.NotEnoughFundsException;
import net.semenovs.test.model.Account;
import net.semenovs.test.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@Service
public class ApplicationService {

    private AccountRepository accountRepository;

    @Autowired
    public ApplicationService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Account transfer(String senderUser, String receiverUser, BigDecimal amount) throws
            AccountNotFoundException, NotEnoughFundsException {
        Account sender = getAccountMandatory(senderUser);
        Account receiver = getAccountMandatory(receiverUser);

        BigDecimal residue = sender.getMoneyAmount().subtract(amount);
        if (residue.compareTo(ZERO) < 0) {
            throw new NotEnoughFundsException(amount, sender.getMoneyAmount());
        }

        sender.setMoneyAmount(residue);
        receiver.setMoneyAmount(receiver.getMoneyAmount().add(amount));
        accountRepository.save(sender);
        accountRepository.save(receiver);
        return sender;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Account topUp(String username, BigDecimal amount) throws AccountNotFoundException {
        Account account = getAccountMandatory(username);
        account.setMoneyAmount(account.getMoneyAmount().add(amount));
        accountRepository.save(account);
        return account;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Account withdraw(String username, BigDecimal amount) throws AccountNotFoundException {
        Account account = getAccountMandatory(username);

        BigDecimal residue = account.getMoneyAmount().subtract(amount);
        if (residue.compareTo(ZERO) < 0) {
            throw new NotEnoughFundsException(amount, account.getMoneyAmount());
        }

        account.setMoneyAmount(residue);
        accountRepository.save(account);
        return account;
    }

    private Account getAccountMandatory(String user) throws AccountNotFoundException {
        return accountRepository.findById(user).orElseThrow(() -> new AccountNotFoundException(user));
    }

    public Account createAccount(String login) throws AccountAlreadyExistException {
        if (accountRepository.findById(login).isPresent()) {
            throw new AccountAlreadyExistException(login);
        }

        Account account = new Account();
        account.setUserName(login);
        accountRepository.save(account);
        return account;
    }
}
