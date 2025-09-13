package com.example.Hospital.controller;

import com.example.Hospital.entity.Insurance;
import com.example.Hospital.service.InsuranceService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/insurances")
@CrossOrigin(origins = "*")

public class InsuranceController {

    private final InsuranceService insuranceService;

    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    // Add insurance to a specific patient
    @PostMapping("/{patientId}")
    public Insurance createInsurance(@PathVariable Long patientId, @RequestBody Insurance insurance) {
        return insuranceService.saveInsurance(patientId, insurance);
    }

    @GetMapping
    public List<Insurance> getAllInsurances() {
        return insuranceService.getAllInsurances();
    }
}
