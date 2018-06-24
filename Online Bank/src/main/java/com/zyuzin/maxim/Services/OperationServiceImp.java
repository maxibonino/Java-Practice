package com.zyuzin.maxim.Services;

import com.zyuzin.maxim.Model.BankAccount;
import com.zyuzin.maxim.Model.BankOperation;
import com.zyuzin.maxim.Repository.BankAccountRepository;
import com.zyuzin.maxim.Repository.BankOperationRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OperationServiceImp implements OperationService {
    private final BankOperationRepository bankOperationRepository;
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public OperationServiceImp(BankOperationRepository bankOperationRepository, BankAccountRepository bankAccountRepository) {
        this.bankOperationRepository = bankOperationRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    @SneakyThrows
    @Override
    public void commit(BankOperation operation) {
        BankAccount sender = operation.getSender();
        sender.setAmount(sender.getAmount() - operation.getAmount());
        if (sender.getAmount() < 0) {
            throw new Exception("Commit failed");
        }
        BankAccount receiver = operation.getReceiver();
        receiver.setAmount(receiver.getAmount() + operation.getAmount());
        bankAccountRepository.save(sender);
        bankAccountRepository.save(receiver);
        bankOperationRepository.save(operation);
    }

    @SneakyThrows
    @Override
    public void rollback(BankOperation operation) {
        BankAccount sender = operation.getSender();
        sender.setAmount(sender.getAmount() + operation.getAmount());
        BankAccount receiver = operation.getReceiver();
        receiver.setAmount(receiver.getAmount() - operation.getAmount());
        if (receiver.getAmount() < 0) {
            throw new Exception("Rollback failed");
        }
        operation.setReverse(true);
        bankAccountRepository.save(sender);
        bankAccountRepository.save(receiver);
        bankOperationRepository.save(operation);
    }
}
