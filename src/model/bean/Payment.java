package model.bean;

import model.dao.PaymentDAO;

public class Payment implements Persistent{
	private Integer id;
	private String name;
	
	public Payment(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Payment(String name) {
		this.id = null;
		this.name = name;
	}
	
	@Override
	public void createNewIfNotExists() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void deleteThis() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updateThis() {
		new PaymentDAO().update(this);
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void changeName(String name)
	{
		this.name = name;
	}
	
}
