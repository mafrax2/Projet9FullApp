package com.openclassroom.mediscreenclient.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class PatientBean {

    private Integer id;
    @NotBlank
    private String family;
    @NotBlank
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
