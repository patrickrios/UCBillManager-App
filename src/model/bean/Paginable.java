package model.bean;

import java.util.ArrayList;

public interface Paginable {
	ArrayList<Persistent> loadNextPage();
	ArrayList<Persistent> loadPreviousPage();
	ArrayList<Persistent> loadFavorites();
}
