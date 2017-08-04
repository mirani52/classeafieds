package com.hackathon.classeafieds.service;

import com.hackathon.classeafieds.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    void save(User user);
    void delete(User user);
}
