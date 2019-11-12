package view.components;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class MonthPicker {
	private int month;
	private int year;
	
	public MonthPicker(int month, int year) {
		this.month = month;
		this.year = year;
	}
	
	public MonthPicker() {		
		Calendar calendar = Calendar.getInstance();
		
		this.month = calendar.get(Calendar.MONTH);///LocalDate.now().getMonthValue();
		this.year = calendar.get(Calendar.YEAR);//LocalDate.now().getYear();
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
		return m+"/"+this.year;
	}
	
	public int yearNumber() {
		return this.year;
	}
}
