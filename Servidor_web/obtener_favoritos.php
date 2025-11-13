<?php
include("env.php");

//Recibe datos del cliente (Android)
$id_usuario = (int)$_POST['id_usuario'] ?? '';

//
$query = $conn->prepare("SELECT 
     fav.id,
     fav.fecha_agregado,
     o.id AS id_obra,
     o.title,
     o.synopsis,
     autores.username AS autor,
     generos.name AS genero,
     formatos.nombre AS formato,
     o.fecha_publicacion
    FROM favoritos AS fav 
	JOIN obras AS o ON fav.id_obra = o.id 
	JOIN usuarios AS autores ON o.id_autor = autores.id
	JOIN generos ON o.id_genero = generos.id
	JOIN formatos ON o.id_formato = formatos.id
    WHERE fav.id_usuario = ?
    ORDER BY fav.fecha_agregado DESC;");

//Busca los favoritos del usuario
$query->bind_param("i", $id_usuario);
$query->execute();
$resultado = $query->get_result();

$favoritos = [];

if ($resultado) {
    while($row = $resultado->fetch_assoc()) {
        $favoritos[] = $row; // Añade cada favorito al array
    }
} else {
    echo json_encode(["status" => "error", "message" => "Favoritos no encontrado"]);
}

echo json_encode([
    "status" => "success", 
    "favorites" => $favoritos // Devuelve el array completo
]);
$conn->close();
?>