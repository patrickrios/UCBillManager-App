package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Category;
import model.bean.Payment;
import model.bean.Persistent;

public class PaymentDAO implements PersistentBean {
	
	private Connection connection;
	
	public PaymentDAO() {
		this.connection = ConnectionFactory.getConnection(); 
	}

	@Override
	public void createNew(Persistent register) {
		// TODO Auto-generated method stub
		
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
		list.add(new Payment(1, "Dinheiro"));
		list.add(new Payment(2, "Cart�o de cr�dito"));
		list.add(new Payment(3,  "Cheque"));
		return list;
	}

	@Override
	public boolean verifyExistenceOf(String identifyCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createNew(String name) {
		String sql = "INSERT INTO ucbm_payments (name) VALUES ('"+name+"')";
		
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
	public ArrayList<Persistent> findAll() {
		ArrayList<Persistent> list = new ArrayList<>();
		String query = "SELECT * FROM ucbm_payments";
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				list.add(new Payment(result.getInt(1), result.getString(2)));
			}
			statement.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
