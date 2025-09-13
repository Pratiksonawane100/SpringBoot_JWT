package com.example.Hospital.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String doctorName;       // or later link with Doctor entity
    private String department;       // e.g., Cardiology, Orthopedics
    private LocalDateTime appointmentDate;

    private String status;           // e.g., SCHEDULED, COMPLETED, CANCELLED

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy hh:mm a")
    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    @JsonBackReference   // ✅ Child side (Patient is parent)
    private Patient patient;
}
