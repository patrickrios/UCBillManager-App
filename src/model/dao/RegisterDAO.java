package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		
		String sql = "DELETE FROM ucbm_register WHERE id_register='"+idRegister+"'";
		
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
		Register reg = (Register)register;
		String update = "UPDATE ucbm_register SET code=?,value=?,parcel=?,paid=?,expiration=?, type?,"
				   +"favorite=?,category_id=?,payment_id=? WHERE id_register=?";
		try {
			PreparedStatement statement = this.connection.prepareStatement(update);
			statement.setString(1, reg.getCode());
			statement.setFloat(2, reg.getValue());
			statement.setInt(3, reg.getParcel());
			statement.setInt(4, (reg.isPaid())?1:0);
			statement.setTimestamp(5, reg.getExpirationDate());
			statement.setInt(6, reg.getTypeValue());
			statement.setInt(7, (reg.isFavorite())?1:0);
			statement.setInt(8, reg.getCategory().getId());
			statement.setInt(9, reg.getPayment().getId());
			statement.setInt(10, reg.getId());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Persistent> getItens(int offset,int limit,int type,int pay){
		ArrayList<Persistent> list = new ArrayList<>();
		
		String query = getDataStatment();
		query += getFilter(type,pay);
		query += getPagination(offset,limit);
			
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next())				
				list.add(getRegisterFromResultSet(result));
			
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
		
		String query = getDataStatment();
		query += getPagination(offset, limit);
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()){
				list.add(getRegisterFromResultSet(result));
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
		boolean val=false;
		if(i==1)val=true;
		return val;
	}

	@Override
	public ArrayList<Persistent> findItens(int offset, int limit, String input) {
		ArrayList<Persistent> list = new ArrayList<>();
		
		String query = getDataStatment();
		query += "WHERE ucbm_register.code LIKE '%"+input+"%' ";
		query += getPagination(offset, limit);
		
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()){
				list.add(getRegisterFromResultSet(result));
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
		query += getFilter(typeFilter, payFilter);
			
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
	
	private String getDataStatment() {
		return 
		"SELECT ucbm_register.id_register, ucbm_register.code, ucbm_register.value, ucbm_register.parcel, ucbm_register.paid, "+ 
		"ucbm_register.expiration, ucbm_register.inclusion, ucbm_register.type, ucbm_register.favorite, "+ 
		"ucbm_register.category_id, ucbm_category.name, ucbm_register.payment_id, ucbm_payments.name "+ 
		"FROM ucbm_register "+
		"INNER JOIN ucbm_category ON ucbm_register.category_id = ucbm_category.id_category "+ 
		"INNER JOIN ucbm_payments ON ucbm_register.payment_id = ucbm_payments.id_payment ";
	}
	
	private String getFilter(int type, int pay) {
		String filter = "";
		
		if((type!=TypeList.ALL)||(pay!=TypePaid.ALL)) {
			filter+= " WHERE ";
					
			if(type!=TypeList.ALL) {
				if(type==TypeList.EXPENSE || type==TypeList.REVENUE)
					filter += "type='"+type+"' ";
				else if(type==TypeList.FAV)
					filter += "favorite='1' ";
			}
					
			if(pay!=TypePaid.ALL) {
				if(type!=TypeList.ALL)
					filter += "AND ";
						
				if(pay==TypePaid.NOTPAID || pay==TypePaid.PAID)
					filter += "paid='"+pay+"' ";
			}
		}
		return filter;
	}
	
	private String getPagination(int offset,int limit) {
		return "LIMIT "+limit+" OFFSET "+offset;
	}
	
	private Register getRegisterFromResultSet(ResultSet result) {
		Register register = null;
		try {
			register = new Register(
				result.getInt(1),
				result.getString(2),
				result.getFloat(3),
				result.getInt(4),
				intToBool(result.getInt(5)),
				result.getTimestamp(6),
				result.getTimestamp(7),
				result.getInt(8),
				intToBool(result.getInt(9)),
				new Category(result.getInt(10), result.getString(11)),
				new Payment(result.getInt(12), result.getString(13))
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return register;
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
