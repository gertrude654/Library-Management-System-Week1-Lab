package org.example.librarymanagementsystemlab.test;

import org.example.librarymanagementsystemlab.daos.PatronDao;
import org.example.librarymanagementsystemlab.daos.implementation.PatronDaoImpl;
import org.example.librarymanagementsystemlab.models.Patron;

import java.time.LocalDate;
import java.util.List;

public class PatronDaoManualTest {

    public static void main(String[] args) {
        PatronDao patronDao = new PatronDaoImpl();

        // Test addPatron method
        System.out.println("Adding a patron...");
        Patron patronToAdd = new Patron("John", "Doe", LocalDate.of(1990, 5, 15));
        patronDao.addPatron(patronToAdd);

        // Test updatePatron method
        System.out.println("\nUpdating a patron...");
        Patron patronToUpdate = new Patron("Jane", "Smith", LocalDate.of(1985, 9, 23));
        patronToUpdate.setPatron_id(1); // Assuming you have a known patron_id for update
        patronDao.updatePatron(patronToUpdate);

        // Test deletePatron method
        System.out.println("\nDeleting a patron...");
        int patronIdToDelete = 2; // Assuming patron with id 2 exists
        patronDao.deletePatron(patronIdToDelete);

        // Test getPatronById method
        System.out.println("\nGetting a patron by ID...");
        int patronIdToRetrieve = 3; // Assuming patron with id 3 exists
        Patron retrievedPatron = patronDao.getPatronById(patronIdToRetrieve);
        if (retrievedPatron != null) {
            System.out.println("Retrieved Patron: " + retrievedPatron);
        } else {
            System.out.println("Patron with ID " + patronIdToRetrieve + " not found.");
        }

        // Test listAllPatrons method
        System.out.println("\nListing all patrons...");
        List<Patron> allPatrons = patronDao.listAllPatrons();
        for (Patron patron : allPatrons) {
            System.out.println(patron);
        }

        // Optionally, add more test cases as needed
    }
}
