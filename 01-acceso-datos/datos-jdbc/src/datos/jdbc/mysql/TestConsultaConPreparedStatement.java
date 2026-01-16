package datos.jdbc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConsultaConPreparedStatement {
	public static void showSQLExceptionInfo(SQLException e) {
		System.err.printf("SQL Exception        : %s%n", e.getClass().getSimpleName());
		System.err.printf("SQL ERROR mensaje    : %s%n", e.getMessage());
		System.err.printf("SQL Estado           : %s%n", e.getSQLState());
		System.err.printf("SQL Código específico: %s%n", e.getErrorCode());
	}
	
	//PreparedStatement (consultas parametrizadas y seguras)
	
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3307/mundo?allowPublicKeyRetrieval=true&useSSL=false";
		String user = "root", pass = "1234";
		String sql = "SELECT * FROM personas where nombre = ?";
		
		/*	Usar parámetros (?)

			Evitar inyección SQL
			
			Reutilizar consultas
			
			Mejorar rendimiento
		 * */
		
		System.out.println("Estableciendo conexión");
		try(Connection conn = DriverManager.getConnection(url,user,pass);
				PreparedStatement ps = conn.prepareStatement(sql);){ 
			/*Envía la consulta a MySQL

			MySQL la precompila

			Queda lista para recibir valores
			 * */
			ps.setString(1, "VALERIA"); //Primer ? de la consulta se usará el valor "VALERIA"
			ResultSet rs = ps.executeQuery(); //Ejecuta la consulta y guarda aquí los valores
			System.out.println("Conexión establecida con éxito");
			int counter =0;
			int index = 0;
			while(rs.next()) {
				if(rs.getInt("nacimiento") % 10 == 0) {
					System.out.printf("%d[%s,%s,%s,%s]%n",index, rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
					counter++;
				}
				index++;
			}
			System.out.printf("Elementos mostrados: %d%n", counter);
			
		} catch (SQLException e) {
			showSQLExceptionInfo(e);
		}
		System.out.println("bye");
	}
}
