package dam.excepciones.pajaros;

import java.util.Objects;

public class Pajaro {
	
	private static int nextID = 1;
	private int id;
	
	
	public Pajaro() {
		id = nextID++;
	}

	
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pajaro other = (Pajaro) obj;
		return id == other.id;
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
