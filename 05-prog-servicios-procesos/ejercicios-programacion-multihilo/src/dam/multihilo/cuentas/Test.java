package dam.multihilo.cuentas;

public class Test {

	public static void main(String[] args) {

		LectorFrasesA fraseSinHilo = new LectorFrasesA("Hola soy una frase");
		LectorFrasesB fraseConHilo = new LectorFrasesB("HOLA SOY ANGIE");
		DeletreatorA palabraSinHilo = new DeletreatorA("PALABRA");
		DeletreatorB palabraConHilo = new DeletreatorB("HOLA");

		fraseSinHilo.leer();
		palabraSinHilo.deletrea();
		fraseConHilo.start();
		palabraConHilo.start();

	}

}
