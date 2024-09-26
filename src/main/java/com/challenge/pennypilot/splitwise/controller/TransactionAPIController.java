package com.challenge.pennypilot.splitwise.controller;

import com.challenge.pennypilot.splitwise.authorization.GroupAuthorizationService;
import com.challenge.pennypilot.splitwise.authorization.TransactionAuthorizationService;
import com.challenge.pennypilot.splitwise.dto.TransactionDTO;
import com.challenge.pennypilot.splitwise.exception.AuthorizationException;
import com.challenge.pennypilot.splitwise.exception.ResourceNotFoundException;
import com.challenge.pennypilot.splitwise.response.SuccessResponse;
import com.challenge.pennypilot.splitwise.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class TransactionAPIController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private GroupAuthorizationService groupAuthorizationService;

    @Autowired
    private TransactionAuthorizationService transactionAuthorizationService;

    @GetMapping("/groups/{groupId}/transactions")
    public List<TransactionDTO> getAllTransactionsOfGroup(@PathVariable long groupId) throws ResourceNotFoundException, AuthorizationException {
        groupAuthorizationService.isCurrentUserMemberOfGroup(groupId);
        return transactionService.getAllTransactionsWithGroup(groupId);
    }

    @GetMapping("/transactions/{transactionId}")
    public TransactionDTO getTransactionDetails(@PathVariable long transactionId) throws ResourceNotFoundException, AuthorizationException {
        transactionAuthorizationService.canCurrentUserAccessTransaction(transactionId);
        return transactionService.getTransactionDetails(transactionId);
    }

    @PostMapping("/groups/{groupId}/transaction")
    public TransactionDTO createTransactionForGroup(@PathVariable long groupId, @RequestBody TransactionDTO transactionDTO) throws Exception {
        groupAuthorizationService.isCurrentUserMemberOfGroup(groupId);
        transactionDTO.validate();
        return transactionService.createTransactionForGroup(groupId, transactionDTO);
    }

    @PutMapping("/transactions/{transactionId}")
    public TransactionDTO editTransaction(@PathVariable long transactionId, @RequestBody TransactionDTO transactionDTO) throws ResourceNotFoundException, AuthorizationException {
        transactionAuthorizationService.canCurrentUserAccessTransaction(transactionId);
        return transactionService.editTransactionWithId(transactionId, transactionDTO);
    }

    @DeleteMapping("/transaction/{transactionId}")
    public ResponseEntity<SuccessResponse> deleteTransaction(@PathVariable long transactionId) throws ResourceNotFoundException, AuthorizationException {
        transactionAuthorizationService.canCurrentUserAccessTransaction(transactionId);
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.ok().body(new SuccessResponse("Transaction Deleted Successfully"));
    }
}
