package datos.jdbc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestPrimeraConsulta {
		
		public static void showSQLExceptionInfo(SQLException e) {
			System.err.printf("SQL Exception        : %s%n", e.getClass().getSimpleName());
			System.err.printf("SQL ERROR mensaje    : %s%n", e.getMessage());
			System.err.printf("SQL Estado           : %s%n", e.getSQLState());
			System.err.printf("SQL Código específico: %s%n", e.getErrorCode());
		}
		
		public static void main(String[] args) {
			
			//Leer datos reales de una BD con JDBC
			
			String url = "jdbc:mysql://localhost:3307/mundo?allowPublicKeyRetrieval=true&useSSL=false";
			String user = "root", pass = "1234";
			String sql = "SELECT * FROM personas";
			System.out.println("Estableciendo conexión");
			try(Connection conn = DriverManager.getConnection(url,user,pass);
					Statement stm = conn.createStatement(); //Ejecutar consultas SQL
					ResultSet rs = stm.executeQuery(sql);){ //Contiene los resultados
				
				/*Envía el SQL a MySQL
				 * MySQL ejecuta la consulta
				 * Devuelve los resultados
				 * Java los guarda en rs
				 * */
				System.out.println("Conexión establecida con éxito");
				int counter =0;
				int index = 0;
				/*rs.next();
				 * Se mueve a la siguiente fila.
				 * Devuelve true si hay datos
				 * Es como un cursor que va bajando fila a fila.
				 * */
				while(rs.next()) {
					if(rs.getInt("nacimiento") % 10 == 0) { //se filtra el año de nacimiento si es multiplo de 10
						System.out.printf("%d[%s,%s,%s,%s]%n",index, rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
						/*Aquí lees columnas por posición (No inicia con 0):

							1 → primera columna
							
							2 → segunda
							
							3 → tercera
							
							También se pueden usar nombres:
							rs.getInt("nacimiento")
						 * */
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
