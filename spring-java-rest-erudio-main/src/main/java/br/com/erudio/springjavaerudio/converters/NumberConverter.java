package br.com.erudio.springjavaerudio.converters;

public class NumberConverter {

    public static Double converToDouble(String strNumber) {
        if (strNumber == null) return 0D;

        // para trazer o que tá em reais para dolar 10.05
        String number = strNumber.replaceAll(",",".");
        if(isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null) return false;
        String number = strNumber.replaceAll(",",".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
