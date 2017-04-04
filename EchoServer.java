
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public final class EchoServer extends Thread {
    public void run() {
    	ServerSocket serverSocket = null;
    	Socket socket = null;
    	
        try {
        	serverSocket = new ServerSocket(2222);
        }
        catch(Exception ex){
        	System.out.println("Socket bind error: " + ex);
        }
        while (true) {
            try {
            	socket = serverSocket.accept();
            	new EchoClientHandler(socket).start();
            }
            catch(Exception ex){
            	System.out.println("Socket accept error: " + ex + ex.getStackTrace());
            }
        }
    }
}

class EchoClientHandler extends Thread{
	private Socket socket;
	
	EchoClientHandler(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
    		//EchoServer replacementServer = new EchoServer();
        	//replacementServer.start();
        	try{
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
            socket.close();
        	}catch(Exception ex){
        		//System.out.println(ex);
        	}
	}
}
