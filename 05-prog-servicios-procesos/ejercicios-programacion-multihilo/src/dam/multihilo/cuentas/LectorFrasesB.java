package dam.multihilo.cuentas;

public class LectorFrasesB extends Thread {

	private String frase;
	final static int milis = 300;


	public LectorFrasesB(String frase) {
		this.frase = frase;
	}




	@Override
	public void run() {
		leer();
	}




	public void leer () {

		String [] palabras = frase.split(" ");
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (String palabra : palabras) {
			System.out.printf("%s", palabra);
		}
		System.out.println();
	}




}
