package com.challenge.pennypilot.splitwise.service;

import com.challenge.pennypilot.splitwise.dto.GroupDTO;
import com.challenge.pennypilot.splitwise.dto.UserDTO;
import com.challenge.pennypilot.splitwise.exception.InvalidDataProvidedException;
import com.challenge.pennypilot.splitwise.exception.ResourceNotFoundException;
import com.challenge.pennypilot.splitwise.model.Group;
import com.challenge.pennypilot.splitwise.model.User;
import com.challenge.pennypilot.splitwise.repository.GroupCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GroupService {
    @Autowired
    private GroupCRUDRepository repository;

    @Autowired
    private UserService userService;

    public Group getGroupModelWithId(long groupId) throws ResourceNotFoundException {
        Optional<Group> group = repository.findById(groupId);
        if (group.isEmpty()) {
            throw new ResourceNotFoundException("Group does not exist");
        }
        return group.get();
    }

    public GroupDTO getGroupWithId(long groupId) throws ResourceNotFoundException {
        return new GroupDTO(getGroupModelWithId(groupId));
    }

    public GroupDTO createGroup(GroupDTO groupDTO, User createdBy) throws Exception {
        Group group = new Group(groupDTO.getName(), groupDTO.getDescription());
        group.setCreatedBy(createdBy);
        group.addUser(createdBy);
        return new GroupDTO(repository.save(group));
    }

    public GroupDTO updateGroupWithId(long groupId, GroupDTO groupDTO) throws ResourceNotFoundException {
        String name = groupDTO.getName();
        String description = groupDTO.getDescription();
        Group group = getGroupModelWithId(groupId);
        if (StringUtils.hasLength(name)) {
            group.setName(name);
        }
        if (StringUtils.hasLength(description)) {
            group.setDescription(description);
        }
        return new GroupDTO(repository.save(group));
    }

    public void deleteUserWithId(long groupId) throws ResourceNotFoundException {
        repository.delete(getGroupModelWithId(groupId));
    }

//    Group with users relational operations

    public void addUsersToGroupWithId(long groupId, List<UserDTO> users) throws ResourceNotFoundException {
        Group group = getGroupModelWithId(groupId);
        List<Long> userIds = new ArrayList<>(users.size());
        for (UserDTO userDTO: users) {
            userIds.add(userDTO.getUserId());
        }
        List<User> userModels = userService.getUserModelsWithIds(userIds);
        group.addAllUsers(userModels);
        repository.save(group);
    }

    public List<UserDTO> getAllUsersBelongToGroup(long groupId) throws ResourceNotFoundException {
        List<User> users = new ArrayList<>(getGroupModelWithId(groupId).getUsers());
        List<UserDTO> userDTOs = new ArrayList<>(users.size());
        for (User user: users) {
            userDTOs.add(new UserDTO(user));
        }
        return userDTOs;
    }

    public void deleteUserFromGroupWithId(long groupId, long userId) throws ResourceNotFoundException, InvalidDataProvidedException {
        Group group = getGroupModelWithId(groupId);
        if (userId == group.getCreatedBy().getUserId()) {
            throw new InvalidDataProvidedException("Cannot delete owner of the group");
        }
        List<User> users = group.getUsers();
        User userToRemove = userService.getUserModelWithId(userId);
        if (users.contains(userToRemove)) {
            users.remove(userToRemove);
        } else {
            throw new ResourceNotFoundException("User does not exist in this group");
        }
        repository.save(group);
    }
}
