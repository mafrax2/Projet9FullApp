package com.openclassroom.mediscreenclient.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class NotesBean {

    @NotNull
    private String notes;
    private int patientId;
    private Date date;


    public NotesBean(String notes, int patientId, Date date) {
        this.notes = notes;
        this.patientId = patientId;
        this.date = date;
    }
}
