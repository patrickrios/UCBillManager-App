package view.util;

public class RealFormat {

	public static String floatToRealString(float value)
	{
		String valueString = Float.toString(value);
		valueString = valueString.replace(".", ",");
		
		return "R$ "+valueString;
	}
	
	public static float realStringToFloat(String value)
	{
		value = value.replace("R$ ", "");
		value = value.replace(",", ".");
		
		return Float.valueOf(value);
	}
}
