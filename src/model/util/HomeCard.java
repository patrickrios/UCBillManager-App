package model.util;

import view.util.RealFormat;

public class HomeCard {
	private String name;
	private int total;
	private int subtotal;
	private float value;
	
	public HomeCard(int total, int subtotal, float value) {
		this.total = total;
		this.subtotal = subtotal;
		this.value = value;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	private float calcPercent(){
		if(total == 0)
			return 0;
		else
			return (100*this.subtotal)/this.total;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSubtotal() {
		return ""+this.subtotal;
	}
	
	public String getValue() {
		return RealFormat.floatToRealString(this.value);
	}
	
	public String getPercentual()
	{
		return this.calcPercent()+"%";
	}

}
