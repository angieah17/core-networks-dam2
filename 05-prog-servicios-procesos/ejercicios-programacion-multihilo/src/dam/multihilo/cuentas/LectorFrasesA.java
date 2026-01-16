package dam.multihilo.cuentas;

public class LectorFrasesA {
	//frase y mili y el método cuando se recorra palabra a palabra

	private String frase;
	final static int milis = 300;




	public LectorFrasesA(String frase) {
		this.frase = frase;
	}




	public void leer () {

		String [] palabras = frase.split(" ");



		for (String palabra : palabras) {
			System.out.printf("%s", palabra);

			try {
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println();
	}

	//Clase terminada en A no multihilo y clase terminada B si es multithreada probar todo en clase aparte
	//Deletrar A y Deletrar B ,



}
