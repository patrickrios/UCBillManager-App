package model.util;

import model.dao.HomeCardDAO;
import model.types.TypePaid;
import model.types.TypeRegister;

public class RegisterInformations {
	private HomeCard card;
	private HomeCard paid;
	private HomeCard notPaid;
	private HomeCardDAO dao;
	private int type;
	
	public RegisterInformations(int type) {
		dao = new HomeCardDAO();
		this.type = type;
	}
	
	public HomeCard loadCardDatas(){
		this.card = dao.loadInfo(this.type);
		this.card.setName(TypeRegister.getTypeName(this.type));
		return this.card;
	}
	
	public HomeCard loadPaidDatas(){
		this.paid = dao.loadInfo(this.type, TypePaid.PAID);
		this.paid.setName("Pagas");
		return this.paid;
	}
	
	public HomeCard loadNotPaidDatas(){
		this.notPaid = dao.loadInfo(this.type, TypePaid.NOTPAID);
		this.notPaid.setName("Não pagas");
		return this.notPaid;
	}
	
}