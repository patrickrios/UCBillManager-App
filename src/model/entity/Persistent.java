package model.entity;

import model.exception.RegisterAlreadyExistException;
import model.exception.RegisterUpdatingException;

public interface Persistent {
	void createNewIfNotExists() throws RegisterAlreadyExistException;
	void deleteThis();
	void updateThis() throws RegisterUpdatingException;
	void updateIdentify(String name);
}
