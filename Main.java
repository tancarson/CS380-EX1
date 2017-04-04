
public class Main {

	public static void main(String[] args) throws InterruptedException {
		if(args.length == 0){
			EchoServer server = new EchoServer();
			server.start();
		}

		EchoClient c1 = new EchoClient();
		
		c1.start();
	}

}
