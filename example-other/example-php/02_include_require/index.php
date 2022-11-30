<!DOCTYPE html>
<html>
<?php include 'header.php'; ?>
<body>

    <div class="leftmenu">
        <?php include 'menu.php'; ?>
    </div>

    <h1>Welcome to my home page!</h1>
    <?php require 'vars.php';
    echo "I have a $color $car"; // I have a red BMW
    ?>

</body>
</html>
