package model.entity;

import model.dao.PaymentDAO;
import model.exception.DeleteITemException;

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
	public void deleteThis() throws DeleteITemException {
		boolean status = new PaymentDAO().delete(this.id);
		if(!status)
			throw new DeleteITemException(name);
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

	@Override
	public void updateIdentify(String name) {
		this.name = name;
		this.updateThis();
		
	}
	
}
