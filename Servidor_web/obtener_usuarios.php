<?php
include("env.php");

$query = $conn->query("SELECT * FROM usuarios");

$usuarios = [];

while ($row = $query->fetch_assoc()) {
    $usuarios[] = $row;
}

echo json_encode($usuarios);
?>