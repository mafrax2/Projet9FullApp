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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;



@RunWith(SpringRunner.class)
@SpringBootTest
public class DiabetesServiceTest {

    @Autowired
    private DiabetesService diabetesService;

    private List<PatientBean> patients;

    private List<List<NotesBean>> notes;


    @BeforeEach
    private void setUpTests() throws ParseException {
        notes = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Date dob = sdf.parse("26-12-1990");
        PatientBean patientBean = new PatientBean();
        patientBean.setFamily("mongin");
        patientBean.setGiven("marc");
        patientBean.setDob(sdf.parse("26-12-1990"));
        patientBean.setSex("M");
        patientBean.setAddress("here");
        patientBean.setPhone("phone");
        PatientBean patientBean2 = new PatientBean("mongin2", "marc2", sdf.parse("26-12-1995"), "M", "here", "phone");
        PatientBean patientBean3 = new PatientBean("mongin3", "marc3", sdf.parse("26-12-1995"), "F", "here", "phone");

        patients = new ArrayList<>();
        patients.add(patientBean);
        patients.add(patientBean2);
        patients.add(patientBean3);

        List<NotesBean> notes1 = new ArrayList<>();
        NotesBean note1p1 = new NotesBean();
        note1p1.setNotes("note");
        note1p1.setPatientId(1);
        note1p1.setDate(sdf.parse("26-12-2025"));
        NotesBean note1p2 = new NotesBean("note2", 1, sdf.parse("27-12-2025"));
        notes1.add(note1p1);
        notes1.add(note1p2);

        notes.add(notes1);

        List<NotesBean> notes2 = new ArrayList<>();
        NotesBean note2p1 = new NotesBean("Fumeur", 1, sdf.parse("26-12-2025"));
        NotesBean note2p2 = new NotesBean("Fumeur", 1, sdf.parse("27-12-2025"));
        notes2.add(note2p1);
        notes2.add(note2p2);

        notes.add(notes2);

        List<NotesBean> notes3 = new ArrayList<>();
        NotesBean note3p1 = new NotesBean("Fumeur", 1, sdf.parse("26-12-2025"));
        NotesBean note3p2 = new NotesBean("Fumeur", 1, sdf.parse("27-12-2025"));
        NotesBean note3p3 = new NotesBean("Fumeur", 1, sdf.parse("27-12-2025"));
        NotesBean note3p4 = new NotesBean("Fumeur", 1, sdf.parse("27-12-2025"));
        NotesBean note3p5 = new NotesBean("Fumeur", 1, sdf.parse("27-12-2025"));
        NotesBean note3p6 = new NotesBean("Fumeur", 1, sdf.parse("27-12-2025"));
        notes3.add(note3p1);
        notes3.add(note3p2);
        notes3.add(note3p3);
        notes3.add(note3p4);
        notes3.add(note3p5);
        notes3.add(note3p6);

        notes.add(notes3);

        List<NotesBean> notes4 = new ArrayList<>();
        NotesBean note4p1 = new NotesBean("Fumeur", 1, sdf.parse("26-12-2025"));
        NotesBean note4p2 = new NotesBean("Fumeur", 1, sdf.parse("27-12-2025"));
        NotesBean note4p3 = new NotesBean("Fumeur", 1, sdf.parse("27-12-2025"));
        NotesBean note4p4 = new NotesBean("Fumeur", 1, sdf.parse("27-12-2025"));
        NotesBean note4p5 = new NotesBean("Fumeur", 1, sdf.parse("27-12-2025"));
        NotesBean note4p6 = new NotesBean("Fumeur", 1, sdf.parse("27-12-2025"));
        NotesBean note4p7 = new NotesBean("Fumeur", 1, sdf.parse("27-12-2025"));
        NotesBean note4p8 = new NotesBean("Fumeur", 1, sdf.parse("27-12-2025"));
        notes4.add(note4p1);
        notes4.add(note4p2);
        notes4.add(note4p3);
        notes4.add(note4p4);
        notes4.add(note4p5);
        notes4.add(note4p6);
        notes4.add(note4p7);
        notes4.add(note4p8);

        notes.add(notes4);

        List<NotesBean> notes5 = new ArrayList<>();
        NotesBean note5p1 = new NotesBean("Fumeur", 1, sdf.parse("26-12-2025"));
        NotesBean note5p2 = new NotesBean("Fumeur", 1, sdf.parse("27-12-2025"));
        NotesBean note5p3 = new NotesBean("Fumeur", 1, sdf.parse("27-12-2025"));
        notes5.add(note5p1);
        notes5.add(note5p2);
        notes5.add(note5p3);

        notes.add(notes5);



    }

    @Test
    public void assessDiabetesMaleMoreThanThirtyNoneTest() {
        String s = diabetesService.assessDiabetes(notes.get(0), patients.get(0));

        assertEquals("Patient : mongin(Age: 31) diabetes assesment is : NONE", s);

    }

    @Test
    public void assessDiabetesMaleMoreThanThirtyBorderlineTest() {
        String s = diabetesService.assessDiabetes(notes.get(1), patients.get(0));

        assertEquals("Patient : mongin(Age: 31) diabetes assesment is : BORDERLINE", s);

    }

    @Test
    public void assessDiabetesMaleMoreThanThirtyInDangerTest() {
        String s = diabetesService.assessDiabetes(notes.get(2), patients.get(0));

        assertEquals("Patient : mongin(Age: 31) diabetes assesment is : INDANGER", s);

    }

    @Test
    public void assessDiabetesMaleMoreThanThirtyEarlyOnSetTest() {
        String s = diabetesService.assessDiabetes(notes.get(3), patients.get(0));

        assertEquals("Patient : mongin(Age: 31) diabetes assesment is : EARLYONSET", s);

    }

    @Test
    public void assessDiabetesMaleUnderThirtyNoneTest() {
        String s = diabetesService.assessDiabetes(notes.get(0), patients.get(1));

        assertEquals("Patient : mongin2(Age: 26) diabetes assesment is : NONE", s);

    }


    @Test
    public void assessDiabetesMaleUnderThirtyInDangerTest() {
        String s = diabetesService.assessDiabetes(notes.get(4), patients.get(1));

        assertEquals("Patient : mongin2(Age: 26) diabetes assesment is : INDANGER", s);

    }

    @Test
    public void assessDiabetesMaleUnderThirtyEarlyOnSetTest() {
        String s = diabetesService.assessDiabetes(notes.get(2), patients.get(1));

        assertEquals("Patient : mongin2(Age: 26) diabetes assesment is : EARLYONSET", s);

    }

    @Test
    public void assessDiabetesFeMaleUnderThirtyNoneTest() {
        String s = diabetesService.assessDiabetes(notes.get(0), patients.get(2));

        assertEquals("Patient : mongin3(Age: 26) diabetes assesment is : NONE", s);

    }


    @Test
    public void assessDiabetesFeMaleUnderThirtyInDangerTest() {
        String s = diabetesService.assessDiabetes(notes.get(2), patients.get(2));

        assertEquals("Patient : mongin3(Age: 26) diabetes assesment is : INDANGER", s);

    }

    @Test
    public void assessDiabetesFeMaleUnderThirtyEarlyOnSetTest() {
        String s = diabetesService.assessDiabetes(notes.get(3), patients.get(2));

        assertEquals("Patient : mongin3(Age: 26) diabetes assesment is : EARLYONSET", s);

    }



}
