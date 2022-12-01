<?php

//
// +---------------------------------------------------------+
// | PHP version 7.4                                         |
// +---------------------------------------------------------+
// | Copyright (c) 1997-2022 Kavin               |
// +---------------------------------------------------------+
// | This source file is subject to  of the PHP license,     |
// | that is bundled with this packafile LICENSE, and is     |
// | available at through the world-web at                   |
// | http://www.php.net/license/2_02.txt.                    |
// | If you did not receive a copy of the  and are unable to |
// | obtain it through the world-wide-web,end a note to      |
// | license@php.net so we can mail you a immediately.       |
// +---------------------------------------------------------+
// | Authors: Kavin <xxx@xxx.xx>               |
// |                                                         |
// +---------------------------------------------------------+
//
// $Id: core.php,v 1.0 2022/11/13 01:26:48 ssb Exp $

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

$gBASE_DIR = "/";
$gX = 5;
$gZ = 9;

function te($n){
    global $gX;
    $y=10;
//    echo "$x";

    $GLOBALS['gZ'] = $GLOBALS['gZ']+$y;

    static $s_m=10;
    $m = $n;
    $m = variable;

    return $m;
}

te(23);

$x1 =1;
echo "$x1";
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

/**
* @ Purpose:
* 访问数据库的类，以ODBC作为通用访问接口
* @Package Name: Database
* @Author: Kavin xxx@xx.cn
* @Modifications:
* No20020523-100:
* odbc_fetch_into()参数位置第二和第三个位置调换
* John Johnson John@crtvu.edu.cn
* @See: (参照)
*/
class CarObj
{
    /**
     * @Purpose:
     * 数据库连接用户名
     * @Attribute/Variable Name: db_user_name
     * @Type: string
     */
    var $color;

    function CarObj($color){
        $this->color = $color;
    }

    /**
    * @Purpose:
    * 执行一次查询
    * @Method Name: Query()
    *
    * @Param: string $queryStr SQL查询字符串
    * @Param: string $username 用户名
    *
    * @Author: Kavin
    *
    * @Return: mixed 查询返回值（结果集对象）
    */
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

$foo = 300;
define("PAY_CHECK","200");
if (PAY_CHECK<$foo){

}

?>
</body>
</html>