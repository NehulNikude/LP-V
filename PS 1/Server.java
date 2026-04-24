import java.rmi.registry.*;

public class Server {
    public static void main(String[] args) throws Exception {
        InterfaceImpl obj = new InterfaceImpl();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("AddServer", obj);
        System.out.println("Server is ready.");
    }
}