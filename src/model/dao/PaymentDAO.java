package model.dao;

import java.util.ArrayList;
import model.bean.Category;
import model.bean.Payment;
import model.bean.Persistent;

public class PaymentDAO implements PersistentBean {

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
		list.add(new Payment(1, "Dinheiro"));
		list.add(new Payment(2, "Cartão de crédito"));
		list.add(new Payment(3,  "Cheque"));
		return list;
	}

	@Override
	public boolean verifyExistenceOf(String identifyCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createNew(String name) {
		System.out.println("Pagamento salvo: "+name);
		
	}

	@Override
	public ArrayList<Persistent> findAll() {
		ArrayList<Persistent> list = new ArrayList<>();
		list.add(new Payment(1, "Dinheiro"));
		list.add(new Payment(2, "Cartão de crédito"));
		list.add(new Payment(3,  "Cheque"));
		return list;
	}

}
