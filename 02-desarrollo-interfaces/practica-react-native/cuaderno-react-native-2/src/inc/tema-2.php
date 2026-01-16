<h2>Tema 2</h2>
<h3>Arrays</h3>
<?php

$numeros = [10, 'veinte', 40, 50]; //se puede hacer tipado dinámico
echo '<p> Lenght: ' . count ($numeros) .'</p>'; //echo imprime

echo "<p> Acceder a un elemento del array: " . $numeros[1] .'</p>';

array_push($numeros, 60);
echo '<p> Nuevo Lenght: ' . count ($numeros) .'</p>'; 
echo "<p> Elemento 4 del array: " . $numeros[4] .'</p>';
sort($numeros); //ordenarlos
echo "<p> Elemento 4 del array después de ordenarlo: " . $numeros[4] .'</p>';

echo '<h3> Foreach en PHP </h3>';
echo '<ul>';
foreach($numeros as $numero){
    echo '<li>' . $numero .'</li>';
}
echo '<ul>';

$indice = array_search('veinte', $numeros);
echo '<p> Índice del elemento "veinte" del array: ' . $indice . '</p>';   

echo '<h3> Array asociativo (como un map) </h3>';

$precios = [
"Pan" => 1.20,
"Leche" => 0.95,
"Café" => 2.50
];

echo '<p> Accediendo por clave "Leche" su valor es: ' . $precios['Leche'] . '</p>';

echo '<h3> Array multidimensional </h3>';
$productos = [
    ["nombre" => "Teclado", "precio" => 20],
    ["nombre" => "Ratón", "precio" => 10],
    ["nombre" => "Pantalla", "precio" => 150]
];

echo '<p> ' . $productos[2]['nombre'] .': ' . $productos[2]['precio'] . '</p>';



?>

