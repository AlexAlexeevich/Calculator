import java.util.Arrays;

public class ArithmeticParser {

    private final static int QUANTITY_OF_EXPRESSION_ELEMENTS = 3;
    private String arithmeticExpression;
    private boolean isRomanNumeralSystem = true;
    private boolean isDecimalNumeralSystem = true;
    private String romanNumbers = "IVXLC";
    private String[] rangeOfRomanNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    public ArithmeticParser(String arithmeticExpression) {
        this.arithmeticExpression = arithmeticExpression;
    }

    public boolean isRomanNumeralSystem() {
        return isRomanNumeralSystem;
    }

    public String[] parseLine() throws Exception {
        String[] arrayOfExpressionNumbers = arithmeticExpression.split("[-/+*\\s]+");
        String expressionSign = arithmeticExpression.replaceAll("[^+*/-]", "");
        checkNumberSystem(arrayOfExpressionNumbers);

        if (arrayOfExpressionNumbers.length != 2 || expressionSign.length() != 1 || (!isDecimalNumeralSystem && !isRomanNumeralSystem)) {
            System.out.println("Неверный формат выражения");
            throw new Exception();
        }

        if (!checkRangeOfNumbers(arrayOfExpressionNumbers)) {
            System.out.println("Значение введенного числа находится вне диапазона");
            throw new Exception();
        }

        String[] expressionElements = new String[QUANTITY_OF_EXPRESSION_ELEMENTS];
        for (int i = 0; i < QUANTITY_OF_EXPRESSION_ELEMENTS; i++) {
            if (i == 0) {
                expressionElements[i] = expressionSign;
                continue;
            }
            expressionElements[i] = arrayOfExpressionNumbers[i - 1];
        }
        return expressionElements;
    }

    private void checkNumberSystem(String[] arrayOfExpressionNumbers) {
        for (String element : arrayOfExpressionNumbers) {
            for (int i = 0; i < element.length(); i++) {
                if(element.charAt(i) < '0' || element.charAt(i) > '9') {
                    isDecimalNumeralSystem = false;
                }
                if (!romanNumbers.contains(String.valueOf(element.charAt(i)))) {
                    isRomanNumeralSystem = false;
                }
            }
        }
    }

    private boolean checkRangeOfNumbers(String[] arrayOfExpressionNumbers){
        if (isDecimalNumeralSystem) {
            for (String s : arrayOfExpressionNumbers) {
                if (Integer.parseInt(s.strip()) < 1 || Integer.parseInt(s.strip()) > 10) {
                    return false;
                }
            }
        } else if (isRomanNumeralSystem) {
            for (String s : arrayOfExpressionNumbers) {
                if (!Arrays.stream(rangeOfRomanNumbers).anyMatch(s::equals)) {
                    return false;
                }
            }
        }
        return true;
    }
}
