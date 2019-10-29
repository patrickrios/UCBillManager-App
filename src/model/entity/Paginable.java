package model.entity;

import java.util.ArrayList;

public interface Paginable {
	ArrayList<Persistent> loadNextPage();
	ArrayList<Persistent> loadPreviousPage();
	ArrayList<Persistent> loadFavorites();
}
