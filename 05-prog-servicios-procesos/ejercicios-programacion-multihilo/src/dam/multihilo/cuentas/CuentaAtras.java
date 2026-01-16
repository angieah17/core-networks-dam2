package dam.multihilo.cuentas;

public class CuentaAtras extends Thread {

	private int valorInicial;
	private String nombre;
	final static int milis = 300;


	public CuentaAtras(int valorInicial, String nombre) {
		this.valorInicial = valorInicial;
		this.nombre = nombre;
	}




	@Override
	public void run() {

		arranca();
	}




	public void arranca() {
		for (int i =valorInicial; i >= 0 ; i--) {
			System.out.printf("%s: %d%n", nombre, i);

			try {
				Thread.sleep(milis); //El .sleepp saca siempre una excepción que debemos gestionar
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}


	public static void main(String[] args) {

		CuentaAtras c1 = new CuentaAtras(10, "C-1");
		CuentaAtras c2 = new CuentaAtras(5, "C-2");

		System.out.println("PROGRAMA EMPEZANDO");
		c1.start();
		System.out.println("PROGRAMA EMPEZADO");
		c2.start();
		System.out.println("PROGRAMA TERMINADO");
		//Al extender de Thread entiende que el run va en un hilo distinto


	}





}
