package dam.multihilo.repaso3;

public class Luciernaga {
	private String nombre;
	private boolean encendido;
	private int energia;
	private static final int milis = 300;


	public Luciernaga(String nombre, int energia) {
		this.nombre = nombre;
		this.energia = energia >= 1 && energia <= 50 ? energia : 1;
	}


	public void enciende () {

		if(encendido) {
			System.out.println("La luciérnaga ya está encendida");
			return;
		}

		if(energia <= 0) {
			System.out.println("La luciérnaga no tiene energía suficiente para encenderse");
		}

		encendido = true;


			for (int i = energia; i > 0; i--) {
				System.out.printf("%s - Energía: %d%n", nombre, energia--);
				try {
					Thread.sleep(milis);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		encendido = false;



	}


	public static void main(String[] args) {

		Luciernaga l1 = new Luciernaga("L1", 10);
		Luciernaga l2 = new Luciernaga("L2", 5);
		Luciernaga l3 = new Luciernaga("L3", 3);

		/*l1.enciende();
		l2.enciende();
		l3.enciende();*/

		new LanzadorLuciernagas(l1).start();
		new LanzadorLuciernagas(l2).start();
		new LanzadorLuciernagas(l3).start();





	}



}
