package com.openclassroom.mediscreenclient;


import com.openclassroom.mediscreenclient.model.NotesBean;
import com.openclassroom.mediscreenclient.model.PatientBean;
import com.openclassroom.mediscreenclient.proxy.MediscreenProxy;
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
public class PatientServiceTest {


    @Mock
    private MediscreenProxy proxy;

    private PatientService service;

    private List<PatientBean> patients;

    private List<NotesBean> notes;


    @BeforeEach
    private void setUpTests() throws ParseException {
        service = new PatientServiceImpl(proxy);
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
    public void findPatientTest() throws ParseException {
        when(proxy.findPatientById(1)).thenReturn(patients.get(0));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        PatientBean patientBean = new PatientBean("mongin", "marc", sdf.parse("26-12-1990"), "M", "here", "phone");
        PatientBean patientById = service.findPatientById(1);

        assertEquals(patientBean, patientById);

    }

    @Test
    public void addPatientTest() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        when(proxy.savePatient(any(PatientBean.class))).thenReturn(new PatientBean("mongin3", "marc3", sdf.parse("26-12-1990"), "M", "here", "phone"));

        PatientBean patientBean = new PatientBean("mongin3", "marc3", sdf.parse("26-12-1990"), "M", "here", "phone");
        PatientBean patientBean1 = service.savePatient(patientBean);

        assertEquals(patientBean, patientBean1);

    }




}
