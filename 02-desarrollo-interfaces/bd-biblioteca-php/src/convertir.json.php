<?php
header("Access-Control-Allow-Origin: *");
//header('Content-Type: application/json');
require_once("conexion.inc.php");
$conexion = new mysqli($servidor, $usuario, $passwd, $basedatos);

if (mysqli_connect_errno()) {
    echo "Error al establecer la conexión con la base de datos: ";
    mysqli_connect_error();
    exit();
}

$resultado = $conexion ->query ("SELECT * FROM libros");
$libros = array();

while ($fila = $resultado->fetch_assoc()) {
    $libros[] = $fila;
	echo $fila['titulo'];
}

// Crear y escribir en un archivo JSON
$archivo_json = 'libros.json';
file_put_contents($archivo_json, json_encode($libros));

echo json_encode($libros);
$conexion->close();
?>
