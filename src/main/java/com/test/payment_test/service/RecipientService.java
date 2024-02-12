package com.test.payment_test.service;

import com.test.payment_test.modul.Recipient;
import com.test.payment_test.repository.RecipientRepository;
import com.test.payment_test.role.Role;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecipientService implements UserDetailsService {
    private final RecipientRepository recipientRepository;

    @Transactional
    public Recipient save(Recipient recipient) {
        return recipientRepository.save(recipient);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Recipient recipient = recipientRepository.findByPhoneNumberRecipient(username);

        if (recipient == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return recipient;
    }


}

//@Transactional
//public Recipient register(Recipient recipient) {
//        Recipient recipient1 = new Recipient();
//        UserDetails rec = User.withDefaultPasswordEncoder()
//                .username(recipient.getPhoneNumberRecipient())
//                .password(recipient.getPassword())
//                .roles(String.valueOf(Role.RECIPIENT))
//                .build();
//        recipient1.setPhoneNumberRecipient(rec.getUsername());
//        recipient1.setPassword(rec.getPassword());
//        recipient1.setRole(Role.RECIPIENT);
//        return recipientRepository.save(recipient1);
//
//    }
