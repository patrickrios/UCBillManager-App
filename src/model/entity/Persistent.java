package model.entity;

import model.exception.RegisterAlreadyExistException;

public interface Persistent {
	void createNewIfNotExists() throws RegisterAlreadyExistException;
	void deleteThis();
	void updateThis();
	void updateIdentify(String name);
}
