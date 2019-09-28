package model.dao;

import java.util.ArrayList;
import model.bean.Category;
import model.bean.Persistent;
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
	public void createNew(String name) {
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
	@Override
	public void createNew(Persistent register) {
		
		
	}

	@Override
	public void delete(int idRegister) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Persistent register) {
		// TODO Auto-generated method stub
		
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
	public boolean verifyExistenceOf(String identifyCode) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public ArrayList<Persistent> findAll() {
		String sql = "SELECT * FROM ucbm_category";
		ArrayList<Persistent> list = new ArrayList<>();
		
		try {
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

}
