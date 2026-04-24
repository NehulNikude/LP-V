import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
    int addNumbers(int a, int b) throws RemoteException;
    int powerOfTwo(int n) throws RemoteException;
    double celsiusToF(double c) throws RemoteException;
    double milesToKm(double m) throws RemoteException;
    String getLargerString(String s1, String s2) throws RemoteException;
    int countVowels(String s) throws RemoteException;
    long factorial(int n) throws RemoteException;
}