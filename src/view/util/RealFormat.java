package view.util;

public class RealFormat {

	public static String floatToRealString(float value){
		String valueString = String.format("R$ %,.2f", value);		
		return valueString;
	}
	
	public static float realStringToFloat(String value){
		value = value.replace("R$ ", "");
		value = value.replace(",", ".");
		
		return Float.valueOf(value);
	}
	
	public static String stringWithoutPrefix(float value){
		String valueString = Float.toString(value);
		valueString = valueString.replace(".", ",");
		
		return valueString;
	}
}
