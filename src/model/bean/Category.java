package model.bean;

public class Category implements Persistent {
	private Integer id;
	private String name;
	
	public Category(Integer id, String name){
		this.id = id;
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
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}


}
