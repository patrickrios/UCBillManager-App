package model.bean;

import java.sql.Timestamp;

public class Register implements Persistent{
	private Integer id;
	private String code;
	private Category category;
	private Payment payment;
	private float value;
	private boolean paid;
	private int parcel;
	private Timestamp expirationDate;
	private Timestamp inclusionDate;
	private int type;
	
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
		// TODO Auto-generated method stub
		
	}
	
}
