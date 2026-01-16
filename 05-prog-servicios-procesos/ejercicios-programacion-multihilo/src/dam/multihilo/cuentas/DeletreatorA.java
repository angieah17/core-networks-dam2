package dam.multihilo.cuentas;

public class DeletreatorA {

	private String palabra;
	final static int mili = 300;


	public DeletreatorA(String palabra) {
		this.palabra = palabra;
	}


	public void deletrea() {


		for (int i = 0; i < palabra.length(); i++) {

			System.out.print(palabra.charAt(i));

			try {
				Thread.sleep(mili);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		System.out.println();
	}




}
