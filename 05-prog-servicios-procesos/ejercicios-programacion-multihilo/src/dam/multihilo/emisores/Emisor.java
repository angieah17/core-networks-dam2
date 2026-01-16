package dam.multihilo.emisores;

public class Emisor {

	private String nombre;
	private char caracter;
	private int segundos;
	private static long milis = 300;
	private static final String ROJO = "\u001B[31m";
	private static final String VERDE = "\u001B[32m";
	private static final String RESET = "\u001B[0m";


	public Emisor(String nombre, char caracter, int segundos) {
		this.nombre = nombre;
		this.caracter = caracter;
		this.segundos = segundos;
	}


	public Emisor(char caracter, int segundos) {
		this("sin nombre", caracter, segundos);
	}


	public String getNombre() {
		return nombre;
	}

	public void emite() {

		//TimeStamp: desde 1970 cuántos milisegundos han pasado

		long timeStampMax = System.currentTimeMillis() + segundos * 1_000;

		System.out.print(VERDE + caracter + RESET);

		while(System.currentTimeMillis() < timeStampMax) {
			System.out.print(caracter);

			try {

				Thread.sleep(milis);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.print(ROJO + caracter + RESET);

	}





}
