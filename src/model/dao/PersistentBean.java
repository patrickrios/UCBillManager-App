package model.dao;

import java.util.ArrayList;

import model.entity.Persistent;
import model.exception.RegisterAlreadyExistException;

public interface PersistentBean {

	void createNew(Persistent register);
	void createNew(String name) throws RegisterAlreadyExistException;
	boolean delete(int idRegister);
	void update(Persistent register);
	ArrayList<Persistent> findGroup(int offset, int limit);
	ArrayList<Persistent> findAll();
	boolean verifyExistenceOf(String identifyCode) throws RegisterAlreadyExistException;
	int numberOfRegisters();
	
}
