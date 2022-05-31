package com.openclassrooms.mediscreen.service;


import com.openclassrooms.mediscreen.model.Patient;
import org.springframework.stereotype.Service;

public interface PatientService {

    Patient savePatient(Patient patient);
    Patient findPatient(String family, String given);
    Patient findById(Integer id);

    Patient findPatientByFamilyName(String family);
}
