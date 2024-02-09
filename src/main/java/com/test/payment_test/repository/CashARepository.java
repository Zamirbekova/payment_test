package com.test.payment_test.repository;

import com.test.payment_test.modul.CashA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashARepository extends JpaRepository<CashA,Long> {
}
