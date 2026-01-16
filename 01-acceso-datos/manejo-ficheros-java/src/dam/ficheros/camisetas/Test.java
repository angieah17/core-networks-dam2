package dam.ficheros.camisetas;

public class Test {
	public static void main(String[] args) {

		System.out.println("=== PROCESADOR DE INVENTARIO DE CAMISETAS ===");
        System.out.println();

        //1. Filtrar lineas con errores
        System.out.println("Iniciando Filtrar líneas con errores...");
        ProcesadorCamisetas.filtraLineasConErrores("src/ficheros.camisetas/data/camisetas.txt");
        System.out.println();

        //2A. Generar reporte de frecuencias ANTES de depurar
        System.out.println("Iniciando generación de reporte de frecuencias antes de depurar...");
        ProcesadorCamisetas.generarFrecuencias("src/ficheros.camisetas/data/camisetas_sin_errores_de_linea.txt", "src/ficheros.camisetas/logs/camisetas_frecuencias_antes.log");
        System.out.println();

        //3. Depurar datos (eliminar caracteres especiales) y guardar los datos finales en camisetas_finales.txt
        System.out.println("Iniciando depuración de datos...");
        ProcesadorCamisetas.eliminarCaracteresEspeciales("src/ficheros.camisetas/data/camisetas_sin_errores_de_linea.txt", "src/ficheros.camisetas/data/camisetas_finales.txt");
        System.out.println();

        // 2B. Generar reporte de frecuencias DESPUÉS de depurar
        System.out.println("Iniciando generación de reporte de frecuencias después de depurar ...");
        ProcesadorCamisetas.generarFrecuencias("src/ficheros.camisetas/data/camisetas_finales.txt", "src/ficheros.camisetas/logs/camisetas_frecuencias_despues.log");
        System.out.println();

        // 4. Generar archivo SQL
        System.out.println("Iniciando generación archivo SQL...");
        ProcesadorCamisetas.generarSQL("src/ficheros.camisetas/data/camisetas_finales.txt");
        System.out.println();

        System.out.println("=== PROCESO COMPLETADO ===");
        System.out.println("Archivos que deben ser generados:");
        System.out.println("1. camisetas_sin_errores_de_linea.txt");
        System.out.println("2. camisetas_con_errores_de_linea.log");
        System.out.println("3. camisetas_frecuencias_antes.log");
        System.out.println("4. camisetas_finales.txt");
        System.out.println("5. camisetas_frecuencias_despues.log");
        System.out.println("6. camisetas.sql");


	}
}
