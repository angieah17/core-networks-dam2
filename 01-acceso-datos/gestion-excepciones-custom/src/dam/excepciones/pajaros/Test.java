package dam.excepciones.pajaros;

public class Test {

public static void main(String[] args) {
		
		Jaula j1 = new Jaula();
		Pajaro p1 = new Pajaro();
		Pajaro p2 = new Pajaro();
		
		
		try {
			j1.meterPajaro(p1);
			j1.meterPajaro(p2);
			j1.meterPajaro(p1);

		} catch (EspacioInsuficienteException e) {
			e.printStackTrace();
		}
		
		System.out.println(j1);
		
		System.out.println(j1.contarPajaros());
		j1.sacarPajaro(p2);
		System.out.println(j1);
		j1.sacarPajaro(p2);
		System.out.println(j1);
		System.out.println(j1.contarPajaros());
		
		try (OperacionesJaula oj1 = new OperacionesJaula(j1);) {
			System.out.println("Hola");
		} catch (JaulaException e) {
			
			e.printStackTrace();
		}
		
		
		System.out.println("Revisando");
		
	}
	
	
	
}
