import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSendResponse implements Runnable{
	
	DatagramSocket server;
	DatagramPacket receivePacket;
    byte[] sendData = new byte[1024];

	
	public UDPSendResponse(DatagramSocket server, DatagramPacket receivePacket) {
		this.server = server;
		this.receivePacket = receivePacket;
	}
	
	public void run() {
		
		String msg = new String( receivePacket.getData());
        InetAddress clientIP = receivePacket.getAddress();

        System.out.println("Recebida mensagem: " + msg + " de " + clientIP);
         
        int port = receivePacket.getPort();	                  
        String response = new StringBuilder(msg).reverse().toString();	                  
        sendData = response.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIP, port);
        
        try {
			server.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}                 

		
	}

}
