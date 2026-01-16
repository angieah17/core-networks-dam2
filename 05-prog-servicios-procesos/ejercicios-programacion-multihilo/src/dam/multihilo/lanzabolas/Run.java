package dam.multihilo.lanzabolas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Run {


	public static void main(String[] args) {

		Lanzabolas l1 = new Lanzabolas("Lanzador 1");
		Lanzabolas l2 = new Lanzabolas("Lanzador 2");
		Lanzabolas l3 = new Lanzabolas("Lanzador 3");

		/*ExecutorService executorDe3 = Executors.newFixedThreadPool(3);

		executorDe3.execute(l1);
		executorDe3.execute(l2);
		executorDe3.execute(l3);
		executorDe3.shutdown();*/

		ExecutorService executorDe1 = Executors.newSingleThreadExecutor();
		/*Se ejecuta de manera secuencia en 1 nuevo hilo, no es el mismo hilo principal.
		 *
		 * */

		/*for (int i = 0; i < 4; i++) {
			executorDe1.execute(l1);
			executorDe1.execute(l2);
			//executorDe1.execute(l3);
			executorDe1.shutdown();
		}*/

		ExecutorService executorDe9 = Executors.newFixedThreadPool(9);

		for (int i = 0; i < 4; i++) {
			executorDe9.execute(l1);
			executorDe9.execute(l2);
			executorDe9.shutdown();
		}




	}





}
