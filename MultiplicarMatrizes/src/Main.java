import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {

		Random rnd = new Random();
		int K = rnd.nextInt(10) + 1;
		int M = rnd.nextInt(10) + 1;
		int N = rnd.nextInt(10) + 1;
		int[][] m1 = geraMatriz(M, K);
		int[][] m2 = geraMatriz(K, N);
		imprime(m1);
		imprime(m2);
		int[][] resultado = new int[M][N];
		
		ArrayList<MultiplicaLinhaColuna> multiplicacoes = new ArrayList<>();

		ThreadPool pool = new ThreadPool(4);
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				MultiplicaLinhaColuna multiplicacao = new MultiplicaLinhaColuna(m1, m2, i, j, pool);
				multiplicacoes.add(multiplicacao);
				while(pool.getCount() >= pool.getMaxThreads()){
					// Se o número máximo de threads for atingido não faça nada
				}
				pool.addThread(new Thread(multiplicacao, String.valueOf(i) + " " + String.valueOf(j)));
			}
		}		
		
		// Esperar todas as threads terminarem.
		for (Thread thread : pool.getThreads()){
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Coloca o resultado de cada multiplicação na matriz resultado
		int k = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				resultado[i][j] = multiplicacoes.get(k).getResultado();
				k++;
			}
		}
		
		imprime(resultado);
	}

	// Método para gerar uma matriz aleatória
	private static int[][] geraMatriz(int nLinhas, int nColunas) {
		int[][] matriz = new int[nLinhas][nColunas];
		Random rnd = new Random();
		for (int i = 0; i < nLinhas; i++) {
			for (int j = 0; j < nColunas; j++) {
				matriz[i][j] = (int) (rnd.nextFloat() * 100 - 50);
			}
		}
		return matriz;
	}

	private static void imprime(int[][] M) {
		System.out.println();
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M[i].length; j++) {
				System.out.print(M[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
