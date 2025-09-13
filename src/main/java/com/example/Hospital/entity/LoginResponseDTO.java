package com.example.Hospital.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginResponseDTO {
    private String jwt;
    private String username;
}
