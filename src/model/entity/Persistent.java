package model.entity;

import model.exception.DeleteITemException;
import model.exception.RegisterAlreadyExistException;
import model.exception.RegisterUpdatingException;

public interface Persistent {
	void createNewIfNotExists() throws RegisterAlreadyExistException;
	void deleteThis() throws DeleteITemException;
	void updateThis() throws RegisterUpdatingException;
	void updateIdentify(String name);
}
