package com.openclassroom.mediscreenclient.service;

import com.openclassroom.mediscreenclient.model.Diabetes;
import com.openclassroom.mediscreenclient.model.NotesBean;
import com.openclassroom.mediscreenclient.model.PatientBean;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
public class DiabetesServiceImpl implements DiabetesService{

        private List<String> triggers = Arrays.asList(new String[]{"Hémoglobine A1C",
                "Microalbumine",
                "Taille",
                "Poids",
                "Fumeur",
                "Anormal",
                "Cholestérol",
                "Vertige",
                "Rechute",
                "Réaction",
                "Anticorps"});




    public String assessDiabetes(List<NotesBean> notes, PatientBean patient){

        boolean moreThanThirty = isMoreThanThirty(patient);
        int i = countTriggers(notes);


        if(moreThanThirty){
            if(i>=2 && i<6){
                return "Patient : "+ patient.getFamily() +"(Age: "+getAge(patient) +") diabetes assesment is : "+ Diabetes.BORDERLINE;
            }
            else if(i>=6 && i<8){
                return "Patient : "+ patient.getFamily() +"(Age: "+getAge(patient) +") diabetes assesment is : "+Diabetes.INDANGER;
            } else if (i>=8){
                return "Patient : "+ patient.getFamily() +"(Age: "+getAge(patient) +") diabetes assesment is : "+Diabetes.EARLYONSET;
            } else {
                return "Patient : "+ patient.getFamily() +"(Age: "+getAge(patient) +") diabetes assesment is : "+ Diabetes.NONE;
            }
        } else {
            if(patient.getSex().equals("M")){
                if(i>=3 && i<5){
                    return "Patient : "+ patient.getFamily() +"(Age: "+getAge(patient) +") diabetes assesment is : "+Diabetes.INDANGER;
                } else if (i>= 5 ){
                    return "Patient : "+ patient.getFamily() +"(Age: "+getAge(patient) +") diabetes assesment is : "+Diabetes.EARLYONSET;
                } else {
                    return "Patient : "+ patient.getFamily() +"(Age: "+getAge(patient) +") diabetes assesment is : "+ Diabetes.NONE;

                }
            } else {
                if(i>=4 && i<7){
                    return "Patient : "+ patient.getFamily() +"(Age: "+getAge(patient) +") diabetes assesment is : "+Diabetes.INDANGER;
                } else if (i>= 7 ){
                    return "Patient : "+ patient.getFamily() +"(Age: "+getAge(patient) +") diabetes assesment is : "+Diabetes.EARLYONSET;
                } else {
                    return "Patient : "+ patient.getFamily() +"(Age: "+getAge(patient) +") diabetes assesment is : "+ Diabetes.NONE;

                }
            }
        }

    }

    private boolean isMoreThanThirty(PatientBean patient){

        int years = getAge(patient);

        if(years>=30){
            return true;
        }

        return false;
    }

    private int getAge(PatientBean patient) {
        Date dob = patient.getDob();
        Calendar calendar = Calendar.getInstance();
        TimeZone timeZone = calendar.getTimeZone();
        LocalDate now = LocalDate.now();
        LocalDate dobLocale = LocalDate.parse(dob.toString());
        int years = Period.between( dobLocale, now).getYears();
        return years;
    }


    private int countTriggers(List<NotesBean> notes){

        int triggerCount= 0;

        for (String trigger : triggers) {
            long count = notes.stream().filter(n -> n.getNotes().replace(" ", "").toLowerCase(Locale.ROOT).contains(trigger.toLowerCase(Locale.ROOT))).count();
            triggerCount +=count;
        }
        return triggerCount;
    }

}
