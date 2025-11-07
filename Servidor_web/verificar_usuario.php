<?php
include("env.php");

//Recibe datos del cliente (Android)
$email = $_POST['email'] ?? '';
$password = $_POST['password'] ?? '';

//Verifica si el correo tiene un formato válido, ejemplo: algo@dominio.com
if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    echo json_encode(["status" => "error", "message" => "Email inválido"]);
    exit();
}

//Busca usuario por email
$query = $conn->prepare("SELECT * FROM usuarios WHERE email = ?");
$query->bind_param("s", $email);
$query->execute();
$resultado = $query->get_result();

if ($resultado->num_rows > 0) {
    $row = $resultado->fetch_assoc();

    //Comparación sin hash (solo temporal) 
    if ($password === $row['password']) {
        echo json_encode([
            "status" => "success", 
            "message" => "Inicio de sesión exitoso",
            "user" => [
                "id" => $row['id'],
                "username" => $row['username'],
                "registration_day" => $row['registration_day'],
                "biography" => $row['biography'],
                "role" => $row['role']
            ]
        ]);
    } else {
        echo json_encode(["status" => "error", "message" => "Contraseña incorrecta"]);
    }
} else {
    echo json_encode(["status" => "error", "message" => "Usuario no encontrado"]);
}

$conn->close();
?>