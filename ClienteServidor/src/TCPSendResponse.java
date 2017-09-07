import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class TCPSendResponse implements Runnable{
	
	Socket client;

	public TCPSendResponse(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		
		try {
			ObjectInputStream input = new ObjectInputStream(client.getInputStream());
			Requisicao requisicao = (Requisicao) input.readObject();				
			String resposta = getResposta(requisicao) + "\n";
			System.out.println("Recebida requisição " + requisicao.toString() + " de " + client.getInetAddress());
	        saveToLog(requisicao, client.getInetAddress());
			
			DataOutputStream output = new DataOutputStream(client.getOutputStream());
			output.writeBytes(resposta);
			System.out.println("Enviada resposta " + resposta.replaceAll("[\n]", "") + " para " +  client.getInetAddress());


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private String getResposta(Requisicao requisicao){
		
		switch (requisicao.getOperacao()) {
			case "soma":
				return String.valueOf(requisicao.getNum1() + requisicao.getNum2());
	
			case "sub":
				return String.valueOf(requisicao.getNum1() - requisicao.getNum2());
			
			case "div":
				return String.valueOf(requisicao.getNum1()/requisicao.getNum2());
				
			case "multi":
				return String.valueOf(requisicao.getNum1()*requisicao.getNum2());
				
			default:
				return "Operação errada";
		}		
		
	}
	
	private void saveToLog(Requisicao requisicao, InetAddress addr) {
        
		List<String> line = Arrays.asList("[" + requisicao.getNum1() + " " + requisicao.getNum2() + " " + 
								requisicao.getOperacao() + "]" + "[" + addr + "]");
		Path file = Paths.get("log.txt");
		try {
			Files.write(file,  line, Charset.forName("UTF-8"),
					StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Requisição salva no log");
	}
}
