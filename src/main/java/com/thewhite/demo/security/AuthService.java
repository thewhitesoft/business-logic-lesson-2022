package com.thewhite.demo.security;

import com.thewhite.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

    public User getCurrentUser() {
        throw new RuntimeException("Not implemented!");
    }
}
