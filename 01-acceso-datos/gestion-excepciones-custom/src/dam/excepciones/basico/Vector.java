package dam.excepciones.basico;


//La clase excepción también se podía crear aquí



public class Vector {

	private int x;
	private int y;


	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Vector (int n) {
		x = y = n;
	}


	public Vector suma (Vector v) throws VectorOutOfBoundsException {

		long sumaX = (long) x + v.x;
		long sumaY= (long) y + v.y;

		if(sumaX > Integer.MAX_VALUE
				|| sumaY > Integer.MAX_VALUE
				|| sumaX < Integer.MIN_VALUE
				||sumaY < Integer.MIN_VALUE) {
			throw new VectorOutOfBoundsException("Suma fuera del rango");
		}


		return new Vector(x + v.x, y + v.y);

	}



	@Override
	public String toString() {
		return String.format("Vector x=%d y y=%d", x, y);
	}

	public static void main(String[] args) {

		Vector vMax = new Vector(Integer.MAX_VALUE);
		Vector vMin = new Vector(Integer.MIN_VALUE);
		Vector vPositivo = new Vector(1);
		Vector vNegativo = new Vector(-1);

		try {
			System.out.println(vPositivo.suma(vNegativo));
		} catch (VectorOutOfBoundsException e) {
			e.printStackTrace();
		}

		try {
			System.out.println(vMax.suma(vMax));
		} catch (VectorOutOfBoundsException e) {
			System.err.println("La suma está fuera de rango");
		}


		System.out.println("HOLAA");

	}

}
