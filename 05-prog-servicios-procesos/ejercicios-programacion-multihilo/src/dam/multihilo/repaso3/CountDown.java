package dam.multihilo.repaso3;

public class CountDown implements Runnable {

	private static final long milis = 300;
	private int start;
	private String name;


	public CountDown(int start, String name) {
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

		CountDown c1 = new CountDown(10, "C1");

		//Estado NEW
		Thread thread = new Thread(c1);

		System.out.printf("%s - Estado: %s%n", thread.getName(), thread.getState());


		//Estado RUNNABLE
		thread.start();
		System.out.printf("%s - Estado: %s%n", thread.getName(), thread.getState());

		//Estado TIMED_WAITING

		while (thread.isAlive()) {

			System.out.printf("%s - Estado: %s%n", thread.getName(), thread.getState());

			try {
				Thread.sleep(milis/3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		//Estado TERMINATED

		System.out.printf("%s - Estado: %s%n", thread.getName(), thread.getState());










	}


}
