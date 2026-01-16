package dam.excepciones.pajaros;


import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class Jaula {
	
	private static int nextID = 1;
	private int id;
	private boolean puertaAbierta;
	private int capacidad;
	private Set<Pajaro> listaPajaros; 
	
	Random random = new Random();
	
	
	public Jaula() {
		id = nextID++;
		capacidad = random.nextInt(3, 7);
		listaPajaros = new LinkedHashSet<Pajaro>(); //almacenamiento de pajaros, evita duplicados y mantiene el orden de inserción
	}

	//Getter y setter para acceder a la propiedad puertaAbierta desde operacionesJaula 
	
	public boolean isPuertaAbierta() {
		return puertaAbierta;
	}

	public void setPuertaAbierta(boolean puertaAbierta) {
		this.puertaAbierta = puertaAbierta;
	}
	
	//METODOS
	
	public void meterPajaro(Pajaro pajaro) throws EspacioInsuficienteException{
		if(capacidad == listaPajaros.size()) throw new EspacioInsuficienteException("La Jaula está llena, no se puede añadir este pájaro");
		if(pajaro != null) listaPajaros.add(pajaro);
	}
	
	public void sacarPajaro(Pajaro pajaro) {
		if(pajaro != null && listaPajaros.contains(pajaro)) listaPajaros.remove(pajaro);
	}
	
	
	
	
	public int contarPajaros() {
		 return listaPajaros.size(); 
	}
	
	
	
	@Override
	public String toString() {
		return String.format("J-%d %s", id, listaPajaros);
	}
	
	
	

	
	
	
	
	
}
