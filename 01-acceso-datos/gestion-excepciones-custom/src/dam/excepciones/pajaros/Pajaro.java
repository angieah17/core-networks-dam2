package dam.excepciones.pajaros;

public class Pajaro {

	private static int nextID = 1;
	private int id;


	public Pajaro() {
		id = nextID++;
	}


	@Override
	public String toString() {
		return String.format("P-%d", id);
	}

	public static void main(String[] args) {

		System.out.println(new Pajaro());
		System.out.println(new Pajaro());
	}



}
