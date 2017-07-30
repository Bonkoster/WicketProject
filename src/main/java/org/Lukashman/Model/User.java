package org.Lukashman.Model;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = -4964696433835589632L;
	
	private int Id;
	private String Username;
	private String Password;
	private String Role;
	
	public User(int id,String username, String password,String role) {
		super();
		Id = id;
		Username = username;
		Password = password;
		Role = role;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public User() {
		super();
		this.setRole("User");
	}

	public User(String username, String password) {
		super();
		Username = username;
		Password = password;
		this.Role = "User";
	}
	
	
}
