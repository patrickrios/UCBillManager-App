package model.bean;

import java.util.ArrayList;

import model.dao.PersistentBean;

public class List implements Paginable {

	private int offset=1;
	private int limit=15;
	private int numOfRegister;
	private PersistentBean pDao;
	
	public List(PersistentBean p) {
		this.pDao = p;
	}

	@Override
	public ArrayList<Persistent> loadNextPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Persistent> loadPreviousPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Persistent> loadFavorites() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
