import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public final class EchoClient extends Thread{
	
    public void run(){
        try (Socket socket = new Socket("localhost", 2222)) {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            
            System.out.println("Client>" + br.readLine());
            
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os, true, "UTF-8");
            
            BufferedReader co = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Client> Enter message: ");
            String message = co.readLine();
            while(!message.equals("exit")){
            	out.println(message);
            	System.out.println(br.readLine());
            	System.out.print("Client> Enter message: ");
            	message = co.readLine();
            }
            out.println(message);
            //Thread.sleep(20);
            //EchoClient next = new EchoClient();
            //next.start();
        }
        catch(Exception ex){
        	System.out.println("Client Error: " + ex);
        }
    }
}















