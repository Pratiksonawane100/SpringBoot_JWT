package com.example.Hospital.controller;

import java.util.List;

import com.example.Hospital.service.AppointmentService;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import com.example.Hospital.entity.Appointment;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
//@EnableWebSecurity
public class AppointmentController {

    private final AppointmentService appointmentService;

    // ✅ Create Appointment
    @PostMapping("/patients/{patientId}/appointments")
    public ResponseEntity<Appointment> createAppointment(@PathVariable Long patientId, @RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.saveAppointment(patientId, appointment));
    }

    // ✅ Get All Appointments
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    // ✅ Get Appointment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // @PatchMapping("/removeAppointment")
    // public ResponseEntity<>

    // ✅ Update Appointment
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id,
//                                                         @RequestBody Appointment updatedAppointment) {
//        return appointmentService.getAppointmentById(id)
//                .map(existing -> {
//                    updatedAppointment.setId(existing.getId()); // keep same ID
//                    return ResponseEntity.ok(appointmentService.saveAppointment(updatedAppointment));
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }

    // ✅ Delete Appointment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
