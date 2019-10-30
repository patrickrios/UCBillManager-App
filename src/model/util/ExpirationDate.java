package model.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ExpirationDate {

	private static SimpleDateFormat f = new SimpleDateFormat("y-MM-dd");
	
	public static String today(){
		Date today = new Date();
		return f.format(today);
	}
	
	public static String firstDayWeek(){
		Date firstDay = week(true);
		return f.format(firstDay);
	}
	
	public static String lastDayWeek(){
		Date lastDay = week(false);
		return f.format(lastDay);
	}
	
	public static String firstDayMonth(){
		Date firstDay = month(true);
		return f.format(firstDay);
	}
	
	public static String lastDayMonth(){
		Date lastDay = month(false);
		return f.format(lastDay);
	}
	
	public static Date week(boolean isFirst){

	       Date dataGB = new Date();

	        GregorianCalendar calendar = new GregorianCalendar();
	        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
	        calendar.setTime(dataGB);

	        if (isFirst) 
	            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
	        else 
	            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
	        
	        return calendar.getTime();
	    }
	
	public static Date month(boolean isFirst)
	{
		Date dataGB = new Date();

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(dataGB);

        if (isFirst) 
            calendar.set(Calendar.DAY_OF_MONTH, 1);
        else 
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        
        return calendar.getTime();
	}
}