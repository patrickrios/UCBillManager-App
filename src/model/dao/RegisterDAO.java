package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.entity.Category;
import model.entity.Listable;
import model.entity.Payment;
import model.entity.Persistent;
import model.entity.Register;
import model.types.TypeList;
import model.types.TypePaid;

public class RegisterDAO implements PersistentBean, Listable {
	
	private Connection connection;
	private String queryStat = "SELECT ucbm_register.id_register, ucbm_register.code, ucbm_register.value, ucbm_register.parcel, ucbm_register.paid, "+ 
			"ucbm_register.expiration, ucbm_register.inclusion, ucbm_register.type, ucbm_register.favorite, "+ 
			"ucbm_register.category_id, ucbm_category.name, ucbm_register.payment_id, ucbm_payments.name "+ 
			"FROM ucbm_register "+
			"INNER JOIN ucbm_category ON ucbm_register.category_id = ucbm_category.id_category "+ 
			"INNER JOIN ucbm_payments ON ucbm_register.payment_id = ucbm_payments.id_payment ";
	
	public RegisterDAO() {
		this.connection = ConnectionFactory.getConnection();
	}

	@Override
	public void createNew(Persistent register) {
		Register r = (Register)register;
		
		String sql = "INSERT INTO ucbm_register (id_register, code, value, parcel, paid, expiration, inclusion, type, favorite, category_id, payment_id) " + 
				"VALUES (DEFAULT,?,?,?,?,DEFAULT,?,?,?,?,?);";
		
		try {
			PreparedStatement statement = this.connection.prepareStatement(sql);
			statement.setString(1, r.getCode());
			statement.setFloat(2, r.getValue());
			statement.setInt(3, r.getParcel());
			statement.setInt(4, (r.isPaid())? 1 : 0);
			statement.setTimestamp(5, r.getExpirationDate());
			statement.setInt(6, r.getTypeValue());
			statement.setInt(7, (r.isFavorite())? 1 : 0);
			statement.setInt(8, r.getCategory().getId());
			statement.setInt(9, r.getPayment().getId());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int idRegister) {
		
		String sql = "DELETE FROM ucbm_register WHERE id_register = '"+idRegister+"'";
		
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
	public void update(Persistent register) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Persistent> getItens(int offset, int limit, int typeList, int paidOrNot){
		ArrayList<Persistent> list = new ArrayList<>();
		String query = this.queryStat;
		
		if(typeList == TypeList.EXPENSE)
			query += "WHERE type='"+typeList+"' ";
		else if(typeList == TypeList.REVENUE)
			query += "WHERE type='"+typeList+"' ";
		else if(typeList == TypeList.FAV)
			query += "WHERE favorite='1' ";
		
		if(paidOrNot == TypePaid.NOTPAID)
			query += "AND paid='"+paidOrNot+"' ";
		else if(paidOrNot == TypePaid.PAID)
			query += "AND paid='"+paidOrNot+"' ";
		
		query += "LIMIT "+limit+" OFFSET "+offset;
			
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet r = statement.executeQuery();
			
			while(r.next()){				
				list.add(new Register(r.getInt(1),r.getString(2),r.getFloat(3),r.getInt(4),intToBool(r.getInt(5)),
						r.getTimestamp(6),r.getTimestamp(7),r.getInt(8),intToBool(r.getInt(9)),new Category(r.getInt(10), r.getString(11)),new Payment(r.getInt(12), r.getString(13))));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<Persistent> findGroup(int offset, int limit) 
	{
		ArrayList<Persistent> list = new ArrayList<>();
		
		String query = this.queryStat+"LIMIT "+limit+" OFFSET "+offset;
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()){
				Integer id = result.getInt(1);
				String code = result.getString(2);
				float value = result.getFloat(3);
				int parcel = result.getInt(4);
				boolean paid = intToBool(result.getInt(5));
				Timestamp exp = result.getTimestamp(6);
				Timestamp inc = result.getTimestamp(7);
				int type = result.getInt(8);
				boolean fav = intToBool(result.getInt(9));
				Category cat = new Category(result.getInt(10), result.getString(11));
				Payment pay = new Payment(result.getInt(12), result.getString(13));
				
				list.add(new Register(id,code,value,parcel,paid,exp,inc,type,fav,cat,pay));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean verifyExistenceOf(String identifyCode) {
		String sql = "SELECT id_register FROM ucbm_register WHERE code LIKE '"+identifyCode+"'";
		boolean exist = true;
		
		try {
			PreparedStatement statement = this.connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			if(!result.next()) 
				exist = false;
			statement.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}

	@Override
	public void createNew(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Persistent> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean intToBool(int i) {
		boolean val = false;
		if(i==1)
			val = true;
		return val;
	}

	@Override
	public ArrayList<Persistent> findItens(int offset, int limit, String input) {
		ArrayList<Persistent> list = new ArrayList<>();
		
		String query = "SELECT ucbm_register.id_register, ucbm_register.code, ucbm_register.value, ucbm_register.parcel, ucbm_register.paid, "+ 
				"ucbm_register.expiration, ucbm_register.inclusion, ucbm_register.type, ucbm_register.favorite, "+ 
				"ucbm_register.category_id, ucbm_category.name, ucbm_register.payment_id, ucbm_payments.name "+ 
				"FROM ucbm_register "+
				"INNER JOIN ucbm_category ON ucbm_register.category_id = ucbm_category.id_category "+ 
				"INNER JOIN ucbm_payments ON ucbm_register.payment_id = ucbm_payments.id_payment "+
				"WHERE ucbm_register.code LIKE '%"+input+"%' LIMIT "+limit+" OFFSET "+offset;
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()){
				Integer id = result.getInt(1);
				String code = result.getString(2);
				float value = result.getFloat(3);
				int parcel = result.getInt(4);
				boolean paid = intToBool(result.getInt(5));
				Timestamp exp = result.getTimestamp(6);
				Timestamp inc = result.getTimestamp(7);
				int type = result.getInt(8);
				boolean fav = intToBool(result.getInt(9));
				Category cat = new Category(result.getInt(10), result.getString(11));
				Payment pay = new Payment(result.getInt(12), result.getString(13));
				
				list.add(new Register(id,code,value,parcel,paid,exp,inc,type,fav,cat,pay));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	

	public int totaRegister(int typeFilter, int payFilter) {
		int total = 0;
		String query = "SELECT COUNT(id_register) FROM ucbm_register";
		
		//if have some filter, add to statement
		if((typeFilter!=TypeList.ALL)||(payFilter!=TypePaid.ALL)) {
			query += " WHERE ";
			
			//if have some filter by register type or favorite
			if(typeFilter!=TypeList.ALL) {
				if(typeFilter==TypeList.EXPENSE || typeFilter==TypeList.REVENUE)
					query += "type='"+typeFilter+"' ";
				else if(typeFilter==TypeList.FAV)
					query += "favorite='1' ";
			}
			
			//if have some filter by pay status
			if(payFilter!=TypePaid.ALL) {
				if(typeFilter!=TypeList.ALL)
					query += "AND ";
				
				if(payFilter==TypePaid.NOTPAID || payFilter==TypePaid.PAID)
					query += "paid='"+payFilter+"' ";
			}
		}
			
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				total = result.getInt(1);
				break;
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public void deleteItens(ArrayList<Persistent> list) {
		for(Persistent p : list) {
			p.deleteThis();
		}
	}
	public void changeFavoriteStatus(int id, boolean status){
		int bit = (status)?1:0;
		String update = "UPDATE ucbm_register SET favorite="+bit+" WHERE id_register='"+id+"'";
		
		try {
			PreparedStatement statement = this.connection.prepareStatement(update);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void changePayStatus(int id, boolean status) {
		int bit = (status)?1:0;
		String update = "UPDATE ucbm_register SET paid="+bit+" WHERE id_register='"+id+"'";
		
		try {
			PreparedStatement statement = this.connection.prepareStatement(update);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
