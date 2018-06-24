package com.zyuzin.maxim.Repository;

import com.zyuzin.maxim.Model.BankOperation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankOperationRepository extends CrudRepository<BankOperation, Long> {
}
