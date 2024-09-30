//package org.example.librarymanagementsystemlab.models;
//
//import java.time.LocalDate;
//
//public class Patron {
//
//    private int patron_id;
//    private String firstName;
//    private String lastName;
//    private LocalDate DOB;
//
//    public Patron() {
//    }
//
//    public Patron(int patron_id) {
//        this.patron_id = patron_id;
//    }
//
//    public Patron(String firstName, String lastName, LocalDate DOB) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.DOB = DOB;
//    }
//
//    public Patron(int patron_id, String firstName, String lastName, LocalDate DOB) {
//        this.patron_id = patron_id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.DOB = DOB;
//    }
//
//    public int getPatron_id() {
//        return patron_id;
//    }
//
//    public void setPatron_id(int patron_id) {
//        this.patron_id = patron_id;
//    }
//
//    public LocalDate getDOB() {
//        return DOB;
//    }
//
//    public void setDOB(LocalDate DOB) {
//        this.DOB = DOB;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    @Override
//    public String toString() {
//        return "Patron{" +
//                "patron_id=" + patron_id +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", DOB=" + DOB +
//                '}';
//    }
//}
//


package org.example.librarymanagementsystemlab.models;

import java.time.LocalDate;

public class Patron {
    private int patron_id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public Patron() {
    }

    public Patron(int patron_id) {
        this.patron_id = patron_id;
    }

    public Patron(int patron_id, String firstName, String lastName, String username, String password) {
        this.patron_id = patron_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public int getPatron_id() {
        return patron_id;
    }

    public void setPatron_id(int patron_id) {
        this.patron_id = patron_id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Patron{" +
                "patron_id=" + patron_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
