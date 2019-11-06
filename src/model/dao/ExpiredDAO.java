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
	
	public ArrayList<ExpiredRegister> loadExpiration(String interval){
		ArrayList<ExpiredRegister> list = new ArrayList<>();
		String query = "SELECT code, value, expiration FROM ucbm_register WHERE "+interval+" AND paid='0'";
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
	
	public int[] numberOfExpirations()
	{
		int[] values = new int[3];
		
		String query1 = "SELECT count(id_register) FROM ucbm_register WHERE "+ExpirationInterval.DAILY+" AND paid='0' LIMIT 1";
		String query2 = query1.replace(ExpirationInterval.DAILY, ExpirationInterval.WEEKLY);
		String query3 = query2.replace(ExpirationInterval.WEEKLY, ExpirationInterval.MONTHLY);
		
		try {
			PreparedStatement st1 = this.connection.prepareStatement(query1);
			PreparedStatement st2 = this.connection.prepareStatement(query2);
			PreparedStatement st3 = this.connection.prepareStatement(query3);
			
			ResultSet r = st1.executeQuery();
			while(r.next())	values[0] = r.getInt(1);
			st1.close();
			
			r = st2.executeQuery();
			while (r.next()) values[1] = r.getInt(1);
			st2.close();
			
			r = st3.executeQuery();
			while(r.next()) values[2] = r.getInt(1);
			st3.close();			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return values;
	}
}
