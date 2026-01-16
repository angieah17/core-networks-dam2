package dam.multihilo.lanzador;

public class Botella {
	private String nombre;
	private int cantidad;
	private char caracter;
	private static long milis;

	static {
		//bloque estático que se ejecuta al crearse la clase
		milis = 300;
	}



	public Botella(String nombre, int cantidad, char caracter) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.caracter = caracter;
	}



	public void vaciar() {

		while(cantidad > 0) {
			cantidad--;
			System.out.print(caracter);

			try {
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


	}




	/*public static void main(String[] args) {

		Botella b1 = new Botella("Botella 1",10, '*');


		b1.vaciar();

	}*/


}
