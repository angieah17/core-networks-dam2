<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Título de la página</title>
    <link rel="stylesheet" href="static/css/reset.css">
    <link rel="stylesheet" href="static/css/monokai.min.css" id="estilos-highlightjs"> <!-- Con esto le da estilo de código -->
    <link rel="stylesheet" href="static/css/estilos.css">
</head>
<body>
    <header>
        <h1>Apuntes sobre React Native</h1>
        <nav>
            <ul>
                
                <?php

                /*Otra forma de hacerlo:
                foreach($tematicas as $tema){
                        echo '<li><a href="' . $tema['slug'] . '.php">' . $tema['menu'] . '</a></li>';
                    }

                */

                ?>

                <?php foreach($tematicas as  $tema ):  ?>
                    <li>
                        <a href="?contenido=<?=$tema["slug"] ?>">
                            <?=$tema["menu"] ?>
                        </a>
                    </li>
                <?php endforeach; ?>


            </ul>
            
        </nav>
    </header>
