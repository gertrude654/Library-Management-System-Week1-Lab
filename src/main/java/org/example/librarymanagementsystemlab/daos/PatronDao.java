//package org.example.librarymanagementsystemlab.daos;
//
//import org.example.librarymanagementsystemlab.models.Patron;
//
//import java.sql.Connection;
//import java.util.List;
//import java.util.Stack;
//
//public interface PatronDao {
//    public void addPatron(Patron patron);
//    public void updatePatron(Patron patron);
//    public void deletePatron(int id);
//    public Patron getPatronById(int id);
//    public List<Patron> listAllPatrons();
//    public boolean validatePatron(String username,String password);
//}

package org.example.librarymanagementsystemlab.daos;

import org.example.librarymanagementsystemlab.models.Patron;

import java.util.List;

public interface PatronDao {
    void addPatron(Patron patron);
    void updatePatron(Patron patron);
    void deletePatron(int id);
    Patron getPatronById(int id);
    List<Patron> listAllPatrons();
    Patron validatePatron(String username, String password);
}

