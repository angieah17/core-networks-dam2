<?php
declare(strict_types=1);

require_once __DIR__ . '/../inc/datos.php';

/**
 * @param string $slug
 * @param array $tematicas
 * @return array|null
 */
function buscarTemaPorSlug(string $slug, array $tematicas): ?array
{
    foreach ($tematicas as $tema) {
        if ($tema['slug'] === $slug) {
            return $tema;
        }
    }
    return null;
}

/**
 * @param string $slug
 * @return string
 */
function obtenerContenidoHTML(string $slug): string
{
    $archivo = __DIR__ . '/../inc/' . $slug . '.php';

    if (!file_exists($archivo)) {
        return '';
    }

    ob_start();
    include $archivo;
    return ob_get_clean();
}

header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET, POST, OPTIONS');
header('Access-Control-Allow-Headers: Content-Type, Authorization');
header('Content-Type: application/json; charset=utf-8');


$contenido = $_GET['contenido'] ?? null;

if (!$contenido) {

    $lista = [];

    foreach ($tematicas as $tema) {
        $lista[] = [
            'slug'        => $tema['slug'],
            'titulo'      => $tema['titulo'],
            'descripcion' => $tema['descripcion']
        ];
    }

    echo json_encode($lista, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
    exit;
}

$tema = buscarTemaPorSlug($contenido, $tematicas);

if (!$tema) {
    http_response_code(404);
    echo json_encode(
        ['error' => 'Tema no encontrado'],
        JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT
    );
    exit;
}

$respuesta = [
    'slug'        => $tema['slug'],
    'titulo'      => $tema['titulo'],
    'descripcion' => $tema['descripcion'],
    'contenido'   => obtenerContenidoHTML($tema['slug'])
];

echo json_encode($respuesta, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);