package dam.multihilo.repaso4;

public class Deletreator implements Runnable {

	private String palabra;
	private static final long milis = 300;


	public Deletreator(String palabra) {
		this.palabra = palabra;
	}


	@Override
	public void run() {

		for (int i = 0; i < palabra.length(); i++) {
			StringBuilder sb = new StringBuilder(); //En cada iteración debo crear un StringBuilder
			for (int j = 0; j < palabra.length(); j++) {

				if (j == i) {
					sb.append(palabra.charAt(i));
				} else {
					sb.append('-');
				}

			}


			System.out.printf("Deletreando \"%s\": [%s]%n", palabra, sb.toString());

			try {

				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}


		}



	}

	public static void main(String[] args) {

		Deletreator d1 = new Deletreator("hola");
		new Thread(d1).start();

	}


}
