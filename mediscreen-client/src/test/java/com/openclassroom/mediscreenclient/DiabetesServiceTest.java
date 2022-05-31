package com.openclassroom.mediscreenclient;

import com.openclassroom.mediscreenclient.model.Diabetes;
import com.openclassroom.mediscreenclient.model.NotesBean;
import com.openclassroom.mediscreenclient.model.PatientBean;
import com.openclassroom.mediscreenclient.proxy.MediscreenProxy;
import com.openclassroom.mediscreenclient.proxy.NotesProxy;
import com.openclassroom.mediscreenclient.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;



@RunWith(SpringRunner.class)
@SpringBootTest
public class DiabetesServiceTest {


    private DiabetesService diabetesService;

    private List<PatientBean> patients;

    private List<NotesBean> notes;


    @BeforeEach
    private void setUpTests() throws ParseException {

        diabetesService = new DiabetesServiceImpl();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        PatientBean patientBean = new PatientBean("mongin", "marc", sdf.parse("26-12-1990"), "M", "here", "phone");
        PatientBean patientBean2 = new PatientBean("mongin1", "marc2", sdf.parse("26-12-1990"), "M", "here", "phone");

        patients = new ArrayList<>();
        patients.add(patientBean);
        patients.add(patientBean2);

        notes = new ArrayList<>();
        NotesBean note = new NotesBean("note", 1, sdf.parse("26-12-2025"));
        NotesBean note2 = new NotesBean("note2", 1, sdf.parse("27-12-2025"));
        notes.add(note);
        notes.add(note2);

    }

    @Test
    private void assessDiabetesTest(){
        String s = diabetesService.assessDiabetes(notes, patients.get(0));

        assertEquals("Patient : mongin (Age: 31) diabetes assesment is : none", s);

    }



}
