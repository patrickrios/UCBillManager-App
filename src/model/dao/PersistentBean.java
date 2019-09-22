package model.dao;

import java.util.ArrayList;
import model.bean.Persistent;

public interface PersistentBean {

	void createNew(Persistent register);
	void delete(int idRegister);
	void update(Persistent register);
	ArrayList<Persistent> findGroup(int offset, int limit);
	boolean verifyExistenceOf(String identifyCode);
	
}
