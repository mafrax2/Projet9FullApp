package com.openclassroom.mediscreennotes.repository;

import com.openclassroom.mediscreennotes.model.Notes;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface NotesRepository extends MongoRepository<Notes, Integer> {

        List<Notes> findAllByPatientId(int id);


}
