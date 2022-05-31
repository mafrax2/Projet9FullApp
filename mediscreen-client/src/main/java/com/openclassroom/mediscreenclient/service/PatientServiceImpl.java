package com.openclassroom.mediscreenclient.service;

import com.openclassroom.mediscreenclient.model.PatientBean;
import com.openclassroom.mediscreenclient.proxy.MediscreenProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private MediscreenProxy proxy;



    @Override
    public PatientBean findPatientById(int id) {
        PatientBean patientById = proxy.findPatientById(id);
        return patientById;
    }

    public PatientBean findPatient(String family, String given){
        PatientBean patient = proxy.findPatient(family, given);
        return patient;
    }

    public PatientBean savePatient(PatientBean patient){
        return proxy.savePatient(patient);
    }

}
