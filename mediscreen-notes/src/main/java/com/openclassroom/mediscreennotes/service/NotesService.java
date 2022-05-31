package com.openclassroom.mediscreennotes.service;

import com.openclassroom.mediscreennotes.model.Notes;
import com.openclassroom.mediscreennotes.repository.CustomNotesRepository;
import com.openclassroom.mediscreennotes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    @Autowired
    private NotesRepository repository;

    @Autowired
    private CustomNotesRepository customRepo;


    public List<Notes> findNotesByPatientId(Integer id){

        return repository.findAllByPatientId(id);
    }


    public Notes addPatientNotes(Notes notes){
        return repository.save(notes);
    }

    public Notes editNotes(Notes notes){

        customRepo.updateNotes(notes.getPatientId(), notes.getDate(), notes.getNotes());

        return notes;
    }

    public List<Notes> deleteNotes(Notes notes){

        if(customRepo.deleteNotes(notes)){
            List<Notes> allByPatientId = repository.findAllByPatientId(notes.getPatientId());
            return allByPatientId;
        }
        return null;
    }

}
