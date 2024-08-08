package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DoctorIRepo;
import com.example.demo.model.Doctor;

@Service
public class DoctorServiceImpl implements DoctorIService {

    @Autowired
    private DoctorIRepo doctorRepo;

    @Autowired
    private EmailService emailService;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }

    @Override
    public Optional<Doctor> getDoctorById(Integer id) {
        return doctorRepo.findById(id);
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        doctor.setOtp(generateOtp());
        Doctor savedDoctor = doctorRepo.save(doctor);
        emailService.sendOtpEmail(doctor.getEmail(), doctor.getOtp());
        return savedDoctor;
    }

    @Override
    public Doctor updateDoctor(Integer id, Doctor doctorDetails) {
        Optional<Doctor> optionalDoctor = doctorRepo.findById(id);

        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            doctor.setFirstName(doctorDetails.getFirstName());
            doctor.setLastName(doctorDetails.getLastName());
            doctor.setSpecialty(doctorDetails.getSpecialty());
            doctor.setPhoneNo(doctorDetails.getPhoneNo());
            doctor.setEmail(doctorDetails.getEmail());
            doctor.setPassword(doctorDetails.getPassword());
            doctor.setOtp(doctorDetails.getOtp());
            doctor.setIsVerified(doctorDetails.getIsVerified());

            return doctorRepo.save(doctor);
        } else {
            return null;
        }
    }

    @Override
    public void deleteDoctor(Integer id) {
        doctorRepo.deleteById(id);
    }

    @Override
    public boolean verifyOtp(String email, int otp) {
        Doctor doctor = doctorRepo.findByEmail(email);
        if (doctor != null && doctor.getOtp().equals(otp)) {
            doctor.setIsVerified(true);
            doctorRepo.save(doctor);
            return true;
        }
        return false;
    }

    private int generateOtp() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }
}
