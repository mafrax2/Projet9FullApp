package com.openclassrooms.mediscreen.service;

import com.openclassrooms.mediscreen.model.Patient;
import com.openclassrooms.mediscreen.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService{

    private final PatientRepository repository;

    public PatientServiceImpl(PatientRepository repository) {
        this.repository = repository;
    }


    @Override
    public Patient savePatient(Patient patient) {
        return repository.save(patient);
    }

    @Override
    public Patient findPatient(String family, String given) {
        return repository.findByFamilyAndGiven(family, given).stream().findFirst().get();
    }

    @Override
    public Patient findById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Patient findPatientByFamilyName(String family) {
        return repository.findByFamily(family).get();
    }
}
