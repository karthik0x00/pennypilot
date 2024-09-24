package com.challenge.pennypilot.splitwise.controller;

import com.challenge.pennypilot.splitwise.dto.GroupDTO;
import com.challenge.pennypilot.splitwise.dto.UserDTO;
import com.challenge.pennypilot.splitwise.exception.ResourceNotFoundException;
import com.challenge.pennypilot.splitwise.response.SuccessResponse;
import com.challenge.pennypilot.splitwise.service.GroupService;
import com.challenge.pennypilot.splitwise.service.UserService;
import com.challenge.pennypilot.splitwise.utils.URIUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GroupAPIController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @PostMapping("/group")
    public ResponseEntity<SuccessResponse> saveGroup(@RequestBody GroupDTO group) throws Exception {
        group.validate();
        GroupDTO groupDTO = groupService.createGroup(group);
        return ResponseEntity.created(URIUtil.buildURIFromId(groupDTO.getGroupId())).body(new SuccessResponse("Group Created Successfully"));
    }

//    TODO: Change path variable to retrieve current user id with authentication
    @GetMapping("/users/{userId}/groups")
    public List<GroupDTO> getAllGroupsBelongsToCurrentUser(@PathVariable long userId) throws ResourceNotFoundException {
        return userService.getAllGroupsBelongToUser(userId);
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<GroupDTO> getGroupDetails(@PathVariable long groupId) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(groupService.getGroupWithId(groupId));
    }

    @PutMapping("/groups/{groupId}")
    public ResponseEntity<GroupDTO> updateUserWithId(@PathVariable long groupId, @RequestBody GroupDTO groupDTO) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(groupService.updateGroupWithId(groupId, groupDTO));
    }

    @DeleteMapping("/groups/{groupId}")
    public ResponseEntity<SuccessResponse> deleteUserWithId(@PathVariable long groupId) throws ResourceNotFoundException {
        groupService.deleteUserWithId(groupId);
        return ResponseEntity.ok().body(new SuccessResponse("Group Deleted Successfully"));
    }

//    Group and user operations

    @PostMapping("/groups/{groupId}/users")
    public ResponseEntity<SuccessResponse> addUsersToGroupWithId(@PathVariable long groupId, @RequestBody List<UserDTO> users) throws ResourceNotFoundException {
        groupService.addUsersToGroupWithId(groupId, users);
        return ResponseEntity.ok().body(new SuccessResponse("Users Added Successfully"));
    }

    @GetMapping("/groups/{groupId}/users")
    public List<UserDTO> getAllUsersBelongToGroup(@PathVariable long groupId) throws ResourceNotFoundException {
        return groupService.getAllUsersBelongToGroup(groupId);
    }

    @DeleteMapping("/groups/{groupId}/users/{userId}")
    public ResponseEntity<SuccessResponse> deleteUserFromGroupWithId(@PathVariable long groupId, @PathVariable long userId) throws ResourceNotFoundException {
        groupService.deleteUserFromGroupWithId(groupId, userId);
        return ResponseEntity.ok().body(new SuccessResponse("User Removed from the Group Successfully"));
    }
}
