package com.challenge.pennypilot.splitwise.authentication;

import com.challenge.pennypilot.splitwise.exception.ResourceNotFoundException;
import com.challenge.pennypilot.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserService userService;

    private OAuth2User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (OAuth2User) authentication.getPrincipal();
    }

    public String getUserEmail() {
        return getUser().getAttribute("email");
    }

    public String getName() {
        return getUser().getAttribute("name");
    }

    public long getUserID() throws ResourceNotFoundException {
//       TODO: Need to find some way to set userid to context when the user logs in
        return userService.getUserWithEmailId(getUserEmail()).getUserId();
    }
}
