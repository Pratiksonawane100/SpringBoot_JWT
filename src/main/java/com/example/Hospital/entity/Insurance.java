    package com.example.Hospital.entity;

    import com.fasterxml.jackson.annotation.JsonBackReference;

    import jakarta.persistence.*;
    import lombok.*;

    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString(exclude = "patient")
    public class Insurance {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String providerName;
        private String policyNumber;
        private String coverageDetails;

        @ManyToOne
        // @JoinColumn(name = "patient_id", referencedColumnName = "id")
        // @JsonBackReference   // ✅ child side
        @JoinColumn(name = "patient_id")
        @JsonBackReference("patient-insurance")
        private Patient patient;
    }
