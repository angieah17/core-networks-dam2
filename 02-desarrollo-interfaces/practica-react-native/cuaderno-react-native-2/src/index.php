<?php

require_once "inc/datos.php"; //require evita que la página siga funcionando, include si permite que siga funcionando
//include_once o require_once : para que si por error se incluye 2 veces, esto te limita a que solo se incluya 1 vez

include "inc/header.php"; //para incluir documento haciendo referencia  a la ubicación del archivo
/*
$nombre = 'Luis'; //variables inician con dolar $
$numero = $_GET['numero'] ?? null ; //comprueba que exista el dato y no sea null, se recomienda poner con instrucciones Get

echo "<h1> Mi nombre es $nombre" . $nombre . "</h1>";  

/*Si se usa comilla doble, se puede incluir variables dentro de la misma '' sino concatenar con .
Se pueden incluir elemento html, generalmente es mejor concatenar y usar '' porque algunos 
atributos de html como style="" requieren el uso de "" */

 //se recoge el numero que se envía a través de la url index.php...numero=18 */*/

//echo '<p> y tengo ' . $numero . ' años </p>'; //echo se usa para escribir dentro de la página web 

/*Con el siguiente ejercicio se puede incluir contenido de un menú de navegación. 
*/


?> 



<main>
  
  <?php 
    $contenido = $_GET['contenido'] ?? null;
    
    if (!$contenido) {
      include 'inc/tema-1.php';
    } else {
      //echo ucfirst(str_replace("-", " ", $contenido));
      echo '<h3>' . $tematicas[ucfirst(str_replace("-", " ", $contenido))]['titulo'] . '</h3>';
      echo '<p>' . $tematicas[ucfirst(str_replace("-", " ", $contenido))]['descripcion'] . '</p>';
      include 'inc/' . $contenido . '.php';
      
    }
    
    
  ?>
</main>
<?php include 'inc/footer.php'; ?>