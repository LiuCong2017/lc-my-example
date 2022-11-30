<?php
//setcookie("user", "Alex Porter", time()+3600);
$expire=time()+60*60*24*30;
setcookie("user", "Alex Porter", $expire);
?>
<html>
<body>

<?php
// Print a cookie
echo $_COOKIE["user"];

// A way to view all cookies
print_r($_COOKIE);

if (isset($_COOKIE["user"]))
    echo "Welcome " . $_COOKIE["user"] . "!
";
else
    echo "Welcome guest!
";

// set the expiration date to one hour ago
setcookie("user", "", time()-3600);

?>

</body>
</html>