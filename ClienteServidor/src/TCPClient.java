import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCPClient {
	
	public static void main(String[] args) {
		
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		int num1 = Integer.parseInt(args[2]);
		int num2 = Integer.parseInt(args[3]);
		String operacao = args[4];		
		
		Requisicao requisicao = new Requisicao(num1, num2, operacao);
		
		try {
			Socket client = new Socket(host, port);
		    ObjectOutputStream sendObject = new ObjectOutputStream(client.getOutputStream());
		    sendObject.writeObject(requisicao);	   
		    
		    BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String resposta = input.readLine();
			System.out.println("Resposta: " + resposta);
			
			client.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
