package socket.examen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class ManejadorCliente implements Runnable { //Runnable para poder implementar en el multihilo

	private Socket socket;

	public ManejadorCliente(Socket socket) {
		this.socket = socket;
	}

	 @Override
	    public void run() {
	        try (
	            BufferedReader entrada = new BufferedReader(
	                new InputStreamReader(socket.getInputStream()));
	            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)
	        ) {
	            String mensajeRecibido;
	            
	            // Bucle para recibir múltiples mensajes del mismo cliente
	            while ((mensajeRecibido = entrada.readLine()) != null) {
	                System.out.println("\n[" + Thread.currentThread().getName() + "] Petición recibida: " + mensajeRecibido);
	                
	                String respuesta = procesarMensaje(mensajeRecibido);
	                
	                System.out.println("[" + Thread.currentThread().getName() + "] Respuesta enviada: " + respuesta);
	                
	                salida.println(respuesta);
	                
	                // Si es mensaje de fin, cerrar la conexión
	                if (mensajeRecibido.equals("#Fin#")) {
	                    System.out.println("✗ Cliente desconectado: " + socket.getInetAddress());
	                    break;
	                }
	            }
	        } catch (IOException e) {
	            System.err.println("Error manejando cliente: " + e.getMessage());
	        } finally {
	            try {
	                socket.close();
	            } catch (IOException e) {
	                System.err.println("Error cerrando socket: " + e.getMessage());
	            }
	        }
	    }
	    
	    // Método que procesa los mensajes según el protocolo
	    private String procesarMensaje(String mensaje) {
	        // Caso 1: Mensaje de fin
	        if (mensaje.equals("#Fin#")) {
	            return "#Finalizado#";
	        }
	        
	        // Dividir el mensaje por '#'
	        String[] partes = mensaje.split("#");
	        
	        // Validar formato básico (debe tener al menos 3 partes considerando los # inicial y final)
	        if (partes.length < 3) {
	            return "#Error#";
	        }
	        
	        String comando = partes[1]; // El comando está en la posición 1
	        
	        try {
	            // Caso 2: Listado números
	            if (comando.equals("Listado números")) {
	                if (partes.length != 4) {
	                    return "#Error#";
	                }
	                int inicio = Integer.parseInt(partes[2]);
	                int fin = Integer.parseInt(partes[3]);
	                
	                if (inicio > fin) {
	                    return "#Error#";
	                }
	                
	                StringBuilder resultado = new StringBuilder();
	                for (int i = inicio; i <= fin; i++) {
	                    resultado.append(i);
	                    if (i < fin) {
	                        resultado.append("|");
	                    }
	                }
	                return resultado.toString();
	            }
	            
	            // Caso 3: Numero aleatorio
	            else if (comando.equals("Numero aleatorio")) {
	                if (partes.length != 4) {
	                    return "#Error#";
	                }
	                int min = Integer.parseInt(partes[2]);
	                int max = Integer.parseInt(partes[3]);
	                
	                if (min > max) {
	                    return "#Error#";
	                }
	                
	                Random random = new Random();
	                int numeroAleatorio = random.nextInt(max - min + 1) + min;
	                return String.valueOf(numeroAleatorio);
	            }
	            
	            // Comando no reconocido
	            else {
	                return "#Error#";
	            }
	            
	        } catch (NumberFormatException e) {
	            // Si los números no son válidos
	            return "#Error#";
	        } catch (Exception e) {
	            return "#Error#";
	        }
	    }

}

	

