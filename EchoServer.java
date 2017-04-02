
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public final class EchoServer extends Thread {

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(2222)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                	//EchoServer replacementServer = new EchoServer();
                	//replacementServer.start();
                	
                    String address = socket.getInetAddress().getHostAddress();
                    System.out.printf("Client connected: %s%n", address);
                    OutputStream os = socket.getOutputStream();
                    PrintStream out = new PrintStream(os, true, "UTF-8");
                    out.printf("Hi %s, thanks for connecting!%n", address);
                }
                catch(Exception ex){
                	System.out.println("Socket accept error: " + ex);
                }
            }
        }
        catch(Exception ex){
        	//System.out.println("Socket bind error: " + ex);
        }
    }
}
