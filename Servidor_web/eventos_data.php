<?php
include("env.php");

// Verificar conexión
if (!$conn) {
    die(json_encode(["error" => "Error de conexión a la base de datos."]));
}


$query = "SELECT id, titulo, fecha_evento, ubicacion, descripcion, organizador, fecha_creacion FROM eventos";

$result = $conn->query($query);

if (!$result) {
    die(json_encode(["error" => "Error en la consulta: " . $conn->error]));
}

$eventos = [];
while ($row = $result->fetch_assoc()) {
    $eventos[] = $row;
}

// Enviar JSON limpio
header('Content-Type: application/json; charset=utf-8');
echo json_encode($eventos);

$conn->close();
?>
