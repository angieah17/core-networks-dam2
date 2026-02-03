package socket.examen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class GestorServerSocket implements Runnable {
	
	//1. Atributos
	
	private Socket socket;
	
	//2. Constructor para que genere instancias por cada clienteSocket
	public GestorServerSocket(Socket socket) {
		this.socket = socket;
	}
	
	
	@Override
	public void run() {
		
		try (PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
			
			String mensajeRecibido;
			
			while ((mensajeRecibido = entrada.readLine()) != null) {
				
				//1. Mostrar el mensaje recibido por parte del cliente y en qué hilo se encuentra
				System.out.printf("Hilo: [%s] Petición recibida: %s%n", Thread.currentThread().getName(), mensajeRecibido);
				
				//2. Procesar el mensaje del cliente
				
				String respuesta = procesarMensaje(mensajeRecibido);

				//3. Mostrar la respuesta enviada al cliente
				
				System.out.printf("Hilo: [%s] Respuesta enviada: %s%n", Thread.currentThread().getName(), respuesta);
				
				//4.Enviar la respuesta al cliente
				salida.println(respuesta);
				//5. Si es mensaje #Fin# cerrar la conexión.
				if (mensajeRecibido.equals("#Fin#")) {
					System.out.println("✗ Cliente desconectado: " + socket.getInetAddress());
					break;
				}
			}
			
			
		} catch (IOException e) {
			System.out.println("Error manejando el cliente: " + e.getMessage());
		}
		
		
	}
	
	
	//Métodos
	
	
	private String procesarMensaje(String mensaje) {
		
		//Caso 1
        if (mensaje.equals("#Fin#")) {
            return "#Finalizado#";
        }
        
        String[] partes = mensaje.split("#"); 
        
        //Valida el formato básico para poder acceder al comando sin ArrayOut...
        if (partes.length < 3) {
            return "#Error#"; //Debe tener por lo menos 3 partes considerando los # inicial y final
        }
        
        String comando = partes[1];
        
        // VALIDACIÓN 1: Verificar que tenga exactamente 4 parámetros
        if (partes.length != 4) {
            return "#Error#";
        }
        
        // VALIDACIÓN 2: Verificar que sean números ANTES de parsear
        if (!partes[2].matches("-?\\d+") || !partes[3].matches("-?\\d+")) {
            return "#Error#";
        }
        		        
        // Ahora sí, parsear (ya sabemos que son números válidos)
        int inicio = Integer.parseInt(partes[2]);
        int fin = Integer.parseInt(partes[3]);
        
        // VALIDACIÓN 3: Verificar rango lógico
        if (inicio > fin) {
            return "#Error#";
        }
        
        
        if (comando.equals("Listado números")) { //Caso 2
        	
            StringBuilder resultado = new StringBuilder();
            for (int i = inicio; i <= fin; i++) {
                resultado.append(i);
                if (i < fin) {
                    resultado.append("|");
                }
            }
            return resultado.toString();
            
        } else if (comando.equals("Numero aleatorio")) { //Caso 3
        	
        	Random random = new Random();
	        int numeroAleatorio = random.nextInt(inicio, fin + 1);
	        return String.valueOf(numeroAleatorio);
	        
        } else {
            return "#Error#";
        }
    }
	
	
	

	
}
