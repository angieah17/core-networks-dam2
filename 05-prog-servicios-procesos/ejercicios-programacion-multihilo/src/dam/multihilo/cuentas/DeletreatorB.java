package dam.multihilo.cuentas;

public class DeletreatorB extends Thread{

	private String palabra;
	final static int mili = 300;


	public DeletreatorB(String palabra) {
		this.palabra = palabra;
	}

	@Override
	public void run() {
		deletrea();
	}


	public void deletrea() {



		for (int i = 0; i < palabra.length(); i++) {

			System.out.println(palabra.charAt(i));

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
