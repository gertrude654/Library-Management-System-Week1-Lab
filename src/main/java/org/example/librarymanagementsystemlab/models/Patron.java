package org.example.librarymanagementsystemlab.models;

import java.time.LocalDate;

public class Patron {

    private int patron_id;
    private String firstName;
    private String lastName;
    private LocalDate DOB;

    public Patron() {
    }

    public Patron(int patron_id) {
        this.patron_id = patron_id;
    }

    public Patron(int patron_id, String firstName, String lastName, LocalDate DOB) {
        this.patron_id = patron_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
    }

    public int getPatron_id() {
        return patron_id;
    }

    public void setPatron_id(int patron_id) {
        this.patron_id = patron_id;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

