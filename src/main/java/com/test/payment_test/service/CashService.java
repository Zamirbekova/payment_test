package com.test.payment_test.service;

import com.test.payment_test.modul.CashB;

import java.util.List;

public interface CashService {
    CashB sendMoney(CashB cashA);

    List<CashB> getAll();
}

