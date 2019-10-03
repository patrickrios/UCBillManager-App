package model.bean;

import java.sql.Timestamp;

import model.dao.RegisterDAO;
import view.util.RealFormat;

public class Register implements Persistent{
	private Integer id;
	private String code;
	private TypeRegister type;
	private Category category;
	private Payment payment;
	private float value;
	private boolean paid;
	private int parcel;
	private Timestamp expirationDate;
	private Timestamp inclusionDate;
	private boolean favorite = false;
	
	public Register(Integer id, String code, Category c, Payment p, float value, boolean paid, int parcel, Timestamp expiration, int type) {
		this.id = id;
		this.code = code;
		this.category = c;
		this.payment = p;
		this.value = value;
		this.paid = paid;
		this.parcel = parcel;
		this.expirationDate = expiration;
		this.type = new TypeRegister(type);
	}
	
	public Register(String code, Category c, Payment p, float value, boolean paid, int parcel, Timestamp expiration, int type) {
		this.id = null;
		this.code = code;
		this.category = c;
		this.payment = p;
		this.value = value;
		this.paid = paid;
		this.parcel = parcel;
		this.expirationDate = expiration;
		this.type = new TypeRegister(type);
	}
	
	public Register(Integer id, String code, String cName, String pName, float value, boolean paid, int parcel, int type) {
		this.id = id;
		this.code = code;
		this.category = new Category(cName);
		this.payment = new Payment(pName);
		this.value = value;
		this.paid = paid;
		this.parcel = parcel;
		this.type = new TypeRegister(type);
	}
	
	public Register(Integer id, String code, float value, int parcel, boolean paid, Timestamp expiration, Timestamp inclusion,  int type, boolean favorite, Category c, Payment p) {
		this.id = id;
		this.code = code;
		this.value = value;
		this.parcel = parcel;
		this.paid = paid;
		this.expirationDate = expiration;
		this.inclusionDate = inclusion;
		this.type = new TypeRegister(type);
		this.favorite = favorite;
		this.category = c;
		this.payment = p;
	}
	
	@Override
	public void createNewIfNotExists() {
		if(this.id == null)
		{
			new RegisterDAO().createNew(this);
		}
		
	}
	
	@Override
	public void deleteThis() {
		new RegisterDAO().delete(this.id);
	}
	
	@Override
	public void updateThis() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return this.code;
	}
	
	public String getValueFormatted()
	{
		return RealFormat.floatToRealString(this.value);
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getCategoryName() {
		return category.toString();
	}
	
	public boolean isPaid() {
		return paid;
	}
	
	public int getParcel() {
		return parcel;
	}
	
	public String getTypeName()
	{
		return this.type.toString();
	}
	
	public Category getCategory() {
		return category;
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	public String getValueWithoutPrefix()
	{
		return RealFormat.stringWithoutPrefix(this.value);
	}
	
	public String getPaidStatus()
	{
		return (this.paid)?"sim":"não";
	}
	
	public float getValue() {
		return value;
	}
	
	public Timestamp getExpirationDate() {
		return expirationDate;
	}
	
	public int getTypeValue()
	{
		return this.type.getCode();
	}
	
	public boolean isFavorite() {
		return favorite;
	}
	
	
}
