import java.rmi.registry.*;

public class Client {
    public static void main(String[] args) throws Exception {

        Registry registry = LocateRegistry.getRegistry("localhost", 1099);

        ServerInterface stub = (ServerInterface) registry.lookup("AddServer");
        
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("Addition (5 + 10): " + stub.addNumbers(5, 10));
            }
            catch (Exception e) {}
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println("2's power of 5: " + stub.powerOfTwo(5));
            }
            catch (Exception e) {}
        });

        Thread t3 = new Thread(() -> {
            try {
                System.out.println("32 degrees Celsius to Fahrenheit: " + stub.celsiusToF(32));
            }
            catch (Exception e) {}
        });

        Thread t4 = new Thread(() -> {
            try {
                System.out.println("10 miles to Km: " + stub.milesToKm(10) + "Km");
            }
            catch (Exception e) {}
        });

        Thread t5 = new Thread(() -> {
            try {
                System.out.println("Lexicographic comparison (apple, banana): " + stub.getLargerString("apple", "banana") + " is larger");
            }
            catch (Exception e) {}
        });

        Thread t6 = new Thread(() -> {
            try {
                System.out.println("Count vowels (Apple): " + stub.countVowels("Apple"));
            }
            catch (Exception e) {}
        });

        Thread t7 = new Thread(() -> {
            try {
                System.out.println("Factorial of 5 = " + stub.factorial(5));
            }
            catch (Exception e) {}
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
    }
}