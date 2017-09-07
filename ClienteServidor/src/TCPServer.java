import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		
		try {
			ServerSocket server = new ServerSocket(port);
			System.out.println("Servidor ouvindo a porta " + port);
			
			while(true) {
				Socket client = server.accept();
		        TCPSendResponse sendResponse = new TCPSendResponse(client);
		        Thread thread = new Thread(sendResponse);
		        thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
