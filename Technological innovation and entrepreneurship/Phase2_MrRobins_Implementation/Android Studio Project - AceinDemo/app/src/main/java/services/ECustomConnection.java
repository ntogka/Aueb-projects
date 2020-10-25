package com.example.georgiosmoschovis.aceindemo.services;

/**
 * This class implements Employees and Customers log-in.
 */
public class ECustomConnection {
	com.example.georgiosmoschovis.aceindemo.domain.User user;
	boolean status;

	public ECustomConnection() {}
	
	public ECustomConnection(com.example.georgiosmoschovis.aceindemo.domain.User user) {
		this.user = user;
	}
	
	public ECustomConnection submit(String username, String password, com.example.georgiosmoschovis.aceindemo.dao.UsersDAO dao) {
		com.example.georgiosmoschovis.aceindemo.domain.User user =  dao.verify(username, password);
		ECustomConnection conn = new ECustomConnection(user);
		return conn;
	}
	
	public boolean getConnectionResult() {
		if(this.user != null) this.status = true;
			else this.status = false;
		return this.status;
	}
	
	public com.example.georgiosmoschovis.aceindemo.domain.Credentials getConnectionData() {
		return this.user.getLoginData();
	}
}
