package model.util;

import view.util.RealFormat;

public class ReportCardItem {
	private String name;
	private int quantity;
	private float total;
	
	public ReportCardItem(String name, int quantity, float total) {
		this.name = name;
		this.quantity = quantity;
		this.total = total;
	}
	
	public String getName() {
		return name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public String getTotalFormatted() {
		return RealFormat.floatToRealString(this.total);
	}
	
	public String getPerc(int t) {
		float perc = (this.quantity*100)/t;
		String p = String.format("%.2f", perc);
		p += "%";
		return p;
		
	}
}
