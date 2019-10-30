package model.util;

public class ExpirationInterval {
	static String statement = "(expiration>=TIMESTAMP(\"$first\",\"00:00:00\")  AND expiration<=TIMESTAMP(\"$last\",\"59:59:59\"))";
	public static String MONTHLY = month();
	public static String WEEKLY = week();
	public static String DAILY = today();
	
	static String month(){
		String m = statement.replace("$first", ExpirationDate.firstDayMonth());
		m = m.replace("$last", ExpirationDate.lastDayMonth());
		return m;
	}
	
	static String week(){
		String w = statement.replace("$first", ExpirationDate.firstDayWeek());
		w = w.replace("$last", ExpirationDate.lastDayWeek());
		return w;
	}
	
	static String today()
	{
		String t = statement.replace("$first", ExpirationDate.today());
		t = t.replace("$last", ExpirationDate.today());
		return t;
	}
	
}
