
public class Main {

	public static void main(String[] args) {
		EchoServer server = new EchoServer();
		server.start();
		
		EchoClient c1 = new EchoClient();
		EchoClient c2 = new EchoClient();
		
		c1.start();
		c2.start();
	}

}
