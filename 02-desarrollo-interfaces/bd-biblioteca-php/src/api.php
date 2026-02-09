<?php
header('Access-Control-Allow-Origin: *');
header("Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept");
header('Access-Control-Allow-Methods: GET, POST, PUT, DELETE');
header('Content-Type: application/json; charset=utf-8');
require_once("conexion.inc.php");
$conexion = new mysqli($servidor, $usuario, $passwd, $basedatos);
$conexion->set_charset("utf8"); 

if (mysqli_connect_errno()) {
    echo "Error al establecer la conexión con la base de datos: ";
    mysqli_connect_error();
    exit();
}

$resultado = $conexion->query("SELECT * FROM libros");

$libros = array();
while ($fila = $resultado->fetch_assoc()) {
    $libros[] =  $fila;
}

echo json_encode($libros, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>
