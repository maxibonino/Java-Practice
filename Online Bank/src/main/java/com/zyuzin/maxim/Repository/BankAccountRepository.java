package com.zyuzin.maxim.Repository;

import com.zyuzin.maxim.Model.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {
}
