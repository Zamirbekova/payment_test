package com.test.payment_test.repository;

import com.test.payment_test.modul.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findBySurNameSender(String name);
    User findByPhoneNumberSender(String number);
    User findByUniqueCode(String uniqueCode);
}
