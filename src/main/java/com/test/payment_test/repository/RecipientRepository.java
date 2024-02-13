package com.test.payment_test.repository;

import com.test.payment_test.modul.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient,Long> {
//    Recipient findByPhoneNumberRecipient(String number);
    Recipient getRecipientByUniqueCode(String code);
}
