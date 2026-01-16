package dam.multihilo.repaso3;

import java.util.ArrayList;
import java.util.List;

public class CountDown2 implements Runnable {

	private static final long milis = 300;
	private int start;
	private String name;


	public CountDown2(int start, String name) {
		this.start = start;
		this.name = name;
	}


	@Override
	public void run() {

		for (int i = start; i > 0; i--) {
			System.out.printf("%s: %d%n", name, i);

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

		System.out.printf("%s - Estado: %s%n", thread.getName(), thread.getState());


		//Estado RUNNABLE
		thread.start();
		states.add(thread.getState());
		System.out.printf("%s - Estado: %s%n", thread.getName(), thread.getState());

		//Estado TIMED_WAITING

		while (thread.isAlive()) {
			states.add(thread.getState());
			System.out.printf("%s - Estado: %s%n", thread.getName(), thread.getState());

			try {
				Thread.sleep(milis/3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		//Estado TERMINATED

		System.out.printf("%s - Estado: %s%n", thread.getName(), thread.getState());
		states.add(thread.getState());

		System.out.println("Los estado por los que ha pasado:");

		states.forEach(System.out::println);

		System.out.printf("Ha pasado por %d estados", states.size());






	}


}
