package model.bean;

import java.sql.Timestamp;

public class Register {
	private Integer id;
	private String code;
	private Category category;
	private Payment payment;
	private float value;
	private boolean paid;
	private int parcel;
	private Timestamp expirationDate;
	private Timestamp inclusionDate;
	private int type;
}
