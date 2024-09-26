package com.challenge.pennypilot.splitwise.authorization;


import com.challenge.pennypilot.splitwise.authentication.AuthenticationService;
import com.challenge.pennypilot.splitwise.exception.AuthorizationException;
import com.challenge.pennypilot.splitwise.exception.ResourceNotFoundException;
import com.challenge.pennypilot.splitwise.model.Group;
import com.challenge.pennypilot.splitwise.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionAuthorizationService {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private GroupAuthorizationService groupAuthorizationService;

    @Autowired
    private TransactionService transactionService;

    public void canCurrentUserAccessTransaction(long transactionId) throws ResourceNotFoundException, AuthorizationException {
        Group belongsToGroup = transactionService.getTransactionModelWithId(transactionId).getGroup();
        groupAuthorizationService.isCurrentUserMemberOfGroup(belongsToGroup.getGroupId());
    }
}
