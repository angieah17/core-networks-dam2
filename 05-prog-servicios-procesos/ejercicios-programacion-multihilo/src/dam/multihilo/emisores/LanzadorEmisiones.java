package dam.multihilo.emisores;

public class LanzadorEmisiones extends Thread {

	private String nombre;
	private Emisor emisor;


	public LanzadorEmisiones(String nombre, Emisor emisor) {
		this.nombre = nombre;
		this.emisor = emisor;
	}


	public String getNombre() {
		return nombre;
	}


	public Emisor getEmisor() {
		return emisor;
	}

	@Override
	public void run() {
		emisor.emite();
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("Lanzador de emisiones:\n");
		sb.append("======================\n");
		sb.append("-Nombre: " + nombre + "\n");
		sb.append("-Emisor: " + emisor.getNombre() + "\n");
		sb.append("-Hilo:\n");
		sb.append("    - name: " + getName()+ "\n"); //El Thread tiene un método para obtener el nombre que es getName()
		sb.append("    - id: " + "AQUI VA ALGO" + "\n");
		sb.append("    - alive?: " + "AQUI VA ALGO" + "\n");
		sb.append("    - state: " + "AQUI VA ALGO" + "\n");
		sb.append("    - prioridad: " + "AQUI VA ALGO" + "\n");
		sb.append("    - interrumpido: " + "AQUI VA ALGO" + "\n");
		sb.append("    - threadGroup: " + "AQUI VA ALGO" + "\n");
		sb.append("    - state: " + getState() + "\n");

		return sb.toString();
	}



}
