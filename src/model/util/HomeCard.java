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
		float perc = 0.0f;
		
		if(this.total > 0)
			perc = (100*this.subtotal)/this.total;
		
		return perc;
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
	
	public String getPercentual(){
		String p = String.format("%.2f", this.calcPercent());
		p += " %";
		return p;
	}
}