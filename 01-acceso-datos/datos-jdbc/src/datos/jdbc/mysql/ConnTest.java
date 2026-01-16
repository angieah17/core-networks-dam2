package datos.jdbc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnTest {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3307/mundo?allowPublicKeyRetrieval=true&useSSL=false";
		/*
		 * jdbc:mysql: //	Usas JDBC con MySQL
		 * localhost	La base de datos está en tu propio ordenador 3307. Puerto asignado a MySQL
		 * mundo	Nombre de la base de datos
		 * allowPublicKeyRetrieval=true	Permite recuperar claves públicas (necesario con MySQL 8+)
		 * useSSL=false	Desactiva la conexión segura (evita avisos)
		 * */
		String user = "root", pass = "1234";
		try {
			System.out.println("Estableciendo conexión");
			Connection conn = DriverManager.getConnection(url,user,pass);
			/*El DriveManager, busca el driver JDBC de MySQL usando la url, use ry pass y devuelve un objeto Connection
			 * */
			System.out.println("Conexión establecida con éxito");
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName()); 
			//Si atrapa una Excepción genérica, pero se imprime por ej: SQLException, CommunicationsException
		}
		System.out.println("bye");
		
		/*Resumen del programa:

			-Carga las clases JDBC
			
			-Define la ruta de la base de datos
			
			-Usa usuario y contraseña
			
			-Intenta conectarse
			
			-Muestra si tuvo éxito o no
		 * */
	}
}
