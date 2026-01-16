package datos.jdbc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnectionMejorado {
	
	public static void showSQLExceptionInfo(SQLException e) {
		System.err.printf("SQL Exception        : %s%n", e.getClass().getSimpleName());
		System.err.printf("SQL ERROR mensaje    : %s%n", e.getMessage());
		System.err.printf("SQL Estado           : %s%n", e.getSQLState());
		System.err.printf("SQL Código específico: %s%n", e.getErrorCode());
		
		//Se ajusta el formato de la info de la excepción que se quiere mostrar.
		
		/*Ejemplo:
		SQL Exception        : SQLException
		SQL ERROR mensaje    : Unknown database 'mundox'
		SQL Estado           : 42000
		SQL Código específico: 1049
				 * */
	}
	
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3307/mundox?allowPublicKeyRetrieval=true&useSSL=false"; //aquí lanza error al usar mundox, es mundo
		String user = "root", pass = "1234";
		System.out.println("Estableciendo conexión");
		
		//se usa try-with-resources para cierre de conexión automática
		try(Connection conn = DriverManager.getConnection(url,user,pass);){
			System.out.println("Conexión establecida con éxito");
		} catch (SQLException e) { //atrapa una excepción específica
			showSQLExceptionInfo(e);
		}
		System.out.println("bye");
		
		
		
	}
}
