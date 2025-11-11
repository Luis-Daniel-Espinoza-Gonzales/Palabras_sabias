<?php
include("env.php");

// ✅ Autores de ciencia ficción (ajustados a tu modelo Android)
$queryAutores = "
SELECT DISTINCT 
    usuarios.id AS id_autor, 
    usuarios.username AS nombre, 
    usuarios.biography AS pais
FROM usuarios
INNER JOIN obras ON obras.id_autor = usuarios.id
WHERE obras.id_genero = 8
LIMIT 3;
";

$resAutores = $conn->query($queryAutores);
$autores = [];
while ($row = $resAutores->fetch_assoc()) {
    $autores[] = $row;
}


$queryObraDestacada = "
SELECT 
    obras.id AS id,
    obras.title AS titulo,
    obras.promedio_puntaje AS calificacion_promedio,
    usuarios.username AS autor
FROM obras
INNER JOIN usuarios ON usuarios.id = obras.id_autor
WHERE obras.promedio_puntaje IS NOT NULL
ORDER BY obras.promedio_puntaje DESC
LIMIT 1;
";

$resObra = $conn->query($queryObraDestacada);
$obraDestacada = $resObra->fetch_assoc();

echo json_encode([
    "autores" => $autores,
    "obra_destacada" => $obraDestacada
]);
?>
