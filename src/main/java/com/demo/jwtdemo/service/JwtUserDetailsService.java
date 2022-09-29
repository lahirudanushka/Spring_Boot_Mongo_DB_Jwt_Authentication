package com.demo.jwtdemo.service;

import com.demo.jwtdemo.repository.UserRepository;
import com.demo.jwtdemo.repository.Users;
import com.demo.jwtdemo.util.BaseException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = userRepository.findUserByUserName(username);
        if (users != null) {
            return new User(users.getUserName(), users.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

    }

    public Users registerUser(Users users) throws BaseException {
        try {
            users.setPassword(bcryptEncoder.encode(users.getPassword()));
            return userRepository.save(users);
        } catch (DuplicateKeyException e) {
            throw new BaseException("User Name Already Exist.", "User Name Already Exist.", HttpStatus.BAD_REQUEST, "400", e.getStackTrace());
        }
    }
}