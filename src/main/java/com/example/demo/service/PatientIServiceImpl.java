package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PatientIRepo;
import com.example.demo.dto.PatientUpdateDTO;
import com.example.demo.model.Patient;

@Service
public class PatientIServiceImpl implements PatientIService {

    @Autowired
    private PatientIRepo patientRepo;

    @Autowired
    private BCryptPasswordEncoder enc;

    @Autowired
    private EmailService emailService;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    @Override
    public Optional<Patient> getPatientById(Integer id) {
        return patientRepo.findById(id);
    }

    @Override
    public Patient createPatient(Patient patient) {
        patient.setOtp(generateOtp());
        patient.setPassword(enc.encode(patient.getPassword()));
        Patient savedPatient = patientRepo.save(patient);

        new Thread(() -> {
            emailService.sendOtpEmail(patient.getEmail(), patient.getOtp());
        }).start();

        return savedPatient;
    }

    @Override
    public Patient updatePatient(Integer id, PatientUpdateDTO patientDetails) {
        Optional<Patient> optionalPatient = patientRepo.findById(id);

        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            patient.setFirstName(patientDetails.getFirstName());
            patient.setLastName(patientDetails.getLastName());
            patient.setAddress(patientDetails.getAddress());
            patient.setAge(patientDetails.getAge());
            patient.setPhoneNo(patientDetails.getPhoneNo());
            if (patientDetails.getPassword() != null) {
                patient.setPassword(enc.encode(patientDetails.getPassword()));
            }

            return patientRepo.save(patient);
        } else {
            return null;
        }
    }

    @Override
    public void deletePatient(Integer id) {
        patientRepo.deleteById(id);
    }

    @Override
    public boolean verifyOtp(String email, int otp) {
        Patient patient = patientRepo.findByEmail(email);
        if (patient != null && patient.getOtp().equals(otp)) {
            patient.setIsVerified(true);
            patientRepo.save(patient);
            return true;
        }
        return false;
    }

    private int generateOtp() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

}
