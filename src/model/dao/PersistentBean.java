package model.dao;

import java.util.ArrayList;

import model.entity.Persistent;

public interface PersistentBean {

	void createNew(Persistent register);
	void createNew(String name);
	void delete(int idRegister);
	void update(Persistent register);
	ArrayList<Persistent> findGroup(int offset, int limit);
	ArrayList<Persistent> findAll();
	boolean verifyExistenceOf(String identifyCode);
	
}
