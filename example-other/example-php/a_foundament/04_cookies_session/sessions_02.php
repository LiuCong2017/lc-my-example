<?php
session_start();

//detect is or not set "views"
if (isset($_SESSION['views'])){
    $_SESSION['views'] = $_SESSION['views']+1;
}else{
    $_SESSION['views']=1;
}

echo "views=".$_SESSION['views'];
