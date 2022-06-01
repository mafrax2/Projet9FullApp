package com.openclassroom.mediscreenclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassroom.mediscreenclient.controller.ClientPatientController;
import com.openclassroom.mediscreenclient.model.PatientBean;
import com.openclassroom.mediscreenclient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ClientPatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PatientService patientService;

    @Test
    public void findPatientIT() throws Exception {
        mvc.perform(get("/find/patient/{id}", 1)).andExpect(status().isOk());
    }

    @Test
    public void findPatientByNameIT() throws Exception {
        mvc.perform(get("/find/patient", 1).param("family", "mongin").param("given", "marc")).andExpect(status().isOk());
    }

    @Test
    public void addPatientIT() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        PatientBean patientBean2 = new PatientBean("mongin2", "marc2", sdf.parse("26-12-1995"), "M", "here", "phone");
        mvc.perform(post("/patient/add").contentType("application/json").accept("application/json").content(objectMapper.writeValueAsString(patientBean2))).andExpect(status().isOk());
    }

    @Test
    public void editPatientIT() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        PatientBean patientBean2 = new PatientBean("mongin2", "marc3", sdf.parse("26-12-1995"), "M", "here", "phone");
        mvc.perform(put("/edit/patient/{id}", 1).contentType("application/json").accept("application/json").content(objectMapper.writeValueAsString(patientBean2))).andExpect(status().isOk());
    }


}
