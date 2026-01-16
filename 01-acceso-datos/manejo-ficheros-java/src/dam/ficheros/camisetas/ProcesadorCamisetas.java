package dam.ficheros.camisetas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProcesadorCamisetas {

	//1. Filtrar líneas con errores

	public static void filtraLineasConErrores(String archivoEntrada) {

		//Uso el try-with resources para que los br y bw se cierren automáticamente

		try(BufferedReader brCamisetas = new BufferedReader(new FileReader(archivoEntrada));
				BufferedWriter bwSinErrores = new BufferedWriter(new FileWriter("src/ficheros.camisetas/data/camisetas_sin_errores_de_linea.txt"));
		        BufferedWriter bwLog = new BufferedWriter(new FileWriter("src/ficheros.camisetas/logs/camisetas_con_errores_de_linea.log"));  ) {

			/*Recordar que new FileWriter crea el archivo si no existe, lo abre si ya existe y sobreescribe por defecto.
			 * */

			 	String linea;
		        int totalLineas = 0;
		        int lineasEliminadas = 0;
		        List<String> lineasConError = new ArrayList<>();

		        //1.1 Se escribe en el archivo camisetas_sin_errores...
		        while ((linea = brCamisetas.readLine()) != null) {
		            totalLineas++;

		            // Validar formato de línea usando la clase Camiseta
		            if (Camiseta.lineaTieneFormatoCorrecto(linea)) {
		                bwSinErrores.write(linea);
		                bwSinErrores.newLine(); //agregar un salto de línea
		            } else {
		                lineasEliminadas++;
		                lineasConError.add(linea);
		            }
		        }

		        //1.2 Se escribe en el archivo log de errores

		        bwLog.write(String.format("Total líneas analizadas: %s", totalLineas));
		        bwLog.newLine();
		        bwLog.write(String.format("Total líneas eliminadas: %s", lineasEliminadas));
		        bwLog.newLine();
		        bwLog.newLine();
		        bwLog.write("Las líneas eliminadas son:");
		        bwLog.newLine();

		        for (String lineaConError : lineasConError) {
					bwLog.write(lineaConError);
					bwLog.newLine();
				}

		        System.out.println("Paso 1 completado:");
		        System.out.println("- Total líneas: " + totalLineas);
		        System.out.println("- Líneas eliminadas: " + lineasEliminadas);
		        System.out.println("- Líneas correctas: " + (totalLineas - lineasEliminadas));

		        System.out.println("Filtrado realizado correctamente");

			} catch (IOException e) {
				System.out.println("Problema filtrando líneas con errores");
				e.printStackTrace();
			}



	}

	//2. Generar reporte de frecuencias

	public static void generarFrecuencias (String archivoEntrada, String archivoSalida) {


		try (BufferedReader brCamisetas = new BufferedReader(new FileReader(archivoEntrada));
				BufferedWriter bwArchivoSalida = new BufferedWriter(new FileWriter(archivoSalida));) {

			//2.1 Se lee el archivo de camisetas_sin_errores

			//Uso del TreeMap para almacenar las frecuencias ordenadas numérica o alfabéticamente

			Map<String, Integer> frecCantidad = new TreeMap<>();
			Map<String, Integer> frecColor = new TreeMap<>();
			Map<String, Integer> frecMarca = new TreeMap<>();
			Map<String, Integer> frecModelo = new TreeMap<>();
			Map<String, Integer> frecTalla = new TreeMap<>();

			String linea;
			while ((linea = brCamisetas.readLine()) != null) {

				Camiseta camiseta = Camiseta.fromCSV(linea);

				if(camiseta != null) {
					String cantidad = String.valueOf(camiseta.getCantidad());
	                String color = camiseta.getColor();
	                String marca = camiseta.getMarca();
	                String modelo = camiseta.getModelo();
	                String talla = camiseta.getTalla();

	                frecCantidad.put(cantidad, frecCantidad.getOrDefault(cantidad,  0) + 1);
	                frecColor.put(color, frecColor.getOrDefault(color,  0) + 1);
	                frecMarca.put(marca, frecMarca.getOrDefault(marca, 0) + 1);
	                frecModelo.put(modelo, frecModelo.getOrDefault(modelo, 0) + 1);
	                frecTalla.put(talla, frecTalla.getOrDefault(talla, 0) + 1);
				}

			}

			//2.2 Se escribe en el reporte de frecuencias

			bwArchivoSalida.write("=== REPORTE DE FRECUENCIAS ===");
			bwArchivoSalida.newLine();
			bwArchivoSalida.newLine();

			// Cantidad
	        bwArchivoSalida.write("CANTIDAD:");
			bwArchivoSalida.newLine();


			for (Map.Entry<String, Integer> entrada : frecCantidad.entrySet()) {
				bwArchivoSalida.write(String.format("%s: %d", entrada.getKey(), entrada.getValue()));
				bwArchivoSalida.newLine();
			}

			//Color
			bwArchivoSalida.write("COLOR:");
			bwArchivoSalida.newLine();

			for (Map.Entry<String, Integer> entrada : frecColor.entrySet()) {
				bwArchivoSalida.write(String.format("%s: %d", entrada.getKey(), entrada.getValue()));
				bwArchivoSalida.newLine();
			}

			//Marca
			bwArchivoSalida.write("MARCA:");
			bwArchivoSalida.newLine();

			for (Map.Entry<String, Integer> entrada : frecMarca.entrySet()) {
				bwArchivoSalida.write(String.format("%s: %d", entrada.getKey(), entrada.getValue()));
				bwArchivoSalida.newLine();
			}

			//Modelo
			bwArchivoSalida.write("MODELO:");
			bwArchivoSalida.newLine();

			for (Map.Entry<String, Integer> entrada : frecModelo.entrySet()) {
				bwArchivoSalida.write(String.format("%s: %d", entrada.getKey(), entrada.getValue()));
				bwArchivoSalida.newLine();
			}

			//Talla
			bwArchivoSalida.write("TALLA:");
			bwArchivoSalida.newLine();

			for (Map.Entry<String, Integer> entrada : frecTalla.entrySet()) {
				bwArchivoSalida.write(String.format("%s: %d", entrada.getKey(), entrada.getValue()));
				bwArchivoSalida.newLine();
			}

			System.out.println("Reporte generado correctamente");

		} catch (IOException e) {
			System.out.println("Problema generando el reporte de frecuencias");
			e.printStackTrace();
		}






	}

	  //3. Eliminar caracteres especiales

	public static void eliminarCaracteresEspeciales(String archivoDeEntrada, String archivoDeSalida) {

		try (BufferedReader brArchivoEntrada = new BufferedReader(new FileReader(archivoDeEntrada));
				BufferedWriter bwArchivoSalida = new BufferedWriter(new FileWriter(archivoDeSalida));) {
			String linea;

			int numeroLinea = 1;

			while ((linea = brArchivoEntrada.readLine()) != null) {
				Camiseta camiseta = Camiseta.fromCSV(linea);

				if(camiseta != null) {
					camiseta.depurarCaracteresEspeciales();
					bwArchivoSalida.write(String.format("%d,%s", numeroLinea, camiseta.toCSV()));
					bwArchivoSalida.newLine();
					numeroLinea++;
				}
			}

			System.out.printf("Datos depurados correctamente en %s%n", archivoDeSalida);

		} catch (IOException e) {
			System.out.println("Problema eliminando caracteres especiales");
			e.printStackTrace();
		}

	}

	//4. Generar SQL

	public static void generarSQL (String archivoDeEntrada) {

		try (BufferedReader brArchivoEntrada = new BufferedReader(new FileReader(archivoDeEntrada));
				BufferedWriter bwArchivoSQL = new BufferedWriter(new FileWriter("src/ficheros.camisetas/data/camisetas.sql"));) {

			StringBuilder sql = new StringBuilder();
			sql.append("-- Creación de la base de datos y tabla\n");
			sql.append("CREATE DATABASE IF NOT EXISTS camisetas;\n");
			sql.append("USE camisetas;\n\n");

			sql.append("CREATE TABLE IF NOT EXISTS camisetas (\n");
			sql.append("    id INT AUTO_INCREMENT PRIMARY KEY,\n");
			sql.append("    cantidad INT,\n");
			sql.append("    color VARCHAR(50),\n");
			sql.append("    marca VARCHAR(100),\n");
			sql.append("    modelo VARCHAR(100),\n");
			sql.append("    talla VARCHAR(20)\n");
			sql.append(");\n\n");

			sql.append("-- Inserción de datos\n");

			bwArchivoSQL.write(sql.toString());
			bwArchivoSQL.newLine();

			String linea;

			while ((linea = brArchivoEntrada.readLine()) != null) {
				Camiseta camiseta = Camiseta.fromCSV(linea);

				if(camiseta != null) {
					bwArchivoSQL.write(camiseta.toSQL());
					bwArchivoSQL.newLine();
				}
			}

			System.out.println("Archivo SQL generado correctamente");

		} catch (IOException e) {
			System.out.println("Problema generando archivo SQL");
		}


	}


}
