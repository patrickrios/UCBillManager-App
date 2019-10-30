package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.util.ExpirationDate;
import model.util.ExpirationInterval;
import model.util.ExpiredRegister;

public class ExpiredDAO {
	private Connection connection;
	
	public ExpiredDAO() {
		this.connection = ConnectionFactory.getConnection();
	}
	
	public ArrayList<ExpiredRegister> loadDailyExpiration(){
		ArrayList<ExpiredRegister> list = new ArrayList<>();
		String query = "SELECT code, value, expiration FROM ucbm_register WHERE "+ExpirationInterval.DAILY+" AND paid='0'";
		query = query.replace("$today", ExpirationDate.today());
		
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet r = statement.executeQuery();
			
			while(r.next()){
				list.add(new ExpiredRegister(r.getString(1), r.getFloat(2), r.getTimestamp(3)));
			}
			statement.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<ExpiredRegister> loadWeeklyExpiration()
	{
		ArrayList<ExpiredRegister> list = new ArrayList<>();
		String query = "SELECT code, value, expiration FROM ucbm_register WHERE "+ExpirationInterval.WEEKLY+" AND paid = '0'";
	
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet r = statement.executeQuery();
			
			while(r.next()) {
				list.add(new ExpiredRegister(r.getString(1), r.getFloat(2), r.getTimestamp(3)));
			}
			statement.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<ExpiredRegister> loadMonthlyExpiration(){
		ArrayList<ExpiredRegister> list = new ArrayList<>();
		String query = "SELECT code, value, expiration FROM ucbm_register WHERE "+ExpirationInterval.MONTHLY+" AND paid='0'";
		
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet r = statement.executeQuery();
			
			while(r.next()) {
				list.add(new ExpiredRegister(r.getString(1), r.getFloat(2), r.getTimestamp(3)));
			}
			statement.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int[] numberOfExpirations()
	{
		int[] values = new int[3];
		int i = 0;
		String sql = "(SELECT count(id_register) AS count FROM ucbm_register WHERE "+ExpirationInterval.DAILY+" AND paid='0')"
				   + "UNION (SELECT count(id_register) FROM ucbm_register WHERE "+ExpirationInterval.WEEKLY+" AND paid='0') "
				   + "UNION (SELECT count(id_register) FROM ucbm_register WHERE "+ExpirationInterval.MONTHLY+" AND paid='0')";
		
		try {
			PreparedStatement s = this.connection.prepareStatement(sql);
			ResultSet r = s.executeQuery();
			while(r.next()) {
				values[i] = r.getInt(1);
				i++;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return values;
	}
}
