package com.test.payment_test.repository;

import com.test.payment_test.modul.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
    boolean existsByEmail(String email);
    Auth findByEmail(String email);
}
