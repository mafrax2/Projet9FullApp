package com.openclassroom.mediscreenclient.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PatientBean {

    private Integer id;
    @NotBlank(message = "family ne doit pas etre vide")
    private String family;
    @NotBlank(message = "given ne doit pas etre vide")
    private String given;
    private Date dob;
    private String sex;

    private String address;

    private String phone;


    public PatientBean(String family, String given, Date dob, String sex, String address, String phone) {
        this.family = family;
        this.given = given;
        this.dob = dob;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }

    public PatientBean() {
    }

}
