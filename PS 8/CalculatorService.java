import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService(targetNamespace = "http://calculator")
public class CalculatorService {
    @WebMethod
    public int add(int a, int b) {
        System.out.println("Server: add(" + a + "," + b + ") called");
        return a + b;
    }

    @WebMethod
    public int subtract(int a, int b) {
        System.out.println("Server: subtract(" + a + "," + b + ") called");
        return a - b;
    }
    @WebMethod
    public int multiply(int a, int b) {
        System.out.println("Server: multiply(" + a + "," + b + ") called");
        return a * b;
    }
    @WebMethod
    public double divide(int a, int b) {
        System.out.println("Server: divide(" + a + "," + b + ") called");
        if (b == 0) throw new ArithmeticException("Division by 0 not allowed");
        return (double) a / b;
    }
}
