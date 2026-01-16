package dam.multihilo.repaso4;

public class Luciernaga {
	private String nombre;
	private boolean encendida;
	private int energia;
	private static final long milis = 300;



	public Luciernaga(String nombre, int energia) {
		this.nombre = nombre;
		this.energia = energia >= 1 && energia <= 50 ? energia : 1 ;
	}

	public void enciende() {

		if(encendida) {
			System.out.println("La luciérnaga ya está encendida");
			return;
		}

		if(energia <= 0) {
			System.out.println("La luciérnaga no tiene energía");
			return;
		}

		encendida = true;

		for (int i = energia; i >= 0; i--) {
			System.out.printf("Luciérnaga %s con energia %d%n", nombre, energia--);
			try {
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

		encendida = false;


	}


	public static void main(String[] args) {

		Luciernaga l1 = new Luciernaga("L1", 5);
		l1.enciende();

	}




}
