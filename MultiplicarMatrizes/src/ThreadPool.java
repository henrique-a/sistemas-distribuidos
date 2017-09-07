import java.util.ArrayList;
import java.util.function.Predicate;

public class ThreadPool {
	
	private int maxThreads;
	private int count = 0;
	private ArrayList<Thread> threads = new ArrayList<>();
	
	public ThreadPool(int maxThreads) {
		this.maxThreads = maxThreads;
	}
	
	public synchronized void addThread(Thread t) {		
		count++;
		threads.add(t);
		threads.get(threads.size() - 1).start();
		System.out.println("Thread " +  threads.get(threads.size() - 1).getName() + " iniciou");
		System.out.println("Número de threads: " + count);
	}
	
	public synchronized void removeThread(int linha, int coluna) {		
		count--;
        Predicate<Thread> threadPredicate = t-> t.getName().equals(String.valueOf(linha) + String.valueOf(coluna));
		threads.removeIf(threadPredicate);
		System.out.println("Thread " + linha + " " + coluna + " terminou");
		System.out.println("Número de threads: " + count);
	}
	
	public synchronized int getCount() {
		return count;
	}
	
	public int getMaxThreads() {
		return maxThreads;
	}

	public ArrayList<Thread> getThreads() {
		return threads;
	}
	
}
