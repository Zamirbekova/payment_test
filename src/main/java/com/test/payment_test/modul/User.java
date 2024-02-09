package com.test.payment_test.modul;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String surNameSender;
    private String phoneNumberSender;
    private BigDecimal money;
    private String uniqueCode;


}


