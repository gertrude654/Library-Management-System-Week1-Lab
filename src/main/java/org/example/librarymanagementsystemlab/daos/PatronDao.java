package org.example.librarymanagementsystemlab.daos;

import org.example.librarymanagementsystemlab.models.Patron;

import java.sql.Connection;
import java.util.List;
import java.util.Stack;

public interface PatronDao {
    public void addPatron(Patron patron);
    public void updatePatron(Patron patron);
    public void deletePatron(int id);
    public Stack<Patron> getPatronById(int id);
    public List<Patron> listAllPatrons();
}
