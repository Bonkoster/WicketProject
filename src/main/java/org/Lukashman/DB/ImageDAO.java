package org.Lukashman.DB;

import java.util.ArrayList;

import org.Lukashman.Model.Image;

public interface ImageDAO {
	
	public ArrayList<Image> getAll();
	
	public Image getOne(int id);
	
	public void addOne(Image im);
	
	public void UpdateOne(int id,Image im);
	
	public void DeleteOne(int id);
	
	public long getCount();
	
	public long getTypedCount(String type);

}
