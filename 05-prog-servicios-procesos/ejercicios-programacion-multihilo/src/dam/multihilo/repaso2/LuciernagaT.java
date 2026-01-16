package dam.multihilo.repaso2;

public class LuciernagaT extends Thread{
	private String nombre;
	private boolean encendido;
	private int energia;
	private static final int milis = 300;


	public LuciernagaT(String nombre, int energia) {
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


	@Override
	public void run() {
		enciende();
	}


	public static void main(String[] args) {

		LuciernagaT l1 = new LuciernagaT("L1", 10);
		LuciernagaT l2 = new LuciernagaT("L2", 5);
		LuciernagaT l3 = new LuciernagaT("L3", 3);

		l1.start();
		l2.start();
		l3.start();


	}




}
