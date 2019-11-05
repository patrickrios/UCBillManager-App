package model.entity;

import java.util.ArrayList;

public interface Listable {
	ArrayList<Persistent> findAll();
	ArrayList<Persistent> findItens(int offset, int limit, String input);
}
