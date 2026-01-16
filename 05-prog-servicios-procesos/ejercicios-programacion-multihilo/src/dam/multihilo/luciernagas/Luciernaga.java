package dam.multihilo.luciernagas;

public class Luciernaga {
	private String nombre;
	private boolean encendido;
	private int energia;
	private static int milis = 300;



	public Luciernaga(String nombre, int energia) {
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


	public static void main(String[] args) {
		Luciernaga l1 = new Luciernaga("L1", 5);
		Luciernaga l2 = new Luciernaga("L2", 8);
		Luciernaga l3 = new Luciernaga("L3", 2);

		l1.enciende();
		l2.enciende();
		l3.enciende();


	}



}
