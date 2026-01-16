package dam.multihilo.botellas;

public class BotellaRunnable implements Runnable {

	private int capacidadMax;
	private int capacidadOcupada;
	private char caracter;
	private static long milis;

	static {
		//bloque estático que se ejecuta al crearse la clase
		milis = 300;
	}


	public BotellaRunnable (char caracter, int capacidadMax) {
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

		BotellaRunnable br1 = new BotellaRunnable('*', 10);
		BotellaRunnable br2 = new BotellaRunnable('-', 5);
		BotellaRunnable br3 = new BotellaRunnable('$', 5);

		new Thread(br1).start();
		new Thread(br2).start();
		new Thread(br3).start();
		//System.out.println(b1);
		//System.out.println(b2);
	}




}
