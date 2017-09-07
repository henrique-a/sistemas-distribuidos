import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDPClient {

	public static void main(String[] args) {
		
				
		int porta = Integer.parseInt(args[1]);
		String msg = args[2];	
		
        try {
        	DatagramSocket client = new DatagramSocket();
        	client.setSoTimeout(1000);
        	InetAddress host = InetAddress.getByName(args[0]); 
        	
        	byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            
            sendData = msg.getBytes();            
            
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, host, porta); 
            try {
    			client.send(sendPacket);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
            
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
            try {
				client.receive(receivePacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
            
            String resposta = new String(receivePacket.getData());
            
            System.out.println("Mensagem invertida: " + resposta);
            
            client.close();
  

        } catch (UnknownHostException e) {
        	System.err.println("Host desconhecido: " + args[0]);
        } catch (IOException e) {
        	System.err.println(e);
		}
        
	}

}
