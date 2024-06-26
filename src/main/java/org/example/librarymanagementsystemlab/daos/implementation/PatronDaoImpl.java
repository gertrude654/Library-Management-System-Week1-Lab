package org.example.librarymanagementsystemlab.daos.implementation;

import org.example.librarymanagementsystemlab.daos.PatronDao;
import org.example.librarymanagementsystemlab.models.Patron;
import org.example.librarymanagementsystemlab.tables.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PatronDaoImpl implements PatronDao {


    @Override
    public boolean validatePatron(String firstName, String password)  {
        String query = "SELECT * FROM patrons WHERE username = ? AND password =  ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, firstName);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }


    @Override
    public void addPatron(Patron patron) {
        String sql = "INSERT INTO patron(first_name, last_name, dob) VALUES (?, ?, ?)";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql) ;
            ps.setString(1, patron.getFirstName());
            ps.setString(2, patron.getLastName());
            ps.setDate(3, Date.valueOf(patron.getDOB()));
            ps.executeUpdate();
            System.out.println(" patron added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePatron(Patron patron) {
        String sql = "UPDATE patron SET first_name = ?, last_name = ?, dob = ? WHERE patron_id = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, patron.getFirstName());
            ps.setString(2, patron.getLastName());
            ps.setDate(3, Date.valueOf(patron.getDOB()));
            ps.setInt(4, patron.getPatron_id());
            ps.executeUpdate();
            System.out.println(" patron updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deletePatron(int id) {

        String sql = "DELETE FROM patron WHERE patron_id = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println(" patron deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public Patron getPatronById(int id) {
        Patron pt = new Patron();
       // Patron> retrieved = new Stack<Patron>();
        try {
            Connection con = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM patron WHERE patron_id =?";
            PreparedStatement bs = con.prepareStatement(sql);
            bs.setInt(1,id);
            ResultSet rs = bs.executeQuery();
            if (rs.next()) {

                pt.setPatron_id(rs.getInt("patron_id"));
                pt.setFirstName(rs.getString("first_name"));
                pt.setLastName(rs.getString("last_name"));
                pt.setDOB(rs.getDate("dob").toLocalDate());
            }
            //retrieved.add(pt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pt;
    }

    @Override
    public List<Patron> listAllPatrons() {
        List<Patron> Patrons = new ArrayList<>();
        String sql = "SELECT * FROM patron";
        try (
             Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Patron patron = new Patron(
                    rs.getInt("patron_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("dob").toLocalDate()
                );
                Patrons.add(patron);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Patrons;
    }

}
