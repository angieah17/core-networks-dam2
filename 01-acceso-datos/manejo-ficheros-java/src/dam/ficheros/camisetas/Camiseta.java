package dam.ficheros.camisetas;

public class Camiseta {

	//no es necesario incluir el id como atributo pues en la bd es autoincremental
	private int cantidad;
	private String color;
	private String marca;
	private String modelo;
	private String talla;

	//Este constructor servirá como base si se quiere crear una camiseta manualmente
	public Camiseta(int cantidad, String color, String marca, String modelo, String talla) {
		this.cantidad = cantidad;
		this.color = color;
		this.marca = marca;
		this.modelo = modelo;
		this.talla = talla;
	}


	public static Camiseta fromCSV (String linea) {

		String[] campos = linea.split(",", -1);

		/*Separa por ",", el -1 permite que los campos vacíos también se cuenten,
		 * lo que ayuda a verificar las camisetas que no están guardadas correctamente.
		 *
		 * El inventario siempre debe contar con 6 campos para que esté correctos. (número de línea, cantidad, color...)*/

        if (campos.length != 6) {
            return null;
        }

      //el campo[0] corresponde al número de línea que cuando esto se pase a la BD será el id y que formalmente no es un atributo de la camiseta

        String cantidadStr = campos[1].trim();

        if(!cantidadStr.isEmpty() && cantidadStr.matches("\\d+")) {  //expresión regular para verificar que se trata de dígitos y se pueda parsear
        	 int cantidad = Integer.parseInt(cantidadStr);
             String color = campos[2].trim(); //trim() elimina espacios del inicio y del final
             String marca = campos[3].trim();
             String modelo = campos[4].trim();
             String talla = campos[5].trim();

             if(color.isEmpty() || marca.isEmpty() || modelo.isEmpty() || talla.isEmpty()) {
                 return null;
             }

             return new Camiseta(cantidad, color, marca, modelo, talla);
        }


        return null;



	}

	//MÉTODOS

	//1. Verificar número correcto de comas

	public static boolean lineaTieneFormatoCorrecto(String linea) {

		int contador = 0;
		for (char c : linea.toCharArray()) {
            if (c == ',') {
                contador++;
            }
        }
        return contador == 5;

	}

	//2. Eliminar caracteres especiales

	// Depurar caracteres especiales
    public void depurarCaracteresEspeciales() {
        this.color = eliminarCaracteresEspeciales(this.color).toLowerCase();
        this.marca = eliminarCaracteresEspeciales(this.marca).toLowerCase();
        this.modelo = eliminarCaracteresEspeciales(this.modelo).toLowerCase();
        this.talla = eliminarCaracteresEspeciales(this.talla).toUpperCase();
    }

	//2.1 Función auxiliar para depurar los caracteres especiales

	private static String eliminarCaracteresEspeciales(String texto) {

		String resultado = texto;

		//2.1.1 Eliminar las tildes
		resultado = resultado.replace('á', 'a').replace('Á', 'A');
        resultado = resultado.replace('é', 'e').replace('É', 'E');
        resultado = resultado.replace('í', 'i').replace('Í', 'I');
        resultado = resultado.replace('ó', 'o').replace('Ó', 'O');
        resultado = resultado.replace('ú', 'u').replace('Ú', 'U');

        // Reemplazar ü
        resultado = resultado.replace('ü', 'u').replace('Ü', 'U');

        // Reemplazar ñ
        resultado = resultado.replace('ñ', 'n').replace('Ñ', 'N');

        return resultado;
	}


	//3. Método para convertir los atributos de la Camiseta a formato CSV (Esto lo uso en el método que depura)

	public String toCSV() {
		return String.format("%s,%s,%s,%s,%s", cantidad, color, marca, modelo, talla);
	}


	//4. Método para generar el INSERTSQL

	public String toSQL() {
		String colorValidoParaSQL = reemplazarComillas(color);
		String marcaValidaParaSQL = reemplazarComillas(marca);
		String modeloValidoParaSQL = reemplazarComillas(modelo);
		String tallaValidaParaSQL = reemplazarComillas(talla);


		return String.format( "INSERT INTO camisetas (cantidad, color, marca, modelo, talla) VALUES (%d, '%s', '%s', '%s', '%s');", cantidad,
				colorValidoParaSQL, marcaValidaParaSQL, modeloValidoParaSQL, tallaValidaParaSQL);
	}

	//4.1 Método auxiliar para prevenir que si un String tiene dentro una '' genere un fallo

	private static String reemplazarComillas (String texto) {
		return texto.replace("'", "''");
	}


	// Getters para obtener los valores al leer los archivos y contabilizar la frecuencia de cada atributo
    public int getCantidad() { return cantidad; }
    public String getColor() { return color; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getTalla() { return talla; }




}
