<?php

$json = '{"a":1,"b":2,"c":3,"d":4,"e":5}';
var_dump(json_decode($json));

/**
 * assoc: 当该参数为 TRUE 时，将返回数组，FALSE 时返回对象。
 */
var_dump(json_decode($json, true));

?>
