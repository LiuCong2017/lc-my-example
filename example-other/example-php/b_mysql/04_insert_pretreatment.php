<?php
$servername = "localhost";
$username = "username";
$password = "password";
$dbname = "myDB";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} else {
    $sql = "INSERT INTOMyGuests (firstname, lastname, email) VALUES(?, ?, ?)";

    // 为 mysqli_stmt_prepare() 初始化statement 对象
    $stmt = mysqli_stmt_init($conn);

    //预处理语句

    if (mysqli_stmt_prepare($stmt, $sql)) {

       // 绑定参数
        /**
         * SSS:
        i - integer
        d - double
        s - string
        b - BLOB
         */
        mysqli_stmt_bind_param($stmt, 'sss', $firstname, $lastname, $email);


        // 设置参数并执行

        $firstname = 'John';
        $lastname = 'Doe';
        $email = 'john@example.com';
        mysqli_stmt_execute($stmt);

        $firstname = 'Mary';
        $lastname = 'Moe';
        $email = 'mary@example.com';
        mysqli_stmt_execute($stmt);


        $firstname = 'Julie';
        $lastname = 'Dooley';
        $email = 'julie@example.com';
        mysqli_stmt_execute($stmt);
    }
}
?>