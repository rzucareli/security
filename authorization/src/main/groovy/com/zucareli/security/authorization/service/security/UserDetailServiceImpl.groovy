package com.zucareli.security.authorization.service.security

import com.zucareli.security.authorization.dao.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailServiceImpl implements UserDetailsService {

    private static final String ROLE_ADMIN = "ROLE_ADMIN"

    @Autowired
    private UserRepository userRepository

    @Override
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.zucareli.security.authorization.domain.User user = userRepository.findByEmail(email)
        if(user == null) {
            throw new UsernameNotFoundException("No user found with username: " + email)
        }

        return new User(user.email, user.password, user.enabled, true, true, true, getAuthorities(ROLE_ADMIN))
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        [new SimpleGrantedAuthority(role)]
    }

}
