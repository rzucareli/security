package com.zucareli.security.service;

import com.zucareli.security.persistence.UserRepository;
import com.zucareli.security.validation.EmailExistsException;
import com.zucareli.security.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User registerNewUser(User user) throws EmailExistsException {
        if(emailExist(user.getEmail())) {
            throw new EmailExistsException("Email " + user.getEmail() + " already registered.");
        }
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private boolean emailExist(String email) {
        final User user = userRepository.findByEmail(email);
        return user != null;
    }
}
