<?php
require_once("conexion.inc.php");
$conexion = new mysqli($servidor, $usuario, $passwd, $basedatos);

if (mysqli_connect_errno())
{
	echo "Error al establecer la conexión con la base de datos: ";
	mysqli_connect_error();
	exit();
}

$resultado = $conexion ->query ("SELECT * FROM libros");

?>
<!doctype html>
<html lang="en">
<head>
    <title>Biblioteca virtual DAM</title>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

    <!-- Bootstrap CSS v5.2.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
</head>

<body>
    <header>
        <!-- place navbar here -->
    </header>
    <main class="container">
        <div class="row">
            <div class="col">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="row">id_libro</th>
                            <th scope="row">titulo</th>
                            <th scope="row">autor</th>
                            <th scope="row">nombre_archivo</th>
                            <th scope="row">Descargar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php
                        while ($fila = $resultado->fetch_array()) {
                            echo '<tr>';
                            echo '<th scope="col">' . $fila['id_libro'] . '</th>';
                            echo '<td>' . $fila['titulo'] . '</td>';
                            echo '<td>' . $fila['autor'] . '</td>';
                            echo '<td>' . $fila['nombre_archivo'] . '</td>';
                            echo '<td><a  class="btn btn-primary" role="button" href="libros/' . $fila['nombre_archivo'] . '" >Descargar</a></td>';
                            echo '</tr>';
                        };
                        ?>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
    <footer>
        <!-- place footer here -->
    </footer>
    <!-- Bootstrap JavaScript Libraries -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
</html>
