
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
                	
                	InputStream is = socket.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                    BufferedReader br = new BufferedReader(isr);
                	
                    String address = socket.getInetAddress().getHostAddress();
                    System.out.printf("Server> Client connected: %s%n", address);
                    OutputStream os = socket.getOutputStream();
                    PrintStream out = new PrintStream(os, true, "UTF-8");
                    out.printf("Hi %s, thanks for connecting!%n", address);
                    
                    String echo = br.readLine();
                    while(!echo.equals("exit")){
                    	out.println("Server> " + echo);
                    	echo = br.readLine();
                    }
                    System.out.println("Server> Client Disconnect");
                }
                catch(Exception ex){
                	System.out.println("Socket accept error: " + ex + ex.getStackTrace());
                }
            }
        }
        catch(Exception ex){
        	//System.out.println("Socket bind error: " + ex);
        }
    }
}
