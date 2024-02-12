package com.test.payment_test.modul;

import com.test.payment_test.role.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "auth")
@Getter
@Setter
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String phoneNumber;
    private String password;
    @Enumerated
    private Role role;
}
