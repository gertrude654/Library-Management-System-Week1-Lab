package org.example.librarymanagementsystemlab.daos;

import org.example.librarymanagementsystemlab.models.Patron;

import java.sql.Connection;
import java.util.List;

public interface PatronDao {
    public void addPatron(Patron patron);
    public void updatePatron(Patron patron);
    public void deletePatron(int id);
    public Patron getPatronById(int id);
    public List<Patron> listAllPatrons();
}
