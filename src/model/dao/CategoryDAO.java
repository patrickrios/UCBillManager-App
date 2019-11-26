package model.dao;

import java.util.ArrayList;

import model.entity.Category;
import model.entity.Persistent;
import model.exception.RegisterAlreadyExistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDAO implements PersistentBean {

	private Connection connection;
	
	public CategoryDAO() {
		this.connection = ConnectionFactory.getConnection();
	}
	

	@Override
	public void createNew(String name) throws RegisterAlreadyExistException {
		
		try{
			verifyExistenceOf(name);
			
			String sql = "INSERT INTO ucbm_category (name) VALUES ('"+name+"')";
			
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.executeUpdate();
				statement.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		catch(RegisterAlreadyExistException e) {
			throw new RegisterAlreadyExistException(name);
		}	
	}
	
	@Override
	public void createNew(Persistent register) {
		
		
	}

	@Override
	public void delete(int idRegister) {
		String sql = "DELETE FROM ucbm_category WHERE id_category = '"+idRegister+"'";
		
		try {
			PreparedStatement statement = this.connection.prepareStatement(sql);
			statement.executeUpdate();
			statement.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Persistent register) {
		Category c = (Category)register;
		int id = c.getId();
		String name = c.getName();
		
		String sql = "UPDATE ucbm_category SET name='"+name+"' WHERE id_category='"+id+"'";
		
		try {
			PreparedStatement statement = this.connection.prepareStatement(sql);
			statement.executeUpdate();
			statement.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public ArrayList<Persistent> findGroup(int offset, int limit) {
		ArrayList<Persistent> list = new ArrayList<>();
		list.add(new Category(1, "Transporte"));
		list.add(new Category(2, "Alimentação"));
		list.add(new Category(3,  "Internet"));
		return list;
	}

	@Override
	public boolean verifyExistenceOf(String identifyCode) throws RegisterAlreadyExistException{
		
		String sql = "SELECT * FROM ucbm_category WHERE name LIKE '"+identifyCode+"'";
		boolean exist = true;
		
		try {
			PreparedStatement statement = this.connection.prepareStatement(sql);
			ResultSet r = statement.executeQuery();
			
			if(!r.next()) 
				exist = false;
			
			statement.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(exist) {
			throw new RegisterAlreadyExistException(identifyCode);
		}
		return exist;
	}


	@Override
	public ArrayList<Persistent> findAll() {
		String sql = "SELECT * FROM ucbm_category";
		ArrayList<Persistent> list = new ArrayList<>();
		
		try 
		{
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet r = statement.executeQuery();
			
			while(r.next()){
				list.add(new Category(r.getInt(1), r.getString(2)));
			}
			
			statement.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public int numberOfRegisters() {
		int total = 0;
		String query = "SELECT COUNT(id_category) FROM ucbm_category LIMIT 1";
		try {
			PreparedStatement stat = this.connection.prepareStatement(query);
			ResultSet r = stat.executeQuery();
			while(r.next()) {
				total = r.getInt(1);
				break;
			}
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
}
