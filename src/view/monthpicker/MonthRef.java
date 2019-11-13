package view.monthpicker;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

public class MonthRef {
	private int month;
	private int year;
	private String[] names = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
	
	public MonthRef(int month, int year) {
		this.month = month;
		this.year = year;
	}
	
	public MonthRef() {		
		//Calendar calendar = Calendar.getInstance();
		
		this.month = LocalDate.now().getMonthValue();//calendar.get(Calendar.MONTH);///
		this.year = LocalDate.now().getYear();//calendar.get(Calendar.YEAR);//
	}
	
	public MonthRef(Date date) {
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
	
	public String year() {
		return this.year+"";
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

