package dam.multihilo.emisores;

public class Test {

	public static void main(String[] args) {


		LanzadorEmisiones l1 = new LanzadorEmisiones("L1", new Emisor('*', 5));
		LanzadorEmisiones l2 = new LanzadorEmisiones("L2", new Emisor('+', 3));

		System.out.println(l1);
		l1.start();
		//l2.start();

		for (int i = 0; i < 25; i++) {
			System.out.println(l1);
		}


		//System.out.println(l2);
		try {
			Thread.sleep(6_000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(l1);
	}

}
