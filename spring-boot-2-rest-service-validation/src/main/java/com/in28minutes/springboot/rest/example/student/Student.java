package com.in28minutes.springboot.rest.example.student;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Student {

    public final static Payload OK = new Payload() {
    };

    enum PayloadOK implements Payload {
        OK, NOT_OK
    }

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(payload = {StoneErrorHandler.class})
    @Size(min = 2, message = "Name should have atleast 2 characters")
    private String name;

    @NotNull
    @Size(min = 7, message = "Passport should have atleast 2 characters")
    private String passportNumber;

    public Student() {
        super();
    }

    public Student(Long id, String name, String passportNumber) {
        super();
        this.id = id;
        this.name = name;
        this.passportNumber = passportNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

}
