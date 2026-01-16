package dam.multihilo.repaso4;

import java.util.ArrayList;
import java.util.List;

public class CountDown2 implements Runnable {

	private static long milis = 100;
	private int start;
	private String name;



	public CountDown2(int start, String name) {
		super();
		this.start = start;
		this.name = name;
	}




	@Override
	public void run() {

		for (int i = start; i >= 0; i--) {
			System.out.printf("%s: %d%n", name, i);

			try {
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}


	public static void main(String[] args) {

		CountDown2 c1 = new CountDown2(5, "C1");
		Thread thread = new Thread(c1);
		List<Thread.State> states = new ArrayList<>();

		//Estado NEW
		System.out.printf("%s - Estado: %s%n", thread.getName(), thread.getState());
		states.add(thread.getState());

		//Estado RUNNABLE

		thread.start();
		System.out.printf("%s - Estado: %s%n", thread.getName(), thread.getState());
		states.add(thread.getState());

		//Estado TIMED_WAITING

		while (thread.isAlive()) {
			System.out.printf("%s - Estado: %s%n", thread.getName(), thread.getState());
			states.add(thread.getState());

			try {
				Thread.sleep(milis/2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

		//Estado TERMINATED

		System.out.printf("%s - Estado: %s%n", thread.getName(), thread.getState());

		//Lista de los estados del Thread

		System.out.println("El thread ha pasado por los siguientes estados:");
		states.forEach(System.out::println);
		System.out.printf("En total:%d%n", states.size());


	}




}
