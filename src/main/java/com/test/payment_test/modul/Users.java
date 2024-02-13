package com.test.payment_test.modul;

import com.test.payment_test.role.Role;
import com.test.payment_test.valid.ValidEmail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "sender_users")
@Getter
@Setter
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String surNameSender;
    private String phoneNumberSender;
    private String surNameRecipient;
//    private String phoneNumberRecipient;
    private BigDecimal money;
    private String uniqueCode;
    private String password;
    private String matchingPassword;
    @ValidEmail
    private String email;
    @Enumerated
    private Role role;
    private String description;
    private LocalDate date;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


