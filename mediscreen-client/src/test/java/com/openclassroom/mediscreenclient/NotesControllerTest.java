package com.openclassroom.mediscreenclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassroom.mediscreenclient.controller.ClientNotesController;
import com.openclassroom.mediscreenclient.controller.ClientPatientController;
import com.openclassroom.mediscreenclient.model.NotesBean;
import com.openclassroom.mediscreenclient.service.NotesService;
import com.openclassroom.mediscreenclient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClientNotesController.class)
public class NotesControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NotesService notesService;

    @Test
    public void findNotesIT() throws Exception {
        mvc.perform(get("/patHistory/find").param("patientId", "1")).andExpect(status().isOk());
    }

    @Test
    public void addNotesIT() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        NotesBean notes = new NotesBean("notes", 1, sdf.parse("26-12-1995"));
        mvc.perform(post("/patHistory/add").contentType("application/json").accept("application/json").content(objectMapper.writeValueAsString(notes))).andExpect(status().isOk());
    }


    @Test
    public void editNotesIT() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        NotesBean notes = new NotesBean("notes3333333", 1, sdf.parse("26-12-1995"));
        mvc.perform(put("/patHistory/edit").contentType("application/json").accept("application/json").content(objectMapper.writeValueAsString(notes))).andExpect(status().isOk());
    }

    @Test
    public void deleteNotesIT() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        NotesBean notes = new NotesBean("notes3333333", 1, sdf.parse("26-12-1995"));
        mvc.perform(delete("/patHistory/delete").contentType("application/json").accept("application/json").content(objectMapper.writeValueAsString(notes))).andExpect(status().isOk());
    }

}
