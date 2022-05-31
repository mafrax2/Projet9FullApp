package com.openclassroom.mediscreenclient.service;

import com.openclassroom.mediscreenclient.model.NotesBean;
import com.openclassroom.mediscreenclient.proxy.NotesProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class NotesServiceImpl implements NotesService {

    @Autowired
    private NotesProxy proxy;

    @Override
    public NotesBean addNotes(NotesBean notes) {
        return proxy.addNotes(notes);
    }

    @Override
    public List<NotesBean> findNotes(int patientId) {

        List<NotesBean> notes = proxy.findNotes(patientId);


        for (NotesBean note: notes
             ) {
            if(note.getNotes().contains("\n")){
                note.setNotes(note.getNotes().replace("\n", System.lineSeparator()));
            }
        }
        return notes;
    }

    @Override
    public NotesBean editNotes(NotesBean notes) {
        return proxy.editNotes(notes);
    }

    @Override
    public List<NotesBean> deleteNotes(NotesBean notes) {
        return proxy.deleteNotes(notes);
    }
}
