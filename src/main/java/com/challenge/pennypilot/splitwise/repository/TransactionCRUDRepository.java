package com.challenge.pennypilot.splitwise.repository;

import com.challenge.pennypilot.splitwise.model.Group;
import com.challenge.pennypilot.splitwise.model.Transaction;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TransactionCRUDRepository extends ListCrudRepository<Transaction, Long> {
    List<Transaction> findByGroupOrderByCreatedTimeDesc(Group group);
}
