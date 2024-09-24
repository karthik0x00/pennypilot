package com.challenge.pennypilot.splitwise.service;

import com.challenge.pennypilot.splitwise.exception.ResourceNotFoundException;
import com.challenge.pennypilot.splitwise.model.Group;
import com.challenge.pennypilot.splitwise.model.Transaction;
import com.challenge.pennypilot.splitwise.dto.TransactionDTO;
import com.challenge.pennypilot.splitwise.model.User;
import com.challenge.pennypilot.splitwise.repository.TransactionCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionCRUDRepository repository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    public Transaction getTransactionModelWithId(long transactionId) throws ResourceNotFoundException {
        Optional<Transaction> transaction = repository.findById(transactionId);
        if (transaction.isEmpty()) {
            throw new ResourceNotFoundException("Transaction does not exist");
        }
        return transaction.get();
    }

    public TransactionDTO getTransactionDetails(long transactionId) throws ResourceNotFoundException {
        return new TransactionDTO(getTransactionModelWithId(transactionId));
    }

    public List<TransactionDTO> getAllTransactionsWithGroup(long groupId) throws ResourceNotFoundException {
        Group group = groupService.getGroupModelWithId(groupId);
        List<Transaction> transactions = repository.findByGroupOrderByCreatedTimeDesc(group);
        List<TransactionDTO> transactionDTOs = new ArrayList<>(transactions.size());
        for (Transaction transaction : transactions) {
            transactionDTOs.add(new TransactionDTO(transaction));
        }
        return transactionDTOs;
    }

    public void deleteTransaction(long transactionId) throws ResourceNotFoundException {
        Transaction t = getTransactionModelWithId(transactionId);
        repository.delete(t);
    }

    public TransactionDTO editTransactionWithId(long transactionId, TransactionDTO transactionDTO) throws ResourceNotFoundException {
        Transaction transaction = getTransactionModelWithId(transactionId);
        String description = transactionDTO.getDescription();
        Double amount = transactionDTO.getAmount();
        Long paidByUser = transactionDTO.getPaidBy();
        if (StringUtils.hasLength(description)) {
            transaction.setDescription(description);
        }
        if (amount != null) {
            transaction.setAmount(amount);
        }
        if (paidByUser != null) {
            if (userService.isUserExists(paidByUser)) {
                transaction.setPaidBy(paidByUser);
            } else {
                throw new ResourceNotFoundException("User does not exist");
            }
        }
        Transaction savedTransaction = repository.save(transaction);
        return new TransactionDTO(savedTransaction);
    }

    public TransactionDTO createTransactionForGroup(long groupId, TransactionDTO transactionDTO) throws ResourceNotFoundException {
        Transaction transaction = new Transaction();
        Group group = groupService.getGroupModelWithId(groupId);
        User paidByUser = userService.getUserModelWithId(transactionDTO.getPaidBy());
        transaction.setGroup(group);
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setPaidBy(paidByUser.getUserId());
        transaction.setDescription(transactionDTO.getDescription());
        transaction = repository.save(transaction);
        return new TransactionDTO(transaction);
    }
}
