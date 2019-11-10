package model.util;

public class ExpirationInterval {
	static String statement = "(expiration>=DATE(\"$first\")  AND expiration<=DATE(\"$last\"))";
	public static final String MONTHLY = month();//\"00:00:00\" \"23:59:59\"
	public static final String WEEKLY = week();
	public static final String DAILY = today();
	
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
	
	public static String getIntervalOfInt(int type)
	{
		String interval = "";
		if(type==0)
			interval = today();
		else if(type==1)
			interval = week();
		else
			interval = month();
		return interval;	
	}
	
}
