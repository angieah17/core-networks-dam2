package dam.multihilo.repaso2;

public class CountDown implements Runnable {
	private int start;
	private String name;
	private static final long milis = 300;



	public CountDown(int start, String name) {
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

		CountDown c1 = new CountDown(10, "C1");

		Thread thread = new Thread(c1);

		//Estado NEW

		System.out.printf("%s - Estado %s%n", thread.getName(), thread.getState());

		//Estado RUNNABLE

		thread.start();
		System.out.printf("%s - Estado %s%n", thread.getName(), thread.getState());

		//Estado TIMED_WAITING y WAITING

		while (thread.isAlive()) {

			System.out.printf("%s - Estado %s%n", thread.getName(), thread.getState());

			try {
				Thread.sleep(milis / 3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		//Estado TERMINATED

		System.out.printf("%s - Estado %s%n", thread.getName(), thread.getState());


	}





}
