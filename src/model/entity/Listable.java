package model.entity;

import java.util.ArrayList;

public interface Listable {
	ArrayList<Persistent> findAll();
	ArrayList<Persistent> loadNextPage(int offset, int limit);
	ArrayList<Persistent> loadPreviousPage(int offset, int limit);
	ArrayList<Persistent> loadFavorites(int offset, int limit);
	ArrayList<Persistent> findItens(int offset, int limit, String input);
}
