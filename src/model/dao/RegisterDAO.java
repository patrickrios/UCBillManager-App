package model.dao;

import java.util.ArrayList;
import model.bean.Persistent;
import model.bean.Register;

public class RegisterDAO implements PersistentBean {

	@Override
	public void createNew(Persistent register) {
		
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
		// TODO Auto-generated method stub
		return false;
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
