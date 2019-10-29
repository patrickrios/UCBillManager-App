package model.entity;

public interface Persistent {
	void createNewIfNotExists();
	void deleteThis();
	void updateThis();
	void updateIdentify(String name);
}
