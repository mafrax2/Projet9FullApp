package com.openclassrooms.mediscreen.controller;

import com.fasterxml.jackson.core.JsonStreamContext;
import com.openclassrooms.mediscreen.model.Patient;
import com.openclassrooms.mediscreen.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.GeneratedValue;

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
    public Patient savePatient(@RequestBody Patient patient){
        System.out.println("clicked  in ms");
        System.out.println(patient);

     service.savePatient(patient);
     return patient;
    }

    @PutMapping("/edit/patient/{id}")
    public Patient editPatient(@PathVariable Integer id, @RequestBody Patient patient){
        System.out.println("clicked edit");
        System.out.println(id);
        System.out.println(patient);
//        Patient id1 = service.findById(id);
//        id1.setFamily(patient.getFamily());
        service.savePatient(patient);
        return patient;
    }

}
