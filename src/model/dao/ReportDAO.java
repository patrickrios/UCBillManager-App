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
		
		String queryGer = selectionStatement();
		String queryExp = selectionStatement();
		String queryRev = selectionStatement();
		
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
		//
		return null;
	}
	
	public ArrayList<ReportCardItem> getPaymentsDatas(){
		//TODO
		return null;
	}
	
	private String selectionStatement() {
		return "SELECT count(id_register), SUM(value) FROM ucbm_register ";
	}

}
