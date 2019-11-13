package view.components;

import java.sql.Date;
import java.util.Calendar;

public class MonthPicker {
	private int month;
	private int year;
	private String[] names = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
	
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
		String m = this.names[this.month-1];
		return m;
	}
	
	public int monthNumber() {
		return this.month;
	}
	
	public String monthAndYear() {
		String m = this.names[this.month-1];
		return m+"/"+this.year;
	}
	
	public int yearNumber() {
		return this.year;
	}
	
	public int nextYear() {
		this.year++;
		return this.year;
	}
	
	public int previousYear() {
		this.year--;
		return this.year;
	}
}

