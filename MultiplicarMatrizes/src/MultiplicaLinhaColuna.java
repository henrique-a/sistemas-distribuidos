
public class MultiplicaLinhaColuna implements Runnable{
	
	private int linha;
	private int coluna;
	private int[][] m1;
	private int[][] m2;
	private int resultado = 0;
	private ThreadPool pool;
	
	public MultiplicaLinhaColuna(int[][] m1, int[][] m2, int linha, int coluna, ThreadPool pool) {
		this.linha = linha;
		this.coluna = coluna;
		this. m1 = m1;
		this.m2 = m2;
		this.pool = pool;
	}
	
	@Override
	public void run() {

		for(int i = 0; i < m2.length; i++){
			resultado += m1[linha][i] * m2[i][coluna];
		}
		
		pool.removeThread(linha, coluna);
		
	}

	public int getResultado() {
		return resultado;
	}
	
}
