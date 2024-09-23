package com.challenge.pennypilot.splitwise.service;

import com.challenge.pennypilot.splitwise.dto.GroupDTO;
import com.challenge.pennypilot.splitwise.dto.UserDTO;
import com.challenge.pennypilot.splitwise.exception.ResourceNotFoundException;
import com.challenge.pennypilot.splitwise.model.Group;
import com.challenge.pennypilot.splitwise.model.User;
import com.challenge.pennypilot.splitwise.repository.UserCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException() {
        super("User does not exist");
    }
}

@Service
public class UserService {
    @Autowired
    private UserCRUDRepository repository;

    public User getUserModelWithId(long userId) throws ResourceNotFoundException {
        Optional<User> optionalUser = repository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        return optionalUser.get();
    }

    public List<User> getUserModelsWithIds(List<Long> userIds) {
        return repository.findAllById(userIds);
    }

    public UserDTO getUserWithID(long userId) throws ResourceNotFoundException {
        User user = getUserModelWithId(userId);
        return new UserDTO(user);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setEmailId(userDTO.getEmailId());
        user.setActive(true);
        user.setName(userDTO.getName());
        return new UserDTO(repository.save(user));
    }

    public void deleteUserWithId(long userId) throws ResourceNotFoundException {
        repository.delete(getUserModelWithId(userId));
    }

    public UserDTO updateUserWithId(long userId, UserDTO userDTO) throws Exception {
        String name = userDTO.getName();
        User user = getUserModelWithId(userId);
        if (StringUtils.hasLength(name)) {
            user.setName(name);
        }
        return new UserDTO(repository.save(user));
    }

    public boolean isUserExists(long userId) {
        return repository.existsById(userId);
    }

    public boolean isUserExists(String emailId) {
        return repository.existsByEmailId(emailId);
    }

    public UserDTO getUserWithEmailId(String emailId) throws ResourceNotFoundException {
        Optional<User> optionalUser = repository.findFirstByEmailIdIgnoreCase(emailId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        return new UserDTO(optionalUser.get());
    }

//    User and Group relational API
    public List<GroupDTO> getAllGroupsBelongToUser(long userId) throws ResourceNotFoundException {
        User user = getUserModelWithId(userId);
        Set<Group> groups = user.getGroups();
        List<GroupDTO> groupDTOs = new ArrayList<>(groups.size());
        for (Group group: groups) {
            groupDTOs.add(new GroupDTO(group));
        }
        return groupDTOs;
    }
}
