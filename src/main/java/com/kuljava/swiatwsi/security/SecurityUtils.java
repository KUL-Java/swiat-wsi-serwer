package com.kuljava.swiatwsi.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static Authentication getLoggedInUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
