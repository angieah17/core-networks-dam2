package dam.multihilo.repaso2;

import java.util.ArrayList;
import java.util.List;

public class CountDown2 implements Runnable {
	private int start;
	private String name;
	private static final long milis = 300;



	public CountDown2(int start, String name) {
		this.start = start;
		this.name = name;
	}



	@Override
	public void run() {

		for (int i = start; i >= 0; i--) {

			System.out.printf("%s - Valor Actual: %d%n", name, i);
			try {
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {

		CountDown2 c1 = new CountDown2(10, "C1");
		List<Thread.State> states = new ArrayList<>();



		//Estado NEW
		Thread thread = new Thread(c1);
		states.add(thread.getState());
		System.out.printf("%s - Estado %s%n", thread.getName(), thread.getState());

		//Estado RUNNABLE

		thread.start();
		states.add(thread.getState());
		System.out.printf("%s - Estado %s%n", thread.getName(), thread.getState());

		//Estado TIMED_WAITING y WAITING

		while (thread.isAlive()) {
			states.add(thread.getState());
			System.out.printf("%s - Estado %s%n", thread.getName(), thread.getState());

			try {
				Thread.sleep(milis / 2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		//Estado TERMINATED

		System.out.printf("%s - Estado %s%n", thread.getName(), thread.getState());
		thread.getState();

		System.out.println("Estados por los que pasó el hilo");
		states.forEach(System.out::println);
		System.out.printf("Cantidad de estados: %d%n", states.size());


	}





}
