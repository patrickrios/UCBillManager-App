package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Payment;
import model.entity.Persistent;
import model.exception.RegisterAlreadyExistException;

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
	public boolean delete(int idRegister) {
		boolean ok = false;
		String sql = "DELETE FROM ucbm_payments WHERE id_payment = '"+idRegister+"'";
		try {
			PreparedStatement statement = this.connection.prepareStatement(sql);
			statement.executeUpdate();
			statement.close();
			ok = true;
		} catch (SQLException e) {
			e.printStackTrace();
			ok=false;
		}
		return ok;
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
		list.add(new Payment(2, "Cart�o de cr�dito"));
		list.add(new Payment(3,  "Cheque"));
		return list;
	}

	@Override
	public boolean verifyExistenceOf(String identifyCode) throws RegisterAlreadyExistException
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
		if(exist)
			throw new RegisterAlreadyExistException(identifyCode);
		return exist;
	}

	@Override
	public void createNew(String name) throws RegisterAlreadyExistException
	{
		try {
			verifyExistenceOf(name);
			
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
		catch(RegisterAlreadyExistException e) {
			throw new RegisterAlreadyExistException(name);
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

	@Override
	public int numberOfRegisters() {
		int total = 0;
		String sql = "SELECT COUNT(id_payment) FROM ucbm_payments LIMIT 1";
		try {
			PreparedStatement stat = this.connection.prepareStatement(sql);
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
