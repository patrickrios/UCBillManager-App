package model.bean;

import model.dao.CategoryDAO;

public class Category implements Persistent {
	private Integer id;
	private String name;
	
	public Category(Integer id, String name){
		this.id = id;
		this.name = name;
	}
	
	public Category(String name){
		this.id = null;
		this.name = name;
	}

	@Override
	public void createNewIfNotExists() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteThis() {
		new CategoryDAO().delete(this.id);
		
	}

	@Override
	public void updateThis() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString(){
		return this.name;
	}
	
	public Integer getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public void changeName(String name){
		this.name = name;
	}


}
