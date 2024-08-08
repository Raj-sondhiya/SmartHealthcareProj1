package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.MedicalRecord;

public interface MedicalrecordIRepo extends JpaRepository<MedicalRecord, Integer> {

}
