<?php
include("env.php");

//Aqui recibe los datos que vienen de android
$username = $_POST['username'] ?? '';
$email = $_POST['email'] ?? '';
$password = $_POST['password'] ?? '';
$biography = $_POST['biography'] ?? '';
$role = "user";

if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    echo json_encode(["status" => "error", "message" => "Email inválido"]);
    exit();
}
//comprueba si ese gmail exite
$consulta = $conn->prepare("SELECT id FROM usuarios WHERE email = ?");
$consulta->bind_param("s", $email);
$consulta->execute();
$resultado = $consulta->get_result();

if ($resultado->num_rows > 0) {
    echo json_encode(["status" => "error", "message" => "Email ya registrado"]);
    exit();
}

//inserta los datos
$query = $conn->prepare("INSERT INTO usuarios (username, email, password, biography, role) VALUES (?, ?, ?, ?, ?)");
$query->bind_param("sssss", $username, $email, $password, $biography, $role);

if ($query->execute()) {
    echo json_encode(["status" => "success", "message" => "Usuario registrado correctamente"]);
} else {
    echo json_encode(["status" => "error", "message" => "Error al registrar"]);
}

$conn->close();
?>