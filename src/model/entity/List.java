package model.entity;

import java.util.ArrayList;
import model.dao.PersistentBean;
import model.dao.RegisterDAO;

public class List {

	private int totalRegisters;
	private int offset;
	private int limit;
	private int totalFavorites;
	private ArrayList<Persistent> itensList;
	
	
	public List(){
		this.offset = 1;
		this.limit = 15;
		loadItens();
	}
	
	 void loadItens(){
		this.itensList = new RegisterDAO().findGroup(this.offset, this.limit);
	}
	
	ArrayList<Persistent> loadItensMarkedAsFavorite(){
		return null;
	}
	
	ArrayList<Persistent> searchItens(String input){
		return new RegisterDAO().findItens(this.offset, this.limit, input);
	}
	
	void deleteAllSelectedItens(ArrayList<Persistent> itens){
		for(Persistent p : itens){
			p.deleteThis();
		}
	}
	
	void marAllSelectedAsFavorite(ArrayList<Persistent> itens){
		for(Persistent p : itens){
			
		}
	}
	
	void resetList(){
		
	}
	
	public ArrayList<Persistent> getItens(){
		return this.itensList;
	}
	
	int valueOfTotalRegister(){
		return this.totalRegisters;
	}
	
	String getPaginationInfo(){
		return "";
	}
	
	int valueOfTotalFavoriteItens(){
		return this.totalFavorites;
	}
	
}
