import java.util.Scanner;

public class Load {
    public static void main(String[] args) {

        System.out.println("Input: ");
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine().strip();

        MathematicalExpression mathematicalExpression = new MathematicalExpression(expression);
        System.out.println("Output:\n" + mathematicalExpression.performAnAction());
    }
}
