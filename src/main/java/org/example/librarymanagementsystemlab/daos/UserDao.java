package org.example.librarymanagementsystemlab.daos;

public interface UserDao {
    public  boolean validateUser(String username, String password);
    public  boolean registerUser(String username, String password);
}
