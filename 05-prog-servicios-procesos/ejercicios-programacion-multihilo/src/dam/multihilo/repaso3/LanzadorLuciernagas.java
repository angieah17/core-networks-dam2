package dam.multihilo.repaso3;

public class LanzadorLuciernagas extends Thread {

	private Luciernaga luciernaga;

	public LanzadorLuciernagas(Luciernaga luciernaga) {
		this.luciernaga = luciernaga;
	}



	@Override
	public void run() {
		luciernaga.enciende();
	}



}
