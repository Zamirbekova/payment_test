package com.test.payment_test.repository;

import com.test.payment_test.modul.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {


    Users findByEmail(String email);

    boolean existsByEmail(String email);

    Users findUsersByUniqueCode(String code);


    Users findByUniqueCode(String code);
}
