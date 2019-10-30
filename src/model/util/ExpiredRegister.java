package model.util;

import java.sql.Timestamp;

import view.util.RealFormat;

public class ExpiredRegister {
	private String code;
	private float value;
	private Timestamp expDate;
	
	public ExpiredRegister(String code, float value, Timestamp exp) {
		this.code = code;
		this.value = value;
		this.expDate = exp;
	}
	
	public String getCode() {
		return code;
	}
	
	public String formattedValue(){
		return RealFormat.floatToRealString(this.value);
	}
	
	public String formattedExpDate()
	{
		return this.expDate.toString();
	}
}
