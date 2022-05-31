package com.openclassroom.mediscreenclient.proxy;

import com.openclassroom.mediscreenclient.model.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@FeignClient(name="microservice-mediscreen", url = "microservice-mediscreen:8081")
public interface MediscreenProxy {

    @GetMapping("/find/patient")
    public PatientBean findPatient(@RequestParam(required = false) String family, @RequestParam(required = false) String given);

    @GetMapping("/find/patient/{id}")
    public PatientBean findPatientById(@PathVariable int id);

    @PostMapping("/patient/add")
    public PatientBean savePatient(@RequestBody PatientBean patient);

}
