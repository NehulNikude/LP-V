import java.util.Scanner;
import calculator.CalculatorService;
import calculator.CalculatorServiceService;

public class CalculatorClient {
    public static void main(String[] args) {
        CalculatorServiceService serviceFactory = new CalculatorServiceService();
        CalculatorService calculator = serviceFactory.getCalculatorServicePort();

        System.out.println("==============================");
        System.out.println("  SOAP Calculator Web Client  ");
        System.out.println("==============================");
        System.out.println("Connected to: http://localhost:8080/calculator");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect operation:");
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            if (choice == 5) {
                System.out.println("Exiting client. Goodbye!");
                break;
            }

            System.out.print("Enter first number (a): ");
            int a = sc.nextInt();
            System.out.print("Enter second number (b): ");
            int b = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Result: " + a + " + " + b + " = " + calculator.add(a, b));
                    break;
                case 2:
                    System.out.println("Result: " + a + " - " + b + " = " + calculator.subtract(a, b));
                    break;
                case 3:
                    System.out.println("Result: " + a + " * " + b + " = " + calculator.multiply(a, b));
                    break;
                case 4:
                    try {
                        System.out.println("Result: " + a + " / " + b + " = " + calculator.divide(a, b));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
