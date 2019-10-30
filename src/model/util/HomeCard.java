package model.util;

public class HomeCard {
	private String name;
	private int total;
	private int subtotal;
	private float percent;
	private float value;
	
	public HomeCard(int total, int subtotal, float value) {
		this.total = total;
		this.subtotal = subtotal;
		this.value = value;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public float calcPercent()
	{
		return this.percent;
	}

}
