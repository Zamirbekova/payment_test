package com.test.payment_test.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SenderDto {
    private String description;
    private LocalDate date;

}
