package model.util;

import model.dao.RegisterInfoDAO;
import model.entity.TypePaid;
import model.entity.TypeRegister;

public class RegisterInformations {
	private HomeCard card;
	private HomeCard paid;
	private HomeCard notPaid;
	private RegisterInfoDAO dao;
	private int type;
	
	public RegisterInformations(int type) {
		dao = new RegisterInfoDAO();
		this.type = type;
	}
	
	public void loadCardDatas(String name){
		this.card = dao.loadInfo(this.type);
		this.card.setName(TypeRegister.getTypeName(this.type));
	}
	
	public void loadPaidDatas(){
		this.paid = dao.loadInfo(this.type, TypePaid.PAID);
		this.paid.setName("Pagas");
	}
	
	public void loadNotPaidDatas(){
		this.notPaid = dao.loadInfo(this.type, TypePaid.NOTPAID);
		this.notPaid.setName("Não pagas");
	}
	
}