package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.PatientUpdateDTO;
import com.example.demo.model.Patient;

public interface PatientIService {
    List<Patient> getAllPatients();
    Optional<Patient> getPatientById(Integer id);
    Patient createPatient(Patient patient);
    Patient updatePatient(Integer id, PatientUpdateDTO patientDetails);
    void deletePatient(Integer id);
    boolean verifyOtp(String email, int otp);
}
