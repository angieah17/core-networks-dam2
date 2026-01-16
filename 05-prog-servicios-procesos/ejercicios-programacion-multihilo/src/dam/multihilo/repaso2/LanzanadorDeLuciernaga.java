package dam.multihilo.repaso2;

public class LanzanadorDeLuciernaga extends Thread {

	private Luciernaga luciernaga;


	public LanzanadorDeLuciernaga(Luciernaga luciernaga) {
		this.luciernaga = luciernaga;
	}



	@Override
	public void run() {
		luciernaga.enciende();
	}
}
