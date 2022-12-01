<?php
/**
 * 面向对象
 */
$servername = "localhost";
$username = "username";
$password =
    "password";
$dbname =
    "myDB";

// 创建链接
$conn =
    new mysqli($servername, $username, $password, $dbname);

// 检查链接

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}


$sql = "INSERT INTO
MyGuests (firstname, lastname, email)

VALUES ('John', 'Doe', 'john@example.com');";

$sql .= "INSERT INTO
MyGuests (firstname, lastname, email)

VALUES ('Mary', 'Moe', 'mary@example.com');";

$sql .= "INSERT INTO
MyGuests (firstname, lastname, email)

VALUES ('Julie', 'Dooley', 'julie@example.com')";


if ($conn->multi_query($sql) === TRUE) {
    echo "New
records created successfully";
} else {
    echo
        "Error: " . $sql . "
" . $conn->error;
}

$conn->close();


/**
 * 面向过程
 */
$servername = "localhost";
$username = "username";
$password =
    "password";
$dbname =
    "myDB";

// 创建链接
$conn = mysqli_connect($servername, $username, $password,
    $dbname);

// 检查链接

if (!$conn) {
    die("Connection
failed: " . mysqli_connect_error());
}

$sql = "INSERT INTO
MyGuests (firstname, lastname, email)

VALUES ('John', 'Doe', 'john@example.com');";

$sql .= "INSERT INTO
MyGuests (firstname, lastname, email)

VALUES ('Mary', 'Moe', 'mary@example.com');";

$sql .= "INSERT INTO
MyGuests (firstname, lastname, email)

VALUES ('Julie', 'Dooley', 'julie@example.com')";


if (mysqli_multi_query($conn, $sql)) {
    echo "New
records
created successfully";
} else {
    echo "Error: "
        . $sql . "
" . mysqli_error($conn);
}

mysqli_close($conn);


/**
 * PDO
 */
$servername = "localhost";
$username = "username";

$password = "password";
$dbname =
    "myDBPDO";

try {
    $conn = new PDO("mysql:host=$servername;dbname=$dbname",
        $username, $password);

// set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE,
        PDO::ERRMODE_EXCEPTION);

// 开始事务

    $conn->beginTransaction();
// SQL 语句

    $conn->exec("INSERT INTO MyGuests (firstname, lastname, email)

VALUES ('John', 'Doe', 'john@example.com')");

    $conn->exec("INSERT INTO MyGuests (firstname, lastname, email)

VALUES ('Mary', 'Moe', 'mary@example.com')");

    $conn->exec("INSERT INTO MyGuests (firstname, lastname, email)

VALUES ('Julie', 'Dooley', 'julie@example.com')");


// commit the transaction
    $conn->commit();

    echo "New records created successfully";
}
catch(PDOException $e)
{

// roll back the transaction if something failed

    $conn->rollback();

    echo $sql . "
" . $e->getMessage();
}


$conn = null;