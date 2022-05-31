package com.openclassrooms.mediscreen.repository;

import com.openclassrooms.mediscreen.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Optional<Patient> findByFamilyAndGiven(String family, String given);
    Optional<Patient> findByFamily(String family);

}
