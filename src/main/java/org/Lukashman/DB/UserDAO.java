package org.Lukashman.DB;

import java.util.ArrayList;

import org.Lukashman.Model.User;

public interface UserDAO {
	
	public ArrayList<User> getAll();
	
	public User getOne(String username);
	
	public void addOne(User us);
	
	public void UpdateOne(int id,User us);
	
	public void DeleteOne(int id);
}
