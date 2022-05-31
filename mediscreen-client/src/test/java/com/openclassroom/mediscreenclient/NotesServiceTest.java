package com.openclassroom.mediscreenclient;

import com.openclassroom.mediscreenclient.model.NotesBean;
import com.openclassroom.mediscreenclient.model.PatientBean;
import com.openclassroom.mediscreenclient.proxy.MediscreenProxy;
import com.openclassroom.mediscreenclient.proxy.NotesProxy;
import com.openclassroom.mediscreenclient.service.NotesService;
import com.openclassroom.mediscreenclient.service.NotesServiceImpl;
import com.openclassroom.mediscreenclient.service.PatientService;
import com.openclassroom.mediscreenclient.service.PatientServiceImpl;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NotesServiceTest {


    @Mock
    private MediscreenProxy mediscreenProxy;
    @Mock
    private NotesProxy notesProxy;

    private PatientService patientService;
    private NotesService notesService;

    private List<PatientBean> patients;

    private List<NotesBean> notes;


    @BeforeEach
    private void setUpTests() throws ParseException {
        patientService = new PatientServiceImpl(mediscreenProxy);
        notesService = new NotesServiceImpl(notesProxy);
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
    public void findNotesTest() throws ParseException {
        when(mediscreenProxy.findPatientById(1)).thenReturn(patients.get(0));
        when(notesProxy.findNotes(any(Integer.class))).thenReturn(notes);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        List<NotesBean> notes = notesService.findNotes(1);

        List<NotesBean> notes1 = new ArrayList<>();
        NotesBean note = new NotesBean("note", 1, sdf.parse("26-12-2025"));
        NotesBean note2 = new NotesBean("note2", 1, sdf.parse("27-12-2025"));
        notes1.add(note);
        notes1.add(note2);

        assertEquals(notes1, notes);

    }

    @Test
    public void addPatientTest() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        when(mediscreenProxy.findPatientById(1)).thenReturn(patients.get(0));
        when(notesProxy.findNotes(any(Integer.class))).thenReturn(notes);
        NotesBean note3 = new NotesBean("note3", 1, sdf.parse("26-12-2025"));
        when(notesProxy.addNotes(note3)).thenReturn(note3);


        NotesBean notesBean = notesService.addNotes(note3);


        assertEquals(note3, notesBean);
    }






}
