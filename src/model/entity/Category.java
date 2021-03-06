package model.entity;

import model.dao.CategoryDAO;
import model.exception.DeleteITemException;

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
	public void deleteThis() throws DeleteITemException{
		boolean status = new CategoryDAO().delete(this.id);
		
		if(!status)
			throw new DeleteITemException(name);
		
	}

	@Override
	public void updateThis() {
		new CategoryDAO().update(this);
		
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

	@Override
	public void updateIdentify(String name) {
		this.name = name;
		this.updateThis();
	}
}
