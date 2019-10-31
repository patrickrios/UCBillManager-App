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
	private RegisterDAO acess = new RegisterDAO();
	
	
	public List(){
		initValues();
		loadItens();
	}
	
	 void loadItens(){
		this.itensList = acess.findGroup(this.offset, this.limit);
	}
	 
	 public ArrayList<Persistent> loadNextPage(){
		 incrementControls();
		 this.itensList = acess.findGroup(this.offset, this.limit);
		 
		 return this.itensList;
	 }
	 public ArrayList<Persistent> loadPreviousPage()
	 {
		 decrementControls();
		 this.itensList = acess.findGroup(this.offset, this.limit);
		 return this.itensList;
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
		//TODO
	}
	
	void resetList(){
		//TODO
	}
	
	public ArrayList<Persistent> getItens(){
		return this.itensList;
	}
	
	public int valueOfTotalRegister(){
		return this.totalRegisters;
	}
	
	public String paginationInfo(){
		
		return "mostrando "+this.offset+"-"+this.limit+" de "+this.totalRegisters;
	}
	
	public int valueOfTotalFavoriteItens(){
		return this.totalFavorites;
	}
	
	public boolean isFirstPage(){
		boolean ok = false;
		if(this.offset <= 1)
			ok = true;
		return ok;
	}
	
	public boolean isLastPage(){
		boolean ok = false;
		
		if(this.limit >= this.totalRegisters)
			ok = true;
		return ok;
	}
	
	private void initValues(){
		 int[] values = new RegisterDAO().loadDatasToInitList();
		 this.totalRegisters = values[0];
		 this.totalFavorites = values[1];
		
		 if(this.totalRegisters <= 0)	this.offset = 0;
		 else	this.offset = 1;
		 
		 if(this.totalRegisters < 15) this.limit = this.totalRegisters;
		 else this.limit = 15;
	 }
	
	private void incrementControls(){
		this.offset += 15;
		this.limit  += 15;
		verifyExcepLimit();
		verifyExcepOff();
	}
	
	private void decrementControls()
	{
		this.offset -= 15;
		this.limit  -= 15;
		verifyExcepLimit();
		verifyExcepOff();
	}
	
	private void verifyExcepLimit(){
		if(this.limit > this.totalRegisters)
			this.limit = this.totalRegisters;
	}
	
	private void verifyExcepOff()
	{
		if(this.offset < 1)
			this.offset = 1;
	}
	
}
