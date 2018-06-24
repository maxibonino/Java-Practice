package com.zyuzin.maxim.Services;

import com.zyuzin.maxim.Model.BankOperation;

public interface OperationService {
    void commit(BankOperation operation);
    void rollback(BankOperation operation);
}
