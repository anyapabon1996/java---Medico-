package com.coderhouse.sessionFive.Six.Seven.integrated_challenge.repository;

import com.coderhouse.sessionFive.Six.Seven.integrated_challenge.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query(value = "SELECT * FROM patient WHERE name =:name", nativeQuery = true)
    Patient findPatientByName(String name);

}
