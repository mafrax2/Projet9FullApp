package com.openclassroom.mediscreenclient.service;

import com.openclassroom.mediscreenclient.model.Diabetes;
import com.openclassroom.mediscreenclient.model.NotesBean;
import com.openclassroom.mediscreenclient.model.PatientBean;

import java.util.List;

public interface DiabetesService {

    String assessDiabetes(List<NotesBean> notes, PatientBean patient);
}
