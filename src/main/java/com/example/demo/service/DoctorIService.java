package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Doctor;

public interface DoctorIService {
    List<Doctor> getAllDoctors();
    Optional<Doctor> getDoctorById(Integer id);
    Doctor createDoctor(Doctor doctor);
    Doctor updateDoctor(Integer id, Doctor doctorDetails);
    void deleteDoctor(Integer id);
    boolean verifyOtp(String email, int otp);
}
