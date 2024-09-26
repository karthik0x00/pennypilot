package com.challenge.pennypilot.splitwise.authorization;


import com.challenge.pennypilot.splitwise.authentication.AuthenticationService;
import com.challenge.pennypilot.splitwise.dto.UserDTO;
import com.challenge.pennypilot.splitwise.exception.AuthorizationException;
import com.challenge.pennypilot.splitwise.exception.ResourceNotFoundException;
import com.challenge.pennypilot.splitwise.model.Group;
import com.challenge.pennypilot.splitwise.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupAuthorizationService {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private GroupService groupService;

    public void isCurrentUserMemberOfGroup(long groupId) throws AuthorizationException, ResourceNotFoundException {
        long currentUserID = authenticationService.getUserID();
        List<UserDTO> users = groupService.getAllUsersBelongToGroup(groupId);
        for (UserDTO user: users) {
            if (user.getUserId() == currentUserID) {
                return;
            }
        }
        throw new AuthorizationException();
    }

    public void isCurrentUserOwnerOfGroup(long groupId) throws ResourceNotFoundException, AuthorizationException {
        Group group = groupService.getGroupModelWithId(groupId);
        long currentUserID = authenticationService.getUserID();
        long groupCreatedBy = group.getCreatedBy().getUserId();
        if (currentUserID != groupCreatedBy) {
            throw new AuthorizationException();
        }
    }
}
