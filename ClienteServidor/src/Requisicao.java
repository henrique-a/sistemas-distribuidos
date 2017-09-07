import java.io.Serializable;

public class Requisicao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int num1;
	private int num2;
	private String operacao;
	
	public Requisicao(int num1, int num2, String op) {
		this.num1 = num1;
		this.num2 = num2;
		this.operacao = op ;
	}

	public String getOperacao() {
		return operacao;
	}
	
	public int getNum1() {
		return num1;
	}
	
	public int getNum2() {
		return num2;
	}
	
	@Override
	public String toString() {
		return String.valueOf(num1) + " " + String.valueOf(num2) + " " + String.valueOf(operacao);
	}
		
}
