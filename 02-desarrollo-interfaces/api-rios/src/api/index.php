<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET, POST, OPTIONS');
header('Access-Control-Allow-Headers: Content-Type, Authorization');
header("Content-Type: application/json; charset=UTF-8");

$host = 'db';
$db   = 'rios';
$user = 'root';
$pass = 'root';

try {
    $pdo = new PDO(
        "mysql:host=$host;dbname=$db;charset=utf8mb4",
        $user,
        $pass,
        [PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION]
    );
} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode([
        'error' => 'Error de conexión',
        'mensaje' => $e->getMessage()
    ]);
    exit;
}



$where = [];
$params = [];


if (isset($_GET['id']) && is_numeric($_GET['id'])) {
    $where[] = 'id_rio = :id';
    $params[':id'] = (int) $_GET['id'];
}


if (isset($_GET['nombre'])) {
    $where[] = 'nombre_rio = :nombre';
    $params[':nombre'] = $_GET['nombre'];
}


if (isset($_GET['continente'])) {
    $where[] = 'continente = :continente';
    $params[':continente'] = $_GET['continente'];
}

$sql = 'SELECT * FROM rios';

if (!empty($where)) {
    $sql .= ' WHERE ' . implode(' AND ', $where);
}

$stmt = $pdo->prepare($sql);
$stmt->execute($params);

$resultado = $stmt->fetchAll(PDO::FETCH_ASSOC);



foreach ($resultado as &$rio) {
    $rio['paises_rio'] = array_map(
        'trim',
        explode(',', $rio['paises_rio'])
    );
}

echo json_encode($resultado, JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE);
