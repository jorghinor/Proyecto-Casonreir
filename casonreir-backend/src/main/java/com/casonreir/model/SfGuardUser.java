package com.casonreir.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "sf_guard_user")
@Data
public class SfGuardUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "es_persona")
    private Integer esPersona;

    @Column(name = "nivel_user", length = 250)
    private String nivelUser;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email_address;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String algorithm = "sha1";

    private String salt;

    @Column(nullable = false)
    private String password;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "is_super_admin")
    private Boolean isSuperAdmin = false;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
