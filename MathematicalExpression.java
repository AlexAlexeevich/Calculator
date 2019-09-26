public class MathematicalExpression {
    private String expressionLine;
    private char arithmeticOperation;
    private String firstNumber;
    private String secondNumber;
    private boolean isRomanNumeralSystem;
    private String[] romanNumbers = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
    private int[] decimalNumbers = {1, 4, 5, 9, 10, 40, 50, 90, 100};

    public MathematicalExpression(String expressionLine) {
        this.expressionLine = expressionLine;
    }

    public String performAnAction() {
        parseLineToElements();
        int result = 0;
        switch (arithmeticOperation) {
            case '+':
                result = Integer.parseInt(firstNumber) + Integer.parseInt(secondNumber);
                break;
            case '-':
                result = Integer.parseInt(firstNumber) - Integer.parseInt(secondNumber);
                break;
            case '/':
                result = Integer.parseInt(firstNumber) / Integer.parseInt(secondNumber);
                break;
            case '*':
                result = Integer.parseInt(firstNumber) * Integer.parseInt(secondNumber);
                break;
        }
        if (isRomanNumeralSystem) {
            return convertDecimalNumbersToRoman(result);
        }
        return String.valueOf(result);
    }

    private void parseLineToElements() {
        ArithmeticParser arithmeticParser = new ArithmeticParser(expressionLine);
        String[] arrayOfValidElements = new String[0];
        try {
            arrayOfValidElements = arithmeticParser.parseLine();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        arithmeticOperation = arrayOfValidElements[0].charAt(0);
        firstNumber = arrayOfValidElements[1];
        secondNumber = arrayOfValidElements[2];
        isRomanNumeralSystem = arithmeticParser.isRomanNumeralSystem();

        if (arithmeticParser.isRomanNumeralSystem()) {
            firstNumber = convertRomanNumbersToDecimal(arrayOfValidElements[1]);
            secondNumber = convertRomanNumbersToDecimal(arrayOfValidElements[2]);
        }
    }

    private String convertRomanNumbersToDecimal(String number) {
        String result = "";
        switch (number) {
            case "I":
                result = "1";
                break;
            case "II":
                result = "2";
                break;
            case "III":
                result = "3";
                break;
            case "IV":
                result = "4";
                break;
            case "V":
                result = "5";
                break;
            case "VI":
                result = "6";
                break;
            case "VII":
                result = "7";
                break;
            case "VIII":
                result = "8";
                break;
            case "IX":
                result = "9";
                break;
            case "X":
                result = "10";
                break;
        }
        return result;
    }

    private String convertDecimalNumbersToRoman(int result) {
        String romanNumber = "";
        int i = decimalNumbers.length - 1;
        int tempResult = 0;
        while (i >= 0) {
            tempResult = result / decimalNumbers[i];
            if (tempResult > 0) {
                for (int j = 0; j < tempResult; j++) {
                    romanNumber += romanNumbers[i];
                }
                result %= decimalNumbers[i];
            }
            i--;
            if (result == 0) {
                break;
            }
        }
        return romanNumber;
    }

}
