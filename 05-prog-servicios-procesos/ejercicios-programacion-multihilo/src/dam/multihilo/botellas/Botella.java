package dam.multihilo.botellas;

public class Botella {

	private int capacidadMax;
	private int capacidadOcupada;
	private char caracter;
	private static long milis;

	static {
		//bloque estático que se ejecuta al crearse la clase
		milis = 300;
	}


	public Botella(char caracter, int capacidadMax) {
		this.capacidadMax = capacidadMax > 0 ? capacidadMax : 1;
		capacidadOcupada = this.capacidadMax;
		this.caracter = caracter;
	}

	public void vaciar() {

		while(capacidadOcupada > 0) {
			capacidadOcupada--;
			System.out.print(caracter);

			try {
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}


	}



	@Override
	public String toString() {
		return String.format("Botella de %c con capacidad %d de %d%n", caracter, capacidadOcupada, capacidadMax);
	}

	public static void main(String[] args) {

		Botella b1 = new Botella('*', 10);
		Botella b2 = new Botella('-', 5);

		b1.vaciar();
		b2.vaciar();

		//System.out.println(b1);
		//System.out.println(b2);
	}


}
