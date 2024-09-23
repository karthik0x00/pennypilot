package com.challenge.pennypilot.splitwise.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class AuthUtil {
    private static OAuth2User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (OAuth2User) authentication.getPrincipal();
    }

    public static String getUserEmail() {
        return getUser().getAttribute("email");
    }

    public static String getName() {
        return getUser().getAttribute("name");
    }
}
