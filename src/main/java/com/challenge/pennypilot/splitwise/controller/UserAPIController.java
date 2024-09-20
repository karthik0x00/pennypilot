package com.challenge.pennypilot.splitwise.controller;

import com.challenge.pennypilot.splitwise.exception.ResourceNotFoundException;
import com.challenge.pennypilot.splitwise.dto.UserDTO;
import com.challenge.pennypilot.splitwise.response.SuccessResponse;
import com.challenge.pennypilot.splitwise.service.UserService;
import com.challenge.pennypilot.splitwise.utils.URIUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserAPIController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<SuccessResponse> createUser(@RequestBody UserDTO user) throws Exception {
        user.validate();
        UserDTO userDTO = userService.createUser(user);
        return ResponseEntity.created(URIUtil.buildURIFromId(userDTO.getUserId())).body(new SuccessResponse("User Created Successfully"));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDTO> getUserWithId(@PathVariable long userId) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(userService.getUserWithID(userId));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDTO> updateUserWithId(@PathVariable long userId, @RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok().body(userService.updateUserWithId(userId, user));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<SuccessResponse> deleteUserWithId(@PathVariable long userId) throws ResourceNotFoundException {
        userService.deleteUserWithId(userId);
        return ResponseEntity.ok().body(new SuccessResponse("User Deleted Successfully"));
    }
}
