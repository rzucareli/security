package com.zucareli.security.web.domain;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column( name = "id_user" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "email" )
    @NotEmpty(message = "Email is required")
    private String email;

    @Column( name = "password" )
    @NotEmpty(message = "Password is required")
    private String password;

    @Transient
    @NotEmpty(message = "Password confirmation is required")
    private String passwordConfirmation;

    @Column(name = "enabled")
    private boolean enabled;

    @Column( name = "created_at" )
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime createdAt = LocalDateTime.now();
}
