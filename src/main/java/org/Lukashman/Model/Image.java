package org.Lukashman.Model;

import java.util.Date;

public class Image {

	private int id;
	private String title;
	private String author;
	private String link;
	private Date sub_date;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public Date getSub_date() {
		return sub_date;
	}
	
	public void setSub_date(Date sub_date) {
		this.sub_date = sub_date;
	}
	
	public Image(int id, String title, String author, String link) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.link = link;
		sub_date = new Date();
	}
	
	public Image() {
		super();
		sub_date = new Date();
	}
	
	
	
}
