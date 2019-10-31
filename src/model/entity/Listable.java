package model.entity;

import java.util.ArrayList;

public interface Listable {
	ArrayList<Persistent> loadNextPage();
	ArrayList<Persistent> loadPreviousPage();
	ArrayList<Persistent> loadFavorites();
}
