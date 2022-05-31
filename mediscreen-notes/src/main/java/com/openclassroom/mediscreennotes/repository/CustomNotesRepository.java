package com.openclassroom.mediscreennotes.repository;

import com.openclassroom.mediscreennotes.model.Notes;

import java.util.Date;

public interface CustomNotesRepository {

    public long updateNotes(int id, Date date, String newNotes);
    public boolean deleteNotes(Notes notes);

}
