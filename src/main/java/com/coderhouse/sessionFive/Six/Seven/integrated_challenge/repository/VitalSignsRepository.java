package com.coderhouse.sessionFive.Six.Seven.integrated_challenge.repository;

import com.coderhouse.sessionFive.Six.Seven.integrated_challenge.domain.entity.Vital_Signs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VitalSignsRepository extends JpaRepository<Vital_Signs, Integer> {

    @Query(value = "SELECT * from vital_signs where patient_id =:id", nativeQuery = true)
    public List<Vital_Signs> allVitalSignsFromPatientById(Integer id);

    @Query(value = "Delete from vital_signs where id =:id", nativeQuery = true)
    public void deleteVitlSign(Integer id);
}
