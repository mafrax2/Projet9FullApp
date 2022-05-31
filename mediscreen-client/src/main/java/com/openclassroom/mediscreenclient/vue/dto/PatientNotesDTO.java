package com.openclassroom.mediscreenclient.vue.dto;

import com.openclassroom.mediscreenclient.model.NotesBean;
import com.openclassroom.mediscreenclient.model.PatientBean;
import lombok.Data;

import java.util.List;

@Data
public class PatientNotesDTO {

    private PatientBean patient;
    private List<NotesBean> notes;

}
