package br.com.erudio.converters;

public class NumberConverter {
	
	public static boolean isNumeric(String strNumber) {
		if (strNumber == null) return false;
		strNumber = strNumber.replaceAll(",", ".");
		try {
            Double.parseDouble(strNumber);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
	}
	
	public static Double convertToDouble(String strNumber) {
		strNumber = strNumber.replaceAll(",", ".");
		return Double.parseDouble(strNumber);
	}
}
