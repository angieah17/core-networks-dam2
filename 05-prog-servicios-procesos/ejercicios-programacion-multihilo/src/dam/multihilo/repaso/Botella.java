package dam.multihilo.repaso;

public class Botella {

	//MÉTODO 3

	private String nombre;
	private int cantidad;
	private char caracter;
	private static long milis = 300;



	public Botella(String nombre, int cantidad, char caracter) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.caracter = caracter;
	}


	public void vaciar() {

		while(cantidad > 0) {

			System.out.print(caracter);
			cantidad--;

			try {
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}


		}

	}



}
