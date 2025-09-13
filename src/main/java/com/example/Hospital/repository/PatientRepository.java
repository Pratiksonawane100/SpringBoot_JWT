package com.example.Hospital.repository;

import com.example.Hospital.entity.GetAllPatient;

import java.util.List;

// import org.springframework.data.jpa.repository.EntityGraph;
// import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.Hospital.entity.Patient;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient, Long> {

        // N+ 1
        // @EntityGraph(attributePaths = {"insurances", "appointments"})
     @Query(value = "SELECT * FROM Patient", nativeQuery = true)
    List<Patient> findAllPatient();

// N+1 problem
//     @Query("SELECT DISTINCT p FROM Patient p " +
//            "LEFT JOIN FETCH p.insurances " +
//            "LEFT JOIN FETCH p.appointments")
//     List<Patient> findAllWithDetails();
    
    @Query("SELECT new com.example.Hospital.entity.GetAllPatient(p.id, p.name, p.email, p.gender) " +
            "FROM Patient p WHERE p.id = :id")
    GetAllPatient findPatientById(@Param("id") Long id);

    @Query("SELECT new com.example.Hospital.entity.GetAllPatient(p.id, p.name, p.email, p.gender) " +
           "FROM Patient p WHERE p.id > :id")
    List<GetAllPatient> findPatientsGreaterThanId(Long id);

//    @Modifying
//    @Transactional
//    @Query("UPDATE Patient p SET p.name = :name, p.email = :email, p.gender = :gender WHERE p.id = :id")
//    void updatePatientById(Long id, String name, String email, String gender);
//
//
//    @Query("SELECT p.id AS id, p.name AS name, p.email AS email, p.gender AS gender " +
//            "FROM Patient p WHERE p.id = :id")
//    GetAllPatient findPatientProjectionById(Long id);
}
