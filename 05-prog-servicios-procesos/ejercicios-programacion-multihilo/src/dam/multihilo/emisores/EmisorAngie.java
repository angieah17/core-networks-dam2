package dam.multihilo.emisores;

public class EmisorAngie {
	//si se concatena por delante todo lo que sigue sale en ese color. 1 verde, negro, rojo

	private String nombre;
	private char caracter;
	private int segundos;
	private static long milis = 300;
	private static final String ROJO = "\u001B[31m";
	private static final String VERDE = "\u001B[32m";
	private static final String RESET = "\u001B[0m";


	public EmisorAngie(String nombre, char caracter, int segundos) {
		this.nombre = nombre;
		this.caracter = caracter;
		this.segundos = segundos;
	}


	public EmisorAngie(char caracter, int segundos) {
		this("sin nombre", caracter, segundos);
	}


	public String getNombre() {
		return nombre;
	}

	public void emite() {

		/*while(tiempoDeEmision < tiempoMaxDeEmision) {

		}*/


		for (int i = 1; i <= segundos; i++) {

			try {
				if(i == 1) {
					System.out.print(VERDE + caracter + RESET);
				}else if (i == segundos){
					System.out.print(ROJO + caracter + RESET);
				}else {
					System.out.print(caracter);
				}

				Thread.sleep(milis);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}


	}





}
