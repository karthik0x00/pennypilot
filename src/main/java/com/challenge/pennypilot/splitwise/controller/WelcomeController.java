package com.challenge.pennypilot.splitwise.controller;

import com.challenge.pennypilot.splitwise.authentication.AuthenticationService;
import com.challenge.pennypilot.splitwise.dto.UserDTO;
import com.challenge.pennypilot.splitwise.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/login-success")
    public String getLoginSuccessPage(HttpServletRequest request) {
        String email = authenticationService.getUserEmail();
        String name = authenticationService.getName();
        if (!userService.isUserExists(email)) {
            UserDTO userDTO = new UserDTO();
            userDTO.setEmailId(email);
            userDTO.setName(name);
            userService.createUser(userDTO);
        }
        Cookie[] cookies = request.getCookies();
        String authCookie = "";
        for (Cookie cookie: cookies) {
            String cookieName = cookie.getName();
            if ("JSESSIONID".equals(cookieName)) {
                authCookie = cookie.getValue();
            }
        }
        return "Login successful. Welcome " + name + ". Your cookie is JSESSIONID=" + authCookie;
    }
}
