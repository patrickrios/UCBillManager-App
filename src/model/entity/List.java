package model.entity;

import java.util.ArrayList;
import model.dao.PersistentBean;

public class List implements Listable {

	private int totalRegisters;
	private int offset;
	private int limit;
	private int totalFavorites;
	private ArrayList<Persistent> itensList;
	
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
