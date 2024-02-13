package com.test.payment_test.repository;

import com.test.payment_test.modul.CashB;
import com.test.payment_test.modul.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashBRepository extends JpaRepository<CashB,Long> {
    CashB findCashBByUniqueCode (String uniqueCode);
    boolean existsByUniqueCode(String code);

}
