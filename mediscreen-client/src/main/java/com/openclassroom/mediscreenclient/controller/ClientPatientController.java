package com.openclassroom.mediscreenclient.controller;


import com.openclassroom.mediscreenclient.model.PatientBean;
import com.openclassroom.mediscreenclient.service.PatientService;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Validated
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
    public ResponseEntity<PatientBean>  savePatient(@RequestBody @Valid PatientBean patient, BindingResult result){
        System.out.println("clicked add");
        System.out.println(patient);
        System.out.println(result);
//        if(!result.hasErrors()){
            PatientBean patientBean = patientService.savePatient(patient);
            return ResponseEntity.ok((patientBean));
//        }
//        return ResponseEntity.ok(patient);
    }

    @PutMapping("/edit/patient/{id}")
    public ResponseEntity<PatientBean>  editPatient(@PathVariable Integer id, @RequestBody PatientBean patient, BindingResult result){
        System.out.println("clicked edit");
        System.out.println(result);
        System.out.println(patient);
//        Patient id1 = service.findById(id);
//        id1.setFamily(patient.getFamily());
        if(!result.hasErrors()){
            PatientBean patientBean = patientService.savePatient(patient);
            return ResponseEntity.ok((patientBean));
        }

        return ResponseEntity.ok((patient));
    }


}
