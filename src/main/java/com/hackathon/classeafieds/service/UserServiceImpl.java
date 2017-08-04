package com.hackathon.classeafieds.service;

import com.hackathon.classeafieds.dao.RoleDao;
import com.hackathon.classeafieds.dao.UserDao;
import com.hackathon.classeafieds.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleDao.findOne(1L));
        user.setEnabled(true);
        userDao.save(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Load the user from the database (throw an exception if not found
        User user = userDao.findByEmail(email);
        if(user == null)
            throw new UsernameNotFoundException("Email not found");

        // Return user object
        return user;
    }
}
