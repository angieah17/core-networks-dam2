package socket.examen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class ClienteSocket2 {
    private static final String HOST = "localhost";
    private static final int PUERTO = 8081;
    
    public static void main(String[] args) {
        try (
            Socket socket = new Socket(HOST, PUERTO);
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("=== CLIENTE SOCKET CONECTADO ===");
            System.out.println("Conectado al servidor en " + HOST + ":" + PUERTO);
            System.out.println("\nEjemplos de mensa  jes:");
            System.out.println("  - #Listado números#2#11#");
            System.out.println("  - #Numero aleatorio#3#20#");
            System.out.println("  - #Fin#");
            System.out.println("  - Cualquier otro mensaje\n");
            
            String mensajeEnviado;
            boolean continuar = true;
            
            while (continuar) {
                System.out.print("Introduce mensaje: ");
                mensajeEnviado = scanner.nextLine();
                
                // Enviar mensaje al servidor
                salida.println(mensajeEnviado);
                System.out.println("Mensaje enviado: " + mensajeEnviado);
                
                // Recibir respuesta del servidor
                String respuesta = entrada.readLine();
                System.out.println("Mensaje recibido: " + respuesta);
                
                // Procesar la respuesta según el tipo
                procesarRespuesta(mensajeEnviado, respuesta);
                
                // Si recibimos #Finalizado#, terminamos
                if (respuesta.equals("#Finalizado#")) {
                    continuar = false;
                }
                
                System.out.println(); // Línea en blanco para separar
            }
            
        } catch (UnknownHostException e) {
            System.err.println("No se pudo encontrar el servidor: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }
    }
    
    // Método para procesar y mostrar la respuesta de forma adecuada
    private static void procesarRespuesta(String mensajeEnviado, String respuesta) {
        if (respuesta.equals("#Error#")) {
            System.out.println("→ Mensaje no adecuadamente formateado para su tratamiento.");
        } else if (respuesta.equals("#Finalizado#")) {
            System.out.println("→ Fin de la conexión");
        } else {
            // Es una respuesta válida (listado de números o número aleatorio)
            System.out.println("→ Resultado: " + respuesta);
        }
    }
}