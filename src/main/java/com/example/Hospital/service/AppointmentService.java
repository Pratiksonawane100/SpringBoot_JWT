package com.example.Hospital.service;

import java.util.List;
import java.util.Optional;

import com.example.Hospital.entity.Patient;
import com.example.Hospital.repository.PatientRepository;
import org.springframework.stereotype.Service;

import com.example.Hospital.entity.Appointment;
import com.example.Hospital.repository.AppointmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService {


    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    // ✅ Create or Update Appointment
    public Appointment saveAppointment(Long patientId, Appointment appointment) {
//        return appointmentRepository.save(appointment);
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("Patient not found"));
        appointment.setPatient(patient);
        return appointmentRepository.save(appointment);
    }

    // ✅ Get All Appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // ✅ Get Appointment by ID
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    // ✅ Delete Appointment
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
