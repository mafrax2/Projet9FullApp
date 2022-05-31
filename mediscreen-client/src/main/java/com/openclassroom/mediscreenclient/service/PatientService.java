package com.openclassroom.mediscreenclient.service;


import com.openclassroom.mediscreenclient.model.PatientBean;
import com.openclassroom.mediscreenclient.proxy.MediscreenProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


public interface PatientService {

    PatientBean findPatientById(int id);
    PatientBean findPatient(String family, String given);
    PatientBean savePatient(PatientBean patient);


}
