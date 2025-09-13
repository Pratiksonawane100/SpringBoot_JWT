package com.example.Hospital.service;

import com.example.Hospital.entity.Insurance;
// import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import java.util.List;
// import java.util.stream.Collectors;
// 
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.Hospital.entity.GetAllPatient;
import com.example.Hospital.entity.Patient;
import com.example.Hospital.repository.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAllPatient();
    }

    public Page<Patient> getPatientsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return patientRepository.findAll(pageable);
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

     public List<GetAllPatient> getPatientsGreaterThanId(Long id) {
        return patientRepository.findPatientsGreaterThanId(id);
    }

    public boolean deletePatientById(Long id){
        if(patientRepository.existsById(id)){
            patientRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

//       public GetAllPatient updateAndReturnPatient(Long id, String name, String email, String gender) {
//        patientRepository.updatePatientById(id, name, email, gender);
//        return patientRepository.findPatientProjectionById(id);
//    }

//    @Transactional
    public Patient addInsuranceToPatient(Long id, Insurance insurance) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

        // ✅ just append the new one
        patient.addInsurance(insurance);
//        System.out.println("size" + patient.getInsurances().size());
        return patientRepository.save(patient);
    }

    public Patient removeAppointment(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        // patient.setAppointments(null);
        // return patient;

        // patient.getAppointments()
        //    .removeIf(app -> app.getId().equals(appointmentId));  // ✅ remove by ID

        // return patientRepository.save(patient);

        patient.getAppointments().clear();  // ✅ removes all appointments
        return patientRepository.save(patient);  // persist changes
    }

}
