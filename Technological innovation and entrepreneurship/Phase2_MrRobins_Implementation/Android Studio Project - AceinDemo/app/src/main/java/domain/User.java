package com.example.georgiosmoschovis.aceindemo.domain;

/**
 * This class illustrates a generic app user.
 */
public class User {
	/* Domain Model: Instance variables */
    Credentials loginData;

    public User() {}
    
    /* Overloaded Constructors */
    public User(String username, String password) {
        Credentials creds = new Credentials(username, password);
        this.loginData = creds;
    }

    public User(Credentials loginData) {
        this.loginData = loginData;
    }

    /* Getters */
    public Credentials getLoginData() {
        return loginData;
    }
}
