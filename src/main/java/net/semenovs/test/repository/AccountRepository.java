package net.semenovs.test.repository;

import net.semenovs.test.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {

}
