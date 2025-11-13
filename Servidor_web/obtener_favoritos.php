<?php
include("env.php");

//Recibe datos del cliente (Android)
$id_usuario = $_POST['id'] ?? '';

//
$query = $conn->prepare("SELECT 
     fav.id as id_favorito,
     fav.fecha_agregado,
     o.id,
     o.title,
     o.synopsis,
     o.id_autor,
     o.id_genero,
     o.id_formato,
     o.fecha_publicacion
    FROM favoritos AS fav JOIN obras AS o ON fav.id_obra = o.id 
    WHERE fav.id_usuario = ?
    ORDER BY fav.fecha_agregado DESC");

//Busca los favoritos del usuario
$query->bind_param("i", $id_usuario);
$query->execute();
$resultado = $query->get_result();

if ($resultado->num_rows > 0) {
    $row = $resultado->fetch_assoc();

    echo json_encode([
        "status" => "success", 
        "favorites" => [
            "id_favorito" => $row['id_favoritos'],
            "fecha_agregado" => $row['fecha_agregado'],
            "id" => $row['id'],
            "title" => $row['title'],
            "synopsis" => $row['synopsis'],
            "id_autor" => $row['id_autor'],
            "id_genero" => $row['id_genero'],
            "id_formato" => $row['id_formato'],
            "fecha_publicacion" => $row['fecha_publicacion']
        ]
    ]);
    
} else {
    echo json_encode(["status" => "error", "message" => "Usuario no encontrado"]);
}

$conn->close();
?>