package com.example.Hospital.Security;

import com.example.Hospital.entity.LoginRequestDTO;
import com.example.Hospital.entity.LoginResponseDTO;
import com.example.Hospital.entity.SignupResponseDTO;
import com.example.Hospital.entity.User;
import com.example.Hospital.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final AuthUtil authUtil;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername() ,loginRequestDTO.getPassword())
        );
        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDTO(token, user.getUsername());
    }

    public SignupResponseDTO signup(LoginRequestDTO loginRequestDTO){
        User user = userRepository.findByUsername(loginRequestDTO.getUsername()).orElse(null);

        if(user != null) throw new IllegalArgumentException("User Already Exits");

        user = userRepository.save(User.builder()
                .username(loginRequestDTO.getUsername())
                .password(passwordEncoder.encode(loginRequestDTO.getPassword()))
                .build()
        );

        return new SignupResponseDTO(user.getId() , user.getUsername());

    }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

}
