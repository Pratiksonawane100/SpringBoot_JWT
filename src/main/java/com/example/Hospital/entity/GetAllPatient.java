package com.example.Hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetAllPatient {

    private Long id;
    private String name;
    private String email;
    private String gender;
    // private LocalDate birthDate;


    // public GetAllPatient() {
    // }

    // public GetAllPatient(Long id, String name) {
    //     this.id = id;
    //     this.name = name;
    // }

    // // Getters & Setters
    // public Long getId() {
    //     return id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    // public String getName() {
    //     return name;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }
}
