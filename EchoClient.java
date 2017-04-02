
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public final class EchoClient extends Thread{

    public void run(){
        try (Socket socket = new Socket("localhost", 2222)) {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println(br.readLine());
        }
        catch(Exception ex){
        	System.out.println("Client Error: " + ex);
        }
    }
}















