package com.test.payment_test.repository;

import com.test.payment_test.modul.CashB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashBRepository extends JpaRepository<CashB,Long> {
    CashB findCashBByUniqueCode (String uniqueCode);
    CashB findByPhoneNumberRecipient(String number);

    CashB findByUniqueCode(String code);

    boolean existsByUniqueCode(String code);

}
