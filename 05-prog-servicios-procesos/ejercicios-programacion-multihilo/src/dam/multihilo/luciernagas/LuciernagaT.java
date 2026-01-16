package dam.multihilo.luciernagas;

public class LuciernagaT extends Thread {
	private String nombre;
	private boolean encendido;
	private int energia;
	private static int milis = 300;



	public LuciernagaT(String nombre, int energia) {
		this.nombre = nombre;
		this.energia = energia >= 1 && energia <= 50 ? energia : 1;
	}

	public void enciende() {

		if(encendido) {
			System.out.println("La luciérnaga ya está encendida");
			return;
		}

		if (energia <= 0) {
			System.out.println("La luciérnaga no tiene energía suficiente para encenderse");
			return;
		}


		encendido = true;

		while (energia > 0) {
				try {
					System.out.printf("Energía de %s: %d%n", nombre ,energia--);
					Thread.sleep(milis);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}

		encendido = false;



	}



	@Override
	public void run() {
		enciende();
	}



	public static void main(String[] args) {
		LuciernagaT l1 = new LuciernagaT("L1", 5);
		LuciernagaT l2 = new LuciernagaT("L2", 8);
		LuciernagaT l3 = new LuciernagaT("L3", 2);

		l1.start();
		l2.start();
		l3.start();

	}



}
