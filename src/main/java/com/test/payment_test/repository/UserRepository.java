package com.test.payment_test.repository;

import com.test.payment_test.modul.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Users findBySurNameSender(String name);
    Users findByPhoneNumberSender(String number);
    Users findByUniqueCode(String uniqueCode);
    Users findByPhoneNumberRecipient(String number);

}
