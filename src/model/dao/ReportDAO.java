package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.util.ReportCardItem;

public class ReportDAO {
	
	private Connection connection;
	
	public ReportDAO() {
		this.connection = ConnectionFactory.getConnection();
	}
	
	public ArrayList<ReportCardItem> getRegistersDatas(){
		ArrayList<ReportCardItem> list = new ArrayList<>();
		String[] names = {"GERAL","DESPESA","RECEITA"};
		
		String queryGer = generalSelectionStatement();
		String queryExp = generalSelectionStatement();
		String queryRev = generalSelectionStatement();
		
		queryExp += "WHERE type='1'";
		queryRev += "WHERE type='2'";
		
		String query = "("+queryGer+") UNION ("+queryExp+") UNION ("+queryRev+")";
		
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			int i=0;
			while (result.next()) {
				list.add(new ReportCardItem(names[i], result.getInt(1),result.getFloat(2)));
				i++;
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
		
		return list;
	}
	
	public ArrayList<ReportCardItem> getCategoriesDatas(){
		ArrayList<ReportCardItem> list = new ArrayList<>();
		String query = categoriesSelectionStatement();
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				list.add(new ReportCardItem(result.getString(1), result.getInt(2), result.getFloat(3)));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<ReportCardItem> getPaymentsDatas(){
		ArrayList<ReportCardItem> list = new ArrayList<>();
		String query = paymentSelectionStatement();
		
		try {
			PreparedStatement statement =this.connection.prepareStatement(query);
			ResultSet r = statement.executeQuery();
			
			while(r.next()) {
				list.add(new ReportCardItem(r.getString(1), r.getInt(2), r.getFloat(3)));
			}
			
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int numberRegister() {
		int num = 0;
		String query = "SELECT COUNT(id_register) FROM ucbm_register LIMIT 1";
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet r = statement.executeQuery();
			while(r.next()) {
				num = r.getInt(1);
				break;
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	private String generalSelectionStatement() {
		return "SELECT count(id_register), SUM(value) FROM ucbm_register ";
	}
	
	private String categoriesSelectionStatement() {
		return "SELECT c.name, COUNT(r.id_register), SUM(r.value) FROM ucbm_register AS r " + 
				"INNER JOIN ucbm_category AS c ON r.category_id=c.id_category " + 
				"GROUP BY r.category_id LIMIT 5";
	}
	
	private String paymentSelectionStatement() {
		return "SELECT p.name, COUNT(r.id_register), SUM(r.value) FROM ucbm_register AS r " + 
				"INNER JOIN ucbm_payments AS p ON r.payment_id=p.id_payment " + 
				"GROUP BY r.payment_id LIMIT 5";
	}

}
