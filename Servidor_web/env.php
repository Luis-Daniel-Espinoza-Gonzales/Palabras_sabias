<?php
$conn = new mysqli("localhost", "root", "", "2t");
if ($conn->connect_error) {
    die("Error de conexión: " . $conn->connect_error);
}
?>
