package dam.multihilo.recordatorio;

public class Test {

	public static void main(String[] args) {

		/*El hilo principal se libera inmediatamente se da la indicación de start
		 * y así se va ejecutando internamente como bien encuentra uso de CPU*/

		CuentaAtras ca1 = new CuentaAtras("C-1", 10);
		CuentaAtras ca2 = new CuentaAtras("C-2", 10);

		ca1.start();
		ca2.start();
		System.out.println("AQUÍ ALGO EN HILO PRINCIPAL");
		CuentaAtrasRunnable car1 = new CuentaAtrasRunnable("C-1", 10);
		CuentaAtrasRunnable car2 = new CuentaAtrasRunnable("C-2", 10);

		//Para usar el run de Runnable se crea un new Thread y se le pasa el car1
		new Thread(car1).start();
		new Thread(car2).start();
		System.out.println("AQUÍ OTRA COSA EN HILO PRINCIPAL");
		//Thread tiene un constructor que permite pasar como parámetro un objeto sobre el cual el método run va ser invocado o el run en sí mismo
		new Thread(
					() -> {
						for (int i = 20; i >= 0; i--) {
							System.out.printf("%s: %d %n","Lambdos", i);
							try {
								Thread.sleep(300);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}

				).start();


		System.out.println("AQUÍ FINALIZA EL HILO PRINCIPAL");

	}




}
