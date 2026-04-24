import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class InterfaceImpl extends UnicastRemoteObject implements ServerInterface {

    protected InterfaceImpl () throws RemoteException{
        super();
    }

    @Override
    public int addNumbers(int a, int b) {
        System.err.println("Adding: " + a + "+" + b + "=" + (a + b));
        return a + b;
    }

    @Override
    public int powerOfTwo(int n) {
        System.out.println("2 ^ " + n +" = " + (1 << n));
        return (1 << n);
    }

    @Override
    public double celsiusToF(double c) {
        System.out.println(c + " degree celsius = " + (c*1.8+32) + " Farenheit");
        return (c*1.8+32);
    }

    @Override
    public double milesToKm(double m) {
        System.out.println(m + " miles = " + (m*1.6) + " Km");
        return (m*1.6);
    }

    @Override
    public String getLargerString(String s1, String s2) {
        if (s1.compareTo(s2) > 0) {
            System.out.println(s1 + " is greater than " + s2);
            return s1;
        } else {
            System.out.println(s2 + " is greater than " + s1);
            return s2;
        }
    }

    @Override
    public int countVowels(String s) {
        int count = 0;
        s = s.toLowerCase();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count ++;
            }
        }

        System.out.println("Vowel Count: " + count);

        return count;
    }

    @Override
    public long factorial(int n) {
        long fact = 1;

        for (int i = 1; i <= n; i++) {
            fact *= i;
        }

        System.out.println("Factorial of " + n + " = " + fact);

        return fact;
    }
}