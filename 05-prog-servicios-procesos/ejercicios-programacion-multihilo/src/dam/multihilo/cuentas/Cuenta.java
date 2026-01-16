package dam.multihilo.cuentas;

public class Cuenta extends Thread {

	private int valorInicial;
	private String nombre;
	final static int milis = 300; //Tiempo que quiero que se pause


	public Cuenta(int valorInicial, String nombre) {
		this.valorInicial = valorInicial;
		this.nombre = nombre;
	}


	@Override
	public void run() {

		for (int i = valorInicial; i <= 10; i++) {

			System.out.printf("%s: %d%n", nombre, i );

			try {
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}


		}

	//Aquí se pone la lógica que quiero que haga cada cuenta

	}

	public static void main(String[] args) {

		Cuenta c1 = new Cuenta(1, "C-1");
		Cuenta c2 = new Cuenta(5, "C-2");
		Cuenta c3 = new Cuenta(2, "C-3");

		c1.start(); //Este método lanza el hilo
		c2.run(); //El run ejecuta el método tal cual, hasta que no termina esta línea no ejecuta la tercera línea que sería el c3.
		c3.start();


	}







}
