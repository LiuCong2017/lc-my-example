<?php
//open session
session_start();

//store session data
$_SESSION['views']=1;

?>
<html>
<body>

<?php
//retrieve session data
echo "PageViews=".$_SESSION['views'];
?>

</body>
</html>