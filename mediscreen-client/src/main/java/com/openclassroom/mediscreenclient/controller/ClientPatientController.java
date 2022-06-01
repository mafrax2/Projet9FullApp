package com.openclassroom.mediscreenclient.controller;


import com.openclassroom.mediscreenclient.model.NotesBean;
import com.openclassroom.mediscreenclient.model.PatientBean;

import com.openclassroom.mediscreenclient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientPatientController {


    @Autowired
    private PatientService patientService;


    public ClientPatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping("find/patient/{id}")
    public ResponseEntity<PatientBean> findPatient(@PathVariable int id){
        PatientBean patientById = patientService.findPatientById(id);
        return ResponseEntity.ok(patientById);
    }

        @GetMapping("/find/patient")
    public PatientBean findPatient(@RequestParam(required = false) String family, @RequestParam(required = false) String given){
        System.out.println("clicked find333");
        PatientBean patient = new PatientBean();
        if(family!=null && !family.isBlank() && given!=null && !given.isBlank()){
            patient = patientService.findPatient(family, given);
        }

        return patient;
    }

    @PostMapping("/patient/add")
    public ResponseEntity<PatientBean> savePatient(@Valid @RequestBody PatientBean patient,  BindingResult result){
        System.out.println("clicked add");
        System.out.println(patient);
        System.out.println(result);
        PatientBean patientBean = patientService.savePatient(patient);
        return ResponseEntity.ok((patientBean));
    }

    @PutMapping("/edit/patient/{id}")
    public PatientBean editPatient(@PathVariable Integer id,@Valid @RequestBody PatientBean patient, BindingResult result){
        System.out.println("clicked edit");
        System.out.println(result);
        System.out.println(patient);
//        Patient id1 = service.findById(id);
//        id1.setFamily(patient.getFamily());
        patientService.savePatient(patient);
        return patient;
    }


}
