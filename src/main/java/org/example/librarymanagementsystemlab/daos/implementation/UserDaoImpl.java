package org.example.librarymanagementsystemlab.daos.implementation;

import org.example.librarymanagementsystemlab.daos.UserDao;
import org.example.librarymanagementsystemlab.tables.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean validateUser(String username, String password)  {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

        public boolean registerUser(String username, String password) {
            String query = "INSERT INTO user (username, password) VALUES (?, ?)";
            try {Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, password);
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


}
