package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.bean.Persistent;
import model.bean.Register;

public class RegisterDAO implements PersistentBean {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Persistent register) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Persistent> findGroup(int offset, int limit) {
		ArrayList<Persistent> list = new ArrayList<>();
		list.add(new Register(1, "WAM-K455", "Transporte", "Dinheiro", 89.90f, true, 3, 1));
		list.add(new Register(2, "DSL-K322", "Alimentação", "Dinheiro", 76.45f, true, 1, 2));
		list.add(new Register(3, "PPk-012", "Saúde", "Cartão de crédito", 12.50f, false, 1, 1));
		list.add(new Register(4, "10D-704", "Manutenção", "Cartão de débito", 33.99f, true, 1, 1));
		list.add(new Register(5, "LLP-001", "Internet", "Cheque", 2.35f, false, 1, 2));
		list.add(new Register(6, "LLP-002", "Luz", "Dineheiro", 7.50f, false, 2, 1));
		list.add(new Register(1, "WAM-K455", "Transporte", "Dinheiro", 89.90f, true, 3, 1));
		list.add(new Register(2, "DSL-K322", "Alimentação", "Dinheiro", 76.45f, true, 1, 2));
		list.add(new Register(3, "PPk-012", "Saúde", "Cartão de crédito", 12.50f, false, 1, 1));
		list.add(new Register(4, "10D-704", "Manutenção", "Cartão de débito", 33.99f, true, 1, 1));
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
		} catch (SQLException e) {
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

}
