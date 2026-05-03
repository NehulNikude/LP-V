import javax.xml.ws.Endpoint;

public class CalculatorPublisher {
    public static void main(String[] args) {
        String url = "http://localhost:8080/calculator";
        Endpoint.publish(url, new CalculatorService());
        System.out.println("Calculator Web Service is running at:");
        System.out.println(url + "?wsdl");
    }
}