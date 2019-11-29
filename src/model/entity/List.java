package model.entity;

import java.util.ArrayList;
import model.dao.RegisterDAO;
import model.types.TypeList;
import model.types.TypePaid;

public class List {

	private int totalRegisters;
	private int offset;
	private int limit;
	private ArrayList<Persistent> itensList;
	private RegisterDAO dataAcess = new RegisterDAO();
	int[] totalValues = new int[4];
	
	
	public List(){
		initValues();
		loadItens();
	}
	
	private void loadItens(){
		this.itensList = dataAcess.getItens(this.offset,this.limit,TypeList.ALL,TypePaid.ALL);
	}
	 
	 public ArrayList<Persistent> loadNextPage(int type, int paid){
		 incrementControls();
		 getItens(type,paid);
		 return this.itensList;
	 }
	 
	 public ArrayList<Persistent> loadPreviousPage(int type, int paid){
		 decrementControls();
		 getItens(type,paid);
		 return this.itensList;
	 }
	
	public ArrayList<Persistent> searchItens(String input){
		return new RegisterDAO().findItens(this.offset, this.limit, input);
	}
	
	void markAllSelectedAsFavorite(ArrayList<Persistent> itens){
		//TODO
	}
	
	void resetList(){
		//TODO
	}
	
	public ArrayList<Persistent> getItens(int type, int paid){
		this.itensList = dataAcess.getItens(this.offset, this.limit, type, paid);
		this.totalRegisters = dataAcess.totaRegister(type, paid);
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
			pagInfo = (this.offset+1)+"-"+(this.offset+this.limit)+" de "+this.totalRegisters;
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
		 this.offset = 0;
		 this.totalRegisters = dataAcess.totaRegister(TypeList.ALL,TypePaid.ALL);
		 
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