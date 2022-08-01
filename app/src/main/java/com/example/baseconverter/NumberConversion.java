package com.example.baseconverter;

public class NumberConversion {
    private String decimal;
    private String binary;
    private String octal;
    private String hexadecimal;
    private final int CHAR_A_MINUS_10 = 55;
    public final int NUMBER_OF_DIGITS_TO_TAKE = 10;

    public void show() {
        System.out.println("Binary: " + getBinary());
        System.out.println("Octal: " + getOctal());
        System.out.println("Decimal: " + getDecimal());
        System.out.println("Hexadecimal: " + getHexadecimal());
    }

    private void convertFromBase10(String str) {
        binary = convertBase10ToBaseX(str, 2);
        octal = convertBase10ToBaseX(str, 8);
        hexadecimal = convertBase10ToBaseX(str, 16);
    }
    private String convertBase10ToBaseX(String str, int base) {
        double number = Double.parseDouble(str);
        int integerPart = (int) number;
        double realPart = number - (double) integerPart;
        String answer = convertIntegerPartToBaseX(integerPart, base) + convertRealPartToBaseX(realPart, base, NUMBER_OF_DIGITS_TO_TAKE);
        return answer;
    }

    private String convertIntegerPartToBaseX(int decimal, int base) {
        if (decimal == 0)
            return "0";

        String answer = "";
        while (decimal > 0) {
            char temp = convertNumToChar(decimal % base);
            answer = answer.concat( String.valueOf(temp) );
            decimal /= base;
        }
        return reverse(answer);
    }

    private static String reverse(String str) {
        char[] chrArray = str.toCharArray();
        int len = chrArray.length;
        for (int i = 0; i < len/2; i++) {
            char temp = chrArray[i];
            chrArray[i] = chrArray[len - i - 1];
            chrArray[len - i - 1] = temp;
        }
        return String.valueOf( chrArray );
    }

    private String convertRealPartToBaseX(double decimal, int base, int numberOfDigitsToTake) {
        if (decimal == 0.0)
            return "";

        String answer = "";
        long integerPartOfDecimal;
        int k = 0;
        do {
            decimal *= base;
            integerPartOfDecimal = (long) decimal;
            decimal -= integerPartOfDecimal;
            answer = answer.concat( String.valueOf( convertNumToChar(integerPartOfDecimal) ) );
            k++;
        } while (decimal != (double) integerPartOfDecimal && k < numberOfDigitsToTake);

        return "." + answer;
    }

    private String convertToBase10(String str, int base) {
        int indexOfDot = indexOf(str, '.');
        double ans = 0;
        for (int i = indexOfDot - 1; i >= 0; i--)
            ans += Math.pow(base, indexOfDot - i - 1) * convertCharToNum( str.charAt(i) );

        int len = str.length();
        if (indexOfDot == len)
            return String.valueOf( (int)ans );

        for (int i = indexOfDot + 1; i < len; i++)
            ans += Math.pow(base, indexOfDot - i) * convertCharToNum( str.charAt(i) );

        return String.valueOf( ans );
    }

    private int indexOf(String str, char target) {
        str = str.concat( String.valueOf(target) );
        int i = 0;
        while (str.charAt(i) != target)
            i++;
        return i;
    }

    private int convertCharToNum(char c) {
        if (c >= '0' && c <= '9')
            return c - '0';
        return c - CHAR_A_MINUS_10;
    }
    private char convertNumToChar(long n) {
        if (n >= 0 && n <= 9)
            return (char) ( n + (long) '0' );
        return (char) (n + CHAR_A_MINUS_10);
    }

    public String getDecimal() {
        return decimal;
    }

    public void setDecimal(String decimal) {
        this.decimal = decimal;
        convertFromBase10(decimal);
    }

    public String getBinary() {
        return binary;
    }

    public void setBinary(String binary) {
        decimal = convertToBase10(binary, 2);
        convertFromBase10(decimal);
    }

    public String getOctal() {
        return octal;
    }

    public void setOctal(String octal) {
        decimal = convertToBase10(octal, 8);
        convertFromBase10(decimal);
    }

    public String getHexadecimal() {
        return hexadecimal;
    }

    public void setHexadecimal(String hexadecimal) {
        decimal = convertToBase10(hexadecimal, 16);
        convertFromBase10(decimal);
    }
}
