package com.openclassroom.mediscreenclient.controller;

import com.openclassroom.mediscreenclient.model.Diabetes;
import com.openclassroom.mediscreenclient.model.NotesBean;
import com.openclassroom.mediscreenclient.model.PatientBean;
import com.openclassroom.mediscreenclient.service.DiabetesService;
import com.openclassroom.mediscreenclient.service.NotesService;
import com.openclassroom.mediscreenclient.service.PatientService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.*;

@RestController
public class ClientAppController {

    @Autowired
    private NotesService notesService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DiabetesService diabetesService;

    @Autowired
    ResourceLoader resourceLoader;


    @GetMapping("/assess/{id}")
    public String diabetesAssesmentById(@PathVariable int id){
        System.out.println("clicked assess");
        PatientBean patientById = patientService.findPatientById(id);
        List<NotesBean> notes = notesService.findNotes(id);

        String s = diabetesService.assessDiabetes(notes, patientById);
        System.out.println(s);
        return s;
    }

    @GetMapping("/populate")
    public void populateNotes() throws IOException {

        Resource resource = resourceLoader.getResource("classpath:Copie_de_Notes_du_praticien_P9.xlsx");
        InputStream inputStream = resource.getInputStream();

        Workbook workbook = new XSSFWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<String>());
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        data.get(i).add(cell.getRichStringCellValue().getString());
                        break;
                    default : break;
                }
            }
            i++;
        }


        Collection<List<String>> values = data.values();

        for (List<String> value: values
             ) {
            PatientBean patient = patientService.findPatient(value.get(0), null);
            notesService.addNotes(new NotesBean(value.get(1), patient.getId(), new Date()));
        }


    }



}
