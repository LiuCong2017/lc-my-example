<?php
declare(encoding='UTF-8');
namespace demo3;
?>
<!doctype html>
<html lang="zh">
<head>
    <title></title>
</head>
<body>

<form method="post" action="<?php echo $_SERVER['PHP_SELF'];?>">
    <label>
        Name: <input type="text" name="fname">
        <input type="submit">
    </label>
</form>
<a href="test_get.php?name=PHP&web=w3cschool.cn">Test $GET</a>

<?php

$name = $_REQUEST['fname'];
$name = $_POST['fname'];
$name = $_GET['name'];

$x = 5;
$z = 9;

function te($n){
//    global $x;
    $y=10;
//    echo "$x";

    $GLOBALS['z']=$GLOBALS['x']+$y;

    static $m=10;
    $m = $n;
    $m = variable;

    return $m;
}

te(23);

echo "$x";
echo "<h2>Test</h2>";
print "ewf";
print_r("fewfwewf");

$arr = array(1,2,4);
var_dump($arr);
sort($arr);
rsort($arr);
$arrlen = count($arr);
for ($i=0;$i<$arrlen;$i++){
    echo $arr[$i];
}

$arr2 = array('a'=>3,'b'=>6);
asort($arr2);
ksort($arr2);
arsort($arr2);
krsort($arr2);
$arr2['a']=89;
//foreach ($arr2 as $k=>$v){
foreach ($arr2 as $v){
//    echo "k=".$k."v=".$v;
    echo "v=".$v;
}

// A two-dimensional array:
$cars = array
(
    array("Volvo",100,96),
    array("BMW",60,59),
    array("Toyota",110,100)
);
$families = array
(
    "Griffin"=>array
    (
        "Peter",
        "Lois",
        "Megan"
    ),
    "Quagmire"=>array
    (
        "Glenn"
    ),
    "Brown"=>array
    (
        "Cleveland",
        "Loretta",
        "Junior"
    )
);
echo "Is " . $families['Griffin'][2] . " a part of the Griffin family?";


class CarObj
{
    var $color;
    function CarObj($color){
        $this->color=$color;
    }

    function getColor(){
        return $this->color;
    }

}

$err = null;

define("CONSTANT","CST",true);
const variable = 3.45;

$str = $err."fwef";
$len = strlen($str);
$findStr = strpos($str,"w");

$GLOBALS['err'];
$_SERVER['SERVER_NAME'];

__LINE__;
__FILE__;

interface Machine{
    public function setAttr($var);
}

class Engine implements Machine {
    public var $motor;
    function setMotor($motor){
        return $this->motor=$motor;
    }

    public function setAttr($var)
    {
        // TODO: Implement setAttr() method.
    }
}
class Car extends Engine {
    protected var $name;
    private var $price;
    function __construct($name,$price)
    {
        $this->name = $name;
        $this->price = $price;
    }

    public function setMotor($motor)
    {
        return $this->motor=$motor+$this->price;
    }

    function __destruct(){
        $this->name=null;
    }

    function getPrice(){
        return $this->price;
    }
    function setPrice($price){
        $this->price = $price;
    }
}
$mercedes = new Car ("mercedes",8900);
$bmw = new Car ();
$audi = new Car ();

$bmw->setPrice(210);
$audi->getPrice();

class Tesla extends Car {
    function __construct($name, $price)
    {
        parent::__construct($name, $price);
    }
}

abstract class Chassis{
    abstract protected function getParam();
    public function setChassis(){

    }
}

final class Wheel extends Chassis{
    public static int $wheel_num = 4;

    final protected function getParam()
    {
        // TODO: Implement getParam() method.
    }
}

print Wheel::$wheel_num;

echo date("Y/m/d") . "<br>";
echo date("Y.m.d") . "<br>";
echo date("Y-m-d");


$tomorrow = mktime(0, 0, 0, date("m"), date("d") + 1, date("Y"));
echo "Tomorrow is " . date("Y/m/d", $tomorrow);

// Prints the day
echo date("l") . "<br>";

// Prints the day, date, month, year, time, AM or PM
echo date("l jS of F Y h:i:s A");


?>
</body>
</html>