package model.entity;

import java.util.ArrayList;
import model.dao.RegisterDAO;
import model.types.TypeList;

public class List {

	private int totalRegisters;
	private int offset;
	private int limit;
	private ArrayList<Persistent> itensList;
	private RegisterDAO acess = new RegisterDAO();
	int[] totalValues = new int[4];
	
	
	public List(){
		initValues();
		loadItens();
	}
	
	private void loadItens(){
		this.itensList = acess.findGroup(this.offset, this.limit);
	}
	 
	 public ArrayList<Persistent> loadNextPage(int type){
		 incrementControls();
		 getItens(type);
		 return this.itensList;
	 }
	 
	 public ArrayList<Persistent> loadPreviousPage(int type){
		 decrementControls();
		 getItens(type);
		 return this.itensList;
	 }
	
	public ArrayList<Persistent> searchItens(String input){
		return new RegisterDAO().findItens(this.offset, this.limit, input);
	}
	
	void deleteAllSelectedItens(ArrayList<Persistent> itens){
		for(Persistent p : itens){
			p.deleteThis();
		}
	}
	
	void markAllSelectedAsFavorite(ArrayList<Persistent> itens){
		//TODO
	}
	
	void resetList(){
		//TODO
	}
	
	public ArrayList<Persistent> getItens(int type){
		this.itensList = acess.getItens(this.offset, this.limit, type);
		this.totalRegisters = this.totalValues[type];
		return this.itensList;
	}
	
	public int valueOfTotalRegister(){
		return this.totalRegisters;
	}
	
	public String paginationInfo(){
		String pagInfo = "";
		if((this.offset+this.limit) > this.totalRegisters)
			pagInfo = (this.offset+1)+"-"+(this.totalRegisters)+" de "+this.totalRegisters;
		else
			pagInfo = (this.offset+1)+"-"+(this.offset+this.limit+1)+" de "+this.totalRegisters;
		return pagInfo;
	}

	
	public boolean isFirstPage(){
		boolean ok = false;
		if(this.offset <= 1)	ok=true;
		return ok;
	}
	
	public boolean isLastPage(){
		boolean ok = false;
		
		if(this.offset+this.limit >= this.totalRegisters)
			ok = true;
		return ok;
	}
	
	private void initValues(){
		 this.totalValues = new RegisterDAO().loadDatasToInitList();
		 this.totalRegisters = totalValues[0];
		
		 this.offset = 0;
		 
		 if(this.totalRegisters <= 15)
			 this.limit = this.totalRegisters;
		 else
			 this.limit = 15;
	 }
	
	private void incrementControls(){
		this.offset += 15;
		verifyExcepOff();
	}
	
	private void decrementControls(){
		this.offset -= 15;
		verifyExcepOff();
	}
	
	private void verifyExcepOff(){
		if(this.offset < 1)
			this.offset = 0;
	}
	
	public void resetPagination(){
		this.offset = 0;
	}
	
}
