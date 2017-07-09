package org.Lukashman.DB;

import java.util.ArrayList;

import org.Lukashman.Model.Type;

public interface TypeDAO {
	
public ArrayList<Type> getAll();
	
	public Type getOne(int id);
	
	public void addOne(Type ty);
	
	public void UpdateOne(int id,Type ty);
	
	public void DeleteOne(int id);
	
	public long getCount();	
	
}
