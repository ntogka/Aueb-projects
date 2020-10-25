package com.example.georgiosmoschovis.aceindemo.dao;

/**
 * This class illustrates a sample data base object interface for Users.
 */
public interface UsersDAO {
	com.example.georgiosmoschovis.aceindemo.domain.User verify(String username, String password);
}
