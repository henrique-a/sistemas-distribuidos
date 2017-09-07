import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
	public static void main(String[] args) {
		
		int port = Integer.parseInt(args[0]);
		
        byte[] receiveData = new byte[1024];        
		
		try {			
		
			DatagramSocket server = new DatagramSocket((port));
			
			while (true) {
				
	            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	            server.receive(receivePacket);
				System.out.println("Servidor ouvindo a porta: " + port);
				UDPSendResponse sendResponse = new UDPSendResponse(server, receivePacket);
				Thread thread = new Thread(sendResponse);
				thread.start();
                
			}

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

}
