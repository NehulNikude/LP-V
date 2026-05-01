import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;

public class TokenRingMutex {
    private int myPort;
    private String nextHost;
    private int nextPort;
    private boolean hasToken;

    TokenRingMutex (int myPort, String nextHost, int nextPort, boolean hasToken) {
        this.myPort = myPort;
        this.nextHost = nextHost;
        this.nextPort = nextPort;
        this.hasToken = hasToken;
    }

    public void startCirculation() throws Exception {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(myPort)) {
                while (true) { 
                    Socket socket = serverSocket.accept();
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    String token = in.readUTF();

                    if (token.equals("TOKEN")) {
                        System.out.println("Token Received from  " + socket.getRemoteSocketAddress() + "\n");
                        hasToken = true;
                    }
                    socket.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        while (true) { 
            if (hasToken) {
                enterCriticalSection();
                sendToken();
                hasToken = false;
            }
            Thread.sleep(2000);
        }
    }

    private void enterCriticalSection() throws Exception {
        System.out.println(">>>>>> ENTERED CRITICAL SECTION\n");
        System.out.println("------Simulate Work\n");
        Thread.sleep(3000);
        System.out.println("<<<<<< EXITED CRITICAL SECTION\n");
    }

    private void sendToken() throws Exception {
        try(Socket socket = new Socket(nextHost, nextPort);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());) {
                out.writeUTF("TOKEN");
                System.out.println("Token Sent to " + socket.getRemoteSocketAddress() + "\n");
        } catch (Exception e) {
            System.out.println("Failed to send token");
        }
    }

    public static void main(String[] args) throws Exception {
        int myPort = Integer.parseInt(args[0]);
        String nextHost = args[1];
        int nextPort = Integer.parseInt(args[2]);
        boolean hasToken = Boolean.parseBoolean(args[3]);

        TokenRingMutex tokenRingMutex = new TokenRingMutex(myPort, nextHost, nextPort, hasToken);

        tokenRingMutex.startCirculation();
    }
}