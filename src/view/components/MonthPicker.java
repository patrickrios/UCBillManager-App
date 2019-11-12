package view.components;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class MonthPicker {
	private int month;
	private int year;
	
	public MonthPicker(int month, int year) {
		this.month = month;
		this.year = year;
	}
	
	public MonthPicker() {		
		this.month = LocalDate.now().getMonthValue();
		this.year = LocalDate.now().getYear();
	}
	
	public MonthPicker(Date date) {
		this.month = date.toLocalDate().getMonthValue();
		this.year = date.toLocalDate().getYear();
	}
	
	public String monthName() {
		String m = new SimpleDateFormat("MMMM").format(month);
		return m;
	}
	
	public int monthNumber() {
		return this.month;
	}
	
	public String monthAndYear() {
		String m = new SimpleDateFormat("MMMM").format(this.month);
		String y = new SimpleDateFormat("yyyy").format(this.year);
		return m+"/"+y;
	}
	
	public int yearNumber() {
		return this.year;
	}
}
