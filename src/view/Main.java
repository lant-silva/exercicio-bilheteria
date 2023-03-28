package view;
import java.util.concurrent.Semaphore;
import controller.ThreadBilheteria;

public class Main {
	
	static Semaphore confirmar;
	
	public static void main(String[] args) {
		
		confirmar = new Semaphore(1);
		for (int i=1;i<=300;i++) {
			Thread venda = new ThreadBilheteria(i, confirmar);
			venda.start();
		}
		
	}
	
}
