package com.test.payment_test.modul;

import com.test.payment_test.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cashA")
@Getter
@Setter
public class CashA {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String surNameSender;
    private String phoneNumberSender;
    private String surNameRecipient;
    private String phoneNumberRecipient;
    private String description;
    private LocalDate date;
    private String uniqueCode;
    private BigDecimal money;
    @OneToOne
    private Users user;
    @Enumerated
    private Status status;


}
