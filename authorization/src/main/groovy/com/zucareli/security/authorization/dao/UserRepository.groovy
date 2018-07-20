package com.zucareli.security.authorization.dao;

import com.zucareli.security.authorization.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email)
}
