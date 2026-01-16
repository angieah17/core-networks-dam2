package dam.multihilo.botellas;

public class BotellaThread extends Thread {

	private int capacidadMax;
	private int capacidadOcupada;
	private char caracter;
	private static long milis;

	static {
		//bloque estático que se ejecuta al crearse la clase
		milis = 300;
	}


	public BotellaThread (char caracter, int capacidadMax) {
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
	public void run() {
		vaciar();
	}



	@Override
	public String toString() {
		return String.format("Botella de %c con capacidad %d de %d%n", caracter, capacidadOcupada, capacidadMax);
	}


	public static void main(String[] args) {

		BotellaThread bt1 = new BotellaThread('*', 10);
		BotellaThread bt2 = new BotellaThread('-', 5);

		bt1.start();
		bt2.start();

		//System.out.println(b1);
		//System.out.println(b2);
	}




}
