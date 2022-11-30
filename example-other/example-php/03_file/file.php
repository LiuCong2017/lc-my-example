<html>
<body>

<?php
$file=fopen("welcome.txt","r") or exit("Unable to open file!");

while(!feof($file))
{
    echo fgets($file). "<br>";
    echo fgetc($file);
}

fclose($file);
?>

</body>
</html>