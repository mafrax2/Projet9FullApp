package com.openclassroom.mediscreenclient.controller;


import com.openclassroom.mediscreenclient.model.NotesBean;
import com.openclassroom.mediscreenclient.model.PatientBean;
import com.openclassroom.mediscreenclient.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ClientNotesController {

    @Autowired
    private NotesService service;


    @PostMapping("patHistory/add")
    public NotesBean addNotes(@RequestBody NotesBean notes){
        System.out.println("clicked add");
        System.out.println(notes);
        notes.setDate(new Date());
        NotesBean notes1 = service.addNotes(notes);
        return notes;
    }

    @GetMapping("patHistory/find")
    public List<NotesBean> findNotes(@RequestParam int patientId){
        System.out.println("cliecked find notes");
        System.out.println(patientId);
        return service.findNotes(patientId);
    }

    @PutMapping("/patHistory/edit")
    public NotesBean editNotes(@RequestBody NotesBean notes){
        System.out.println("clicked edit");
        NotesBean notesBean = service.editNotes(notes);
        return notesBean;
    }

    @DeleteMapping("patHistory/delete")
    public List<NotesBean> deleteNotes(@RequestBody NotesBean notes){
        System.out.println("clicked delete");
        return service.deleteNotes(notes);
    }


}
