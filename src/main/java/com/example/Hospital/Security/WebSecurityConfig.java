package com.example.Hospital.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable for REST APIs
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/patients/all", "/auth/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/patients/**").authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(Customizer.withDefaults()) // default login form
            .httpBasic(Customizer.withDefaults()); // basic auth for APIs

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        UserDetails doctor = User.withUsername("doctor")
                .password(passwordEncoder.encode("doc123"))
                .roles("DOCTOR")
                .build();

        UserDetails patient = User.withUsername("patient")
                .password(passwordEncoder.encode("patient123"))
                .roles("PATIENT")
                .build();

        return new InMemoryUserDetailsManager(admin, doctor, patient);
    }
}
