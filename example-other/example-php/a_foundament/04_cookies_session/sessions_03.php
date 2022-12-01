<?php
session_start();
if(isset($_SESSION['views']))
    unset($_SESSION['views']);

//or
/* reset all session */
session_destroy();