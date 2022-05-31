package com.openclassroom.mediscreennotes.model;

import lombok.Data;

import java.util.Date;

@Data
public class Notes {

    private String notes;
    private int patientId;
    private Date date;


}
