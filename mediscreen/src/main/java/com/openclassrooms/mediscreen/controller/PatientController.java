package com.openclassrooms.mediscreen.controller;

import com.fasterxml.jackson.core.JsonStreamContext;
import com.openclassrooms.mediscreen.model.Patient;
import com.openclassrooms.mediscreen.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.GeneratedValue;
import javax.validation.Valid;

@RestController
@Data
@AllArgsConstructor
public class PatientController {

    @Autowired
    private PatientService service;


    @RequestMapping("/")
    public String home(Model model)
    {
        return "home";
    }

    @GetMapping("/find/patient")
    public Patient findPatient(@RequestParam(required = false) String family, @RequestParam(required = false) String given, Model model){
        System.out.println("clicked find");
        Patient patient = new Patient();
        if(family!=null && !family.isBlank() && given!=null && !given.isBlank()){
        patient = service.findPatient(family, given);
        } else if(family!=null && !family.isBlank() ){
            patient = service.findPatientByFamilyName(family);
        }
        model.addAttribute("patient", patient);

        return patient;
    }

    @GetMapping("/find/patient/{id}")
    public Patient findPatientById(@PathVariable int id){
        Patient patient = service.findById(id);
        return patient;
    }

    @PostMapping("/patient/add")
    public ResponseEntity<Patient>  savePatient(@Valid @RequestBody Patient patient,  BindingResult result){
        System.out.println("clicked add");
        System.out.println(patient);
        System.out.println(result);
        if(!result.hasErrors()){
            Patient patientbean = service.savePatient(patient);
            return ResponseEntity.ok((patientbean));
        }

        return ResponseEntity.ok((patient));
    }

    @PutMapping("/edit/patient/{id}")
    public ResponseEntity<Patient>  editPatient(@PathVariable Integer id,@Valid @RequestBody Patient patient, BindingResult result){
        System.out.println("clicked edit");
        System.out.println(result);
        System.out.println(patient);
//        Patient id1 = service.findById(id);
//        id1.setFamily(patient.getFamily());
        if(!result.hasErrors()){
            Patient patientbean = service.savePatient(patient);
            return ResponseEntity.ok((patientbean));
        }

        return ResponseEntity.ok((patient));
    }

}
