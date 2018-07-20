package com.zucareli.security.authorization.domain;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "user")
class User {

    @Id
    @Column( name = "id_user" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Column( name = "email" )
    @NotEmpty(message = "Email is required")
    String email

    @Column( name = "password" )
    @NotEmpty(message = "Password is required")
    String password

    @Transient
    @NotEmpty(message = "Password confirmation is required")
    String passwordConfirmation

    @Column(name = "enabled")
    boolean enabled

    @Column( name = "created_at" )
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    LocalDateTime createdAt = LocalDateTime.now();
}
