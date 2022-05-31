package com.openclassroom.mediscreenclient.service;

import com.openclassroom.mediscreenclient.model.NotesBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface NotesService {


    NotesBean addNotes(NotesBean notes);
    List<NotesBean> findNotes(int patientId);
    NotesBean editNotes(NotesBean notes);
    List<NotesBean> deleteNotes(NotesBean notes);

}
