//package org.example.librarymanagementsystemlab.daos.implementation;
//
//import org.example.librarymanagementsystemlab.daos.PatronDao;
//import org.example.librarymanagementsystemlab.models.Patron;
//import org.example.librarymanagementsystemlab.tables.DatabaseConnection;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class PatronDaoImpl implements PatronDao {
//
//
//    @Override
//    public boolean validatePatron(String firstName, String password)  {
//        String query = "SELECT * FROM patrons WHERE username = ? AND password =  ?";
//        try (Connection connection = DatabaseConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, firstName);
//            statement.setString(2, password);
//            ResultSet resultSet = statement.executeQuery();
//            return resultSet.next();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//
//    }
//
//
//    @Override
//    public void addPatron(Patron patron) {
//        String sql = "INSERT INTO patron(first_name, last_name, dob) VALUES (?, ?, ?)";
//        try {
//            Connection connection = DatabaseConnection.getConnection();
//            PreparedStatement ps = connection.prepareStatement(sql) ;
//            ps.setString(1, patron.getFirstName());
//            ps.setString(2, patron.getLastName());
//            ps.setDate(3, Date.valueOf(patron.getDOB()));
//            ps.executeUpdate();
//            System.out.println(" patron added successfully.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void updatePatron(Patron patron) {
//        String sql = "UPDATE patron SET first_name = ?, last_name = ?, dob = ? WHERE patron_id = ?";
//        try {
//            Connection connection = DatabaseConnection.getConnection();
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, patron.getFirstName());
//            ps.setString(2, patron.getLastName());
//            ps.setDate(3, Date.valueOf(patron.getDOB()));
//            ps.setInt(4, patron.getPatron_id());
//            ps.executeUpdate();
//            System.out.println(" patron updated successfully.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public void deletePatron(int id) {
//
//        String sql = "DELETE FROM patron WHERE patron_id = ?";
//        try {
//            Connection connection = DatabaseConnection.getConnection();
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, id);
//            ps.executeUpdate();
//            System.out.println(" patron deleted successfully.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    @Override
//    public Patron getPatronById(int id) {
//        Patron pt = new Patron();
//       // Patron> retrieved = new Stack<Patron>();
//        try {
//            Connection con = DatabaseConnection.getConnection();
//            String sql = "SELECT * FROM patron WHERE patron_id =?";
//            PreparedStatement bs = con.prepareStatement(sql);
//            bs.setInt(1,id);
//            ResultSet rs = bs.executeQuery();
//            if (rs.next()) {
//
//                pt.setPatron_id(rs.getInt("patron_id"));
//                pt.setFirstName(rs.getString("first_name"));
//                pt.setLastName(rs.getString("last_name"));
//                pt.setDOB(rs.getDate("dob").toLocalDate());
//            }
//            //retrieved.add(pt);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return pt;
//    }
//
//    @Override
//    public List<Patron> listAllPatrons() {
//        List<Patron> Patrons = new ArrayList<>();
//        String sql = "SELECT * FROM patron";
//        try (
//             Connection connection = DatabaseConnection.getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery()) {
//            while (rs.next()) {
//                Patron patron = new Patron(
//                    rs.getInt("patron_id"),
//                    rs.getString("first_name"),
//                    rs.getString("last_name"),
//                    rs.getDate("dob").toLocalDate()
//                );
//                Patrons.add(patron);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return Patrons;
//    }
//
//}


package org.example.librarymanagementsystemlab.daos.implementation;

import org.example.librarymanagementsystemlab.daos.PatronDao;
import org.example.librarymanagementsystemlab.models.Patron;
import org.example.librarymanagementsystemlab.tables.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatronDaoImpl implements PatronDao {


    @Override
    public Patron validatePatron(String username, String password) {
        String query = "SELECT * FROM patron WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Patron(
                        resultSet.getInt("patron_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("dob").toLocalDate(),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addPatron(Patron patron) {
        String sql = "INSERT INTO patron (first_name, last_name, dob, username, password) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, patron.getFirstName());
            ps.setString(2, patron.getLastName());
            ps.setDate(3, Date.valueOf(patron.getDOB()));
            ps.setString(4, patron.getUsername());
            ps.setString(5, patron.getPassword());
            ps.executeUpdate();
            System.out.println("Patron added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePatron(Patron patron) {
        String sql = "UPDATE patron SET first_name = ?, last_name = ?, dob = ?, username = ?, password = ? WHERE patron_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, patron.getFirstName());
            ps.setString(2, patron.getLastName());
            ps.setDate(3, Date.valueOf(patron.getDOB()));
            ps.setString(4, patron.getUsername());
            ps.setString(5, patron.getPassword());
            ps.setInt(6, patron.getPatron_id());
            ps.executeUpdate();
            System.out.println("Patron updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePatron(int id) {
        String sql = "DELETE FROM patron WHERE patron_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Patron deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Patron getPatronById(int id) {
        Patron patron = new Patron();
        String sql = "SELECT * FROM patron WHERE patron_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                patron.setPatron_id(rs.getInt("patron_id"));
                patron.setFirstName(rs.getString("first_name"));
                patron.setLastName(rs.getString("last_name"));
                patron.setDOB(rs.getDate("dob").toLocalDate());
                patron.setUsername(rs.getString("username"));
                patron.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patron;
    }

    @Override
    public List<Patron> listAllPatrons() {
        List<Patron> patrons = new ArrayList<>();
        String sql = "SELECT * FROM patron";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Patron patron = new Patron(
                        rs.getInt("patron_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getString("username"),
                        rs.getString("password")
                );
                patrons.add(patron);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patrons;
    }
}
