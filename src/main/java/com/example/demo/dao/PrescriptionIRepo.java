package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Prescription;

public interface PrescriptionIRepo extends JpaRepository<Prescription, Integer> {

}
