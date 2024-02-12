package com.test.payment_test.config;

import com.test.payment_test.modul.Auth;
import com.test.payment_test.modul.Recipient;
import com.test.payment_test.modul.Users;
import com.test.payment_test.repository.AuthRepository;
import com.test.payment_test.repository.RecipientRepository;
import com.test.payment_test.repository.UserRepository;
import com.test.payment_test.role.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;
    private final RecipientRepository recipientRepository;
    private final AuthRepository authRepository;
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public SecurityConfig(UserRepository userRepository, RecipientRepository recipientRepository, AuthRepository authRepository) {
        this.userRepository = userRepository;
        this.recipientRepository = recipientRepository;
        this.authRepository = authRepository;
    }
//    private final LoginRepository loginRepository;

//    public SecurityConfig(LoginRepository loginRepository) {
//        this.loginRepository = loginRepository;
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        Auth auth = new Auth();
//
//        UserDetails user = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("707"))
//                .roles(String.valueOf(Role.ADMIN))
//                .build();
//        auth.setPhoneNumber(user.getUsername());
//        auth.setPassword(user.getPassword());
//        auth.setRole(Role.ADMIN);
//        authRepository.save(auth);
//        return new InMemoryUserDetailsManager(user);
//
//    }

//    @Bean
//    public UserDetailsService userDetailsRecipientService() {
//        Recipient recipients = new Recipient();
//
//        UserDetails recipient = User.builder()
//                .username("777")
//                .password(passwordEncoder.encode("777"))
//                .roles(String.valueOf(Role.RECIPIENT))
//                .build();
//        recipients.setPhoneNumberRecipient(recipient.getUsername());
//        recipients.setPassword(recipient.getPassword());
//        recipients.setRole(Role.RECIPIENT);
//        recipientRepository.save(recipients);
//        return new InMemoryUserDetailsManager(recipient);
//
//    }


    @Bean
    public UserDetailsService userDetailsUsersService() {
        Users users = new Users();

        UserDetails userDetails = User.builder().username("707")
                .password(passwordEncoder.encode("707"))
                .roles(String.valueOf(Role.SENDER))
                .build();
        users.setPhoneNumberSender(userDetails.getUsername());
        users.setPassword(passwordEncoder.encode("707"));
        users.setRole(Role.SENDER);
        userRepository.save(users);

        return new InMemoryUserDetailsManager(userDetails);

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(new AntPathRequestMatcher("/public/**")).hasAnyRole("SENDER")
                        .anyRequest().hasAnyAuthority("SENDER")
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll());
        return http.build();
    }


//    @Bean
//    public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .build();
//    }
}