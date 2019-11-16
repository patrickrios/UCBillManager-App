package model.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import model.dao.RegisterDAO;
import model.types.TypeRegister;
import view.monthpicker.MonthRef;
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
	private MonthRef month;
	private Timestamp expirationDate;
	private Timestamp inclusionDate;
	private boolean favorite = false;
	
	public Register(Integer id, String code, Category c, Payment p, float value, boolean paid, int parcel, MonthRef month, Timestamp expiration, int type) {
		this.id = id;
		this.code = code;
		this.category = c;
		this.payment = p;
		this.value = value;
		this.paid = paid;
		this.parcel = parcel;
		this.month = month;
		this.expirationDate = expiration;
		this.type = new TypeRegister(type);
	}
	
	public Register(String code, Category c, Payment p, float value, boolean paid, int parcel,MonthRef month, Timestamp expiration, int type) {
		this.id = null;
		this.code = code;
		this.category = c;
		this.payment = p;
		this.value = value;
		this.paid = paid;
		this.parcel = parcel;
		this.month = month;
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
	
	public Register(Integer id, String code, float value, int parcel, boolean paid, MonthRef month, Timestamp expiration, Timestamp inclusion,  int type, boolean favorite, Category c, Payment p) {
		this.id = id;
		this.code = code;
		this.value = value;
		this.parcel = parcel;
		this.paid = paid;
		this.month = month;
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
		new RegisterDAO().updateDatas(this);
	}
	public boolean updateDatas() {
		return new RegisterDAO().updateDatas(this);
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
	
	public Integer getId() {
		return id;
	}
	
	public String getValueWithoutPrefix(){
		return RealFormat.stringWithoutPrefix(this.value);
	}
	
	public String getPaidStatus(){
		return (this.paid)?"sim":"não";
	}
	
	public float getValue(){
		return value;
	}
	
	public Date getDateValueMonth() {
		return this.month.getDateValue();
	}
	
	public MonthRef getMonth() {
		return this.month;
	}
	
	public String getMonthFormatted() {
		return this.month.monthAndYear();
	}
	
	public Timestamp getExpirationDate(){
		return expirationDate;
	}
	
	public String exiprationDateFormatted(){
		String exp = new SimpleDateFormat("dd/MMM/yyyy").format(this.expirationDate);
		return exp.toUpperCase();
	}
	
	public String inclusionDateFormatted() {
		String date = new SimpleDateFormat("dd/M/yyyy").format(this.inclusionDate);
		String time = new SimpleDateFormat("hh:mm").format(this.inclusionDate);
		return "Adicionado em "+date+" às "+time;
	}
	
	public int getTypeValue(){
		return this.type.getCode();
	}
	
	public boolean isFavorite(){
		return favorite;
	}
	
	public void markAsFavorite(){
		this.favorite = true;
		updateFavoriteStatus();
	}
	
	public void switchFavoriteStatus() {
		this.favorite = !this.favorite;
		updateFavoriteStatus();
	}
	
	private void updateFavoriteStatus() {
		if(this.id != null) {
			new RegisterDAO().changeFavoriteStatus(id, favorite);
		}
	}
	
	public void unmarkAsFavorite(){
		this.favorite = false;
	}
	

	@Override
	public void updateIdentify(String name) {
		this.code = name;
		
	}
	
	public boolean isExpired(){
		boolean yes = false;
		java.util.Date now = new java.util.Date();
		
		if(this.expirationDate.before(now))
			yes = true;
		
		return yes;
	}
	
	public void switchPaymentStatus(){
		this.paid = !this.paid;
		updatePayStatus();
	}
	
	private void updatePayStatus(){
		if(this.id != null)
			new RegisterDAO().changePayStatus(id, paid);
	}
	
	public void updateDatas(String code, Category c, Payment p, float value, boolean paid, int parcel, MonthRef month, Timestamp expiration, int type) {
		this.code = code;
		this.category = c;
		this.payment = p;
		this.value = value;
		this.paid = paid;
		this.parcel = parcel;
		this.month = month;
		this.expirationDate = expiration;
		this.type = new TypeRegister(type);
	}
	
	public void printStatus() {
		System.out.println("Register ("+this.code+")");
		System.out.println("\tcode: "+this.code);
		System.out.println("\tcateg: "+this.category.getName());
		System.out.println("\tpay: "+this.payment.getName());
		System.out.println("\tvalue: "+this.value);
		System.out.println("\tpaid: "+this.getPaidStatus());
		System.out.println("\tparcel: "+this.parcel);
		System.out.println("\texp date: "+this.exiprationDateFormatted());
		System.out.println("\ttipo: "+this.getTypeName());
	}
}
