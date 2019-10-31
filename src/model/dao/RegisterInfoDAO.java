package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.util.HomeCard;

public class RegisterInfoDAO {

	private Connection connection;
	
	public RegisterInfoDAO() {
		this.connection = ConnectionFactory.getConnection();
	}
	
	public HomeCard loadInfo(int type, int paid){
		HomeCard paidOrNot = null;
		String query = "SELECT COUNT(id_register), SUM(value) FROM ucbm_register WHERE (type='"+type+"' AND paid='"+paid+"')";
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet r = statement.executeQuery();
			while (r.next()) {
				paidOrNot = new HomeCard(26, r.getInt(1), r.getFloat(2));
				break;
			}
			statement.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return paidOrNot;
	}
	
	public HomeCard loadInfo(int type){
		HomeCard card = null;
		String query = "SELECT COUNT(id_register), SUM(value) FROM ucbm_register WHERE type = '"+type+"' ";
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()){
				card = new HomeCard(23, result.getInt(1), result.getFloat(2));
				break;
			}
			statement.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return card;
	}
}
