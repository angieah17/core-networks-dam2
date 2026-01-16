package dam.multihilo.lanzador;

public class Test {
	 public static void main(String[] args) {

		 LanzadorVaciadoBotella l1 = new LanzadorVaciadoBotella(new Botella("Botella1", 5, '*'));
		 new LanzadorVaciadoBotella(new Botella("Botella1", 5, '-')).start();
		 l1.start(); //Se puede hacer start() porque Lanzador... extiende de Thread.


	}
}
