package dam.multihilo.repaso;

public class Test {

	public static void main(String[] args) {

		//Método 1 Thread
		new BotellaThread("bt2",5, '-').start();
		new BotellaThread("bt1",10, '*').start();

		//Método 2 Runnable
		new Thread(new BotellaRunnable("br1", 6, 'x')).start();

		//Método 3 objeto como propiedad
		new LanzadorVaciadoBotella(new Botella("b1", 8, '_')).start();



	}

}
