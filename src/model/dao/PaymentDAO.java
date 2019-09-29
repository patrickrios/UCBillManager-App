package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	public void update(Persistent register) 
	{
		Payment p = (Payment)register;
		int id = p.getId();
		String name = p.getName();
		
		String sql = "UPDATE ucbm_payments SET name='"+name+"' WHERE id_payment='"+id+"'";
		
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
		list.add(new Payment(1, "Dinheiro"));
		list.add(new Payment(2, "Cartão de crédito"));
		list.add(new Payment(3,  "Cheque"));
		return list;
	}

	@Override
	public boolean verifyExistenceOf(String identifyCode) 
	{
		String sql = "SELECT * FROM ucbm_payments WHERE name LIKE '"+identifyCode+"'";
		boolean exist = true;
		
		try {
			PreparedStatement statement = this.connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			if(!result.next())
				exist = false;
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}

	@Override
	public void createNew(String name)
	{
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
	public ArrayList<Persistent> findAll() 
	{
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
