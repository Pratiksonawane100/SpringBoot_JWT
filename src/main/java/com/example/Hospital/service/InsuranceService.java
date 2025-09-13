package com.example.Hospital.service;

import com.example.Hospital.entity.Insurance;
import com.example.Hospital.entity.Patient;
import com.example.Hospital.repository.InsuranceRepository;
import com.example.Hospital.repository.PatientRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    public InsuranceService(InsuranceRepository insuranceRepository, PatientRepository patientRepository) {
        this.insuranceRepository = insuranceRepository;
        this.patientRepository = patientRepository;
    }

    public Insurance saveInsurance(Long patientId, Insurance insurance) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("Patient not found"));
        insurance.setPatient(patient);
        return insuranceRepository.save(insurance);
    }

    public List<Insurance> getAllInsurances() {
        return insuranceRepository.findAll();
    }
}
