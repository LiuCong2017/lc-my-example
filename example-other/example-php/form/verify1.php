<?php
// 定义变量并默认设置为空值
$name = $email = $gender = $comment = $website = "";

if ($_SERVER["REQUEST_METHOD"] == "POST")
{
    $name = test_input($_POST["name"]);
    $email = test_input($_POST["email"]);
    $website = test_input($_POST["website"]);
    $comment = test_input($_POST["comment"]);
    $gender = test_input($_POST["gender"]);
}

function test_input($data)
{
    $data = trim($data);
    $data = stripslashes($data);
    $data = htmlspecialchars($data);
    return $data;
}
?>
    <script src="https://cdn.bootcss.com/jquery/2.0.2/jquery.min.js"></script>
    <h2>PHP 表单验证实例</h2>
    <form method="post" action="verify1.php">
        名字: <input type="text" name="name">
        <br><br>
        E-mail: <input type="text" name="email">
        <br><br>
        网址: <input type="text" name="website">
        <br><br>
        备注: <textarea name="comment" rows="5" cols="40"></textarea>
        <br><br>
        性别:
        <input type="radio" name="gender" value="female">女
        <input type="radio" name="gender" value="male">男
        <br><br>
        <input type="submit" name="submit" value="Submit">
    </form>

<?php
echo "<h2>您输入的内容是:</h2>";
echo $name;
echo "<br>";
echo $email;
echo "<br>";
echo $website;
echo "<br>";
echo $comment;
echo "<br>";
echo $gender;
?>