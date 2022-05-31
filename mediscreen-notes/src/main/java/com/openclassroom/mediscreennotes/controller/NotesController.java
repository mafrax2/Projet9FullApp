package com.openclassroom.mediscreennotes.controller;

import com.openclassroom.mediscreennotes.model.Notes;
import com.openclassroom.mediscreennotes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class NotesController {

    @Autowired
    private NotesService service;


    @PostMapping("patHistory/add")
    public Notes addNotes(@RequestBody Notes notes){
        System.out.println("clicked add");
        System.out.println(notes);
        notes.setDate(new Date());
        Notes notes1 = service.addPatientNotes(notes);
        return notes1;
    }

    @PutMapping("patHistory/edit")
    public Notes editNotes(@RequestBody Notes notes){
        System.out.println("clicked edit");
        System.out.println(notes);
        Notes notes1 = service.editNotes(notes);
        return notes1;
    }

    @GetMapping("patHistory/find")
    public List<Notes> findNotes(@RequestParam int patientId){
        System.out.println("reached ms notes");
        System.out.println(patientId);
        return service.findNotesByPatientId(patientId);
    }

    @DeleteMapping("patHistory/delete")
    public List<Notes> deleteNotes(@RequestBody Notes notes){
        System.out.println("reached ms notes delete");
       return service.deleteNotes(notes);
    }


}
