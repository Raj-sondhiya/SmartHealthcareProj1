package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Doctor;

public interface DoctorIRepo extends JpaRepository<Doctor, Integer> {
    Doctor findByEmail(String email);
}
