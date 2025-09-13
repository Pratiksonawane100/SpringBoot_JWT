package com.example.Hospital.controller;

import java.util.List;
// import java.util.Map;

import com.example.Hospital.entity.Insurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;   // ✅ correct Page import

import com.example.Hospital.entity.GetAllPatient;
import com.example.Hospital.entity.Patient;
import com.example.Hospital.service.PatientService;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "*")
// @EnableWebSecurity
public class HospitalController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/all")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Patient>> getPaginatedPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(patientService.getPatientsWithPagination(page, size));
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @GetMapping("/greater/{id}")
    public List<GetAllPatient> getPatientsGreaterThanId(@PathVariable Long id) {
        return patientService.getPatientsGreaterThanId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        boolean deleted = patientService.deletePatientById(id);

        if (deleted) {
            return ResponseEntity.ok("Patient with ID " + id + " deleted successfully!");
        } else {
            return ResponseEntity.status(404).body("Patient with ID " + id + " not found.");
        }
    }

    @PostMapping("/{id}/insurances")
    public ResponseEntity<Patient> addInsurance(@PathVariable Long id, @RequestBody Insurance insurance) {
        Patient updatedPatient = patientService.addInsuranceToPatient(id, insurance);
        return ResponseEntity.ok(updatedPatient);
    }

    @PutMapping("/removeAppointment/{id}")
    public ResponseEntity<Patient> removeAppo(@PathVariable Long id){
        Patient updatedPatient = patientService.removeAppointment(id);
        return ResponseEntity.ok(updatedPatient);
    }

}
