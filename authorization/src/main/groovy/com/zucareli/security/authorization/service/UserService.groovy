package com.zucareli.security.authorization.service

import com.zucareli.security.authorization.configuration.validation.EmailExistsException
import com.zucareli.security.authorization.dao.UserRepository
import com.zucareli.security.authorization.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    PasswordEncoder passwordEncoder

    @Autowired
    UserRepository userRepository

    User registerNewUser(User user) throws EmailExistsException {
        if(emailExist(user.getEmail())) {
            throw new EmailExistsException("Email ${user.email} already registered.")
        }
        user.enabled = true
        user.password = passwordEncoder.encode(user.getPassword())
        userRepository.save(user)
    }

    private boolean emailExist(String email) {
        final User user = userRepository.findByEmail(email)
        user != null
    }
}
