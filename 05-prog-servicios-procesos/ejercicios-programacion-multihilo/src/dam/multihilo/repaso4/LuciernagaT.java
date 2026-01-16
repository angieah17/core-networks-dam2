package dam.multihilo.repaso4;

public class LuciernagaT  extends Thread{
	private String nombre;
	private boolean encendida;
	private int energia;
	private static final long milis = 300;



	public LuciernagaT(String nombre, int energia) {
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

	@Override
	public void run() {
		enciende();
	}



	public static void main(String[] args) {

		LuciernagaT l1 = new LuciernagaT("L1", 5);
		LuciernagaT l2 = new LuciernagaT("L2", 10);
		l1.start();
		l2.start();


	}




}
