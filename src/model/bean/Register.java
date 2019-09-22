package model.bean;

import java.sql.Timestamp;

import view.util.RealFormat;

public class Register implements Persistent{
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
	
	public Register(Integer id, String code, Category c, Payment p, float value, boolean paid, int parcel, Timestamp expiration, int type) {
		this.id = id;
		this.code = code;
		this.category = c;
		this.payment = p;
		this.value = value;
		this.paid = paid;
		this.parcel = parcel;
		this.expirationDate = expiration;
		this.type = type;
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
		this.type = type;
	}
	
	@Override
	public void createNewIfNotExists() {
		if(this.id == null)
		{
			System.out.println("Registro {");
			System.out.println("\tcode='"+this.code+"'");
			System.out.println("\tcategory='"+this.category.toString()+"'");
			System.out.println("\tpayment="+this.payment.toString()+"'");
			System.out.println("\tvalue='"+RealFormat.floatToRealString(this.value)+"'");
			System.out.println((paid) ? "\tpaid": "\tnot paid");
			System.out.println("\tparcel='"+this.parcel+"'");
			System.out.println("\texpiration='"+this.expirationDate.toString()+"'");
			System.out.println("}");
		}
		
	}
	
	@Override
	public void deleteThis() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updateThis() {
		// TODO Auto-generated method stub
		
	}
	
}
