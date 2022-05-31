package com.openclassroom.mediscreennotes.repository;

import com.mongodb.client.result.UpdateResult;
import com.openclassroom.mediscreennotes.model.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Repository
public class CustomNotesRepositoryImpl implements CustomNotesRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public long updateNotes(int id, Date date, String newNotes) {
        Query query = new Query(Criteria.where("patientId").is(id).and("date").is(date));
        Update update = new Update();
        update.set("notes", newNotes);

        UpdateResult result = this.mongoTemplate.updateFirst(query, update, Notes.class);

        return result.getModifiedCount();

    }

    @Override
    public boolean deleteNotes(Notes notes) {
        Query query = new Query(Criteria.where("patientId").is(notes.getPatientId()).and("date").is(notes.getDate()));
        Notes allAndRemove = this.mongoTemplate.findAndRemove(query, Notes.class);
        System.out.println("allremoved");
        System.out.println(allAndRemove);
        if (allAndRemove != null) {
            return true;
        }
        return false;
    }
}
