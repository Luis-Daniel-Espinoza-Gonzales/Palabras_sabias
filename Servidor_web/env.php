<?php
$conn = new mysqli("localhost", "root", "", "palabras_sabias");
if ($conn->connect_error) {
    die("Error de conexión: " . $conn->connect_error);
}
?>
