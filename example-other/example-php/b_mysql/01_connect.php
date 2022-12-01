<?php

/**
 * 面向对象
 */
$servername = "localhost";
$username = "username";
$password = "password";

//create connection
$conn = new mysqli($servername, $username, $password);

// detect connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
echo "Connected successfully";
$conn->close();

/**
 * 面向过程
 */
$servername = "localhost";
$username = "username";
$password = "password";

// 创建连接
$conn = mysqli_connect($servername, $username, $password);

// 检测连接
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
echo "Connected successfully";
mysqli_close($conn);

/**
 * PDO
 */
$servername = "localhost";
$username = "username";
$password = "password";

try {
    $conn = new PDO("mysql:host=$servername;dbname=myDB", $username, $password);
    echo "Connected successfully";
}
catch(PDOException $e)
{
    echo $e->getMessage();
}
$conn = null;