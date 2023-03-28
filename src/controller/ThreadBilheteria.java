package controller;
import java.util.concurrent.Semaphore;

public class ThreadBilheteria extends Thread {

	Semaphore confirmar;
	int cadastro;
	static int maxIngressos = 100;

	public ThreadBilheteria(int i, Semaphore confirmar) {
		cadastro = i;
		this.confirmar = confirmar;
	}
	
	@Override
	public void run() {
		login();
	}
	
	public int random(int min, int max) {
		int temp = (int) (Math.random()*max)+min;
		return temp;
	}
	
	public void login() {
		int tLogin = random(50,1950);
		
		try {
			sleep(tLogin);
		} catch (InterruptedException e) {
		}
		
		if (tLogin > 1000) {
			System.out.println("(Cadastro "+cadastro+") Tempo Esgotado. Entre no sistema novamente");	
		}else {
			System.out.println("(Cadastro "+cadastro+") Login Efetuado");
			compra();
		}
	}
	public void compra() {
		int tCompra = random(1000, 3000);
		try {
			sleep(tCompra);
		} catch (InterruptedException e) {
		}
		
		if (tCompra >2500) {
			System.out.println("(Cadastro "+cadastro+") Tempo Esgotado, nao foi possivel efetuar a compra");
		}else {
			int qtdeIngressos = random(1, 4);
			System.out.println("Efetuando confirmação...");
			confirma(qtdeIngressos);
		}
	}
	public void confirma(int ingressos) {
		try {
			confirmar.acquire();
			if (ingressos > maxIngressos) {
				System.out.println("Ingressos esgotados");
			}else {
				maxIngressos -= ingressos;
				System.out.println("(Cadastro "+cadastro+") Compra Efetuada!");
				System.out.println("Ingressos restantes: "+maxIngressos);
			}
		} catch (Exception e) {
			
		} finally {
			confirmar.release();
		}
	}
}
