package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.util.HomeCard;

public class HomeCardDAO {

	private Connection connection;
	
	public HomeCardDAO() {
		this.connection = ConnectionFactory.getConnection();
		this.thisMonthlyInterval();
	}
	
	public HomeCard loadInfo(int type, int paid){
		HomeCard paidOrNot = null;
		int total=0, subtotal=0;
		float value=0.0f;
		
		String query1 = "SELECT COUNT(id_register), SUM(value) FROM ucbm_register WHERE (type='"+type+"' AND paid='"+paid+"' AND  $int)";
		String query2 = "SELECT COUNT(id_register) FROM ucbm_register WHERE (type='"+type+"' AND $int )";
		
		query1 = replaceWithInterval(query1);
		query2 = replaceWithInterval(query2);
		
		try {
			PreparedStatement stat1 = this.connection.prepareStatement(query2);
			PreparedStatement stat2 = this.connection.prepareStatement(query1);
			ResultSet r = stat1.executeQuery();
			while (r.next()) {
				total = r.getInt(1);
				break;
			}
			
			r = stat2.executeQuery();
			while(r.next()) {
				subtotal = r.getInt(1);
				value = r.getFloat(2);
			}
			paidOrNot = new HomeCard(total, subtotal, value);
			stat1.close();
			stat2.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return paidOrNot;
	}
	
	public HomeCard loadInfo(int type){
		HomeCard card = null;
		int total = 0;
		int subtotal = 0;
		float value = 0.0f;
		
		String query1 = "SELECT COUNT(id_register), SUM(value) FROM ucbm_register WHERE (type='"+type+"' AND $int)";
		String query2 = "SELECT COUNT(id_register) FROM ucbm_register WHERE $int";
		
		query1 = replaceWithInterval(query1);
		query2 = replaceWithInterval(query2);
		try {
			PreparedStatement stat1 = this.connection.prepareStatement(query1);
			PreparedStatement stat2 = this.connection.prepareStatement(query2);
			
			ResultSet result = stat1.executeQuery();
			while(result.next()){
				subtotal = result.getInt(1);
				value = result.getFloat(2);
				break;
			}
			result = stat2.executeQuery();
			while(result.next()) {
				total = result.getInt(1);
				break;
			}
			card = new HomeCard(total, subtotal, value);
			stat1.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return card;
	}
	
	private String thisMonthlyInterval() {
		Date d = new Date();
		String inter = new SimpleDateFormat("yyyy-MM-15").format(d);
		return "month='"+inter+"'";
	}
	
	private String replaceWithInterval(String query) {
		return query.replace("$int", thisMonthlyInterval());
	}
}
