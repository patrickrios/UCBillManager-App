package model.dao;

import java.util.ArrayList;
import model.bean.Category;
import model.bean.Persistent;

public class CategoryDAO implements PersistentBean {

	@Override
	public void createNew(Persistent register) {
		// TODO Auto-generated method stub
		
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
		list.add(new Category(1, "Transporte"));
		list.add(new Category(2, "Alimentação"));
		list.add(new Category(3,  "Internet"));
		return list;
	}

	@Override
	public boolean verifyExistenceOf(String identifyCode) {
		// TODO Auto-generated method stub
		return false;
	}

}
