<?php
include "connection.php";
$color = $_GET["color"];
if (empty($color)) {
    echo ("Please enter a new Colour");
}else if (!preg_match('/^[a-zA-z]+$/',$color)) {
    echo ("Invalid ,only include text");
} else {
    $color_rs = Database::search("SELECT * FROM `color` WHERE `color` LIKE '" . $color . "' ");
    $color_num = $color_rs->num_rows;
    if ($color_num > 0) {
        echo ("The Color is already registered");
    } else {
        Database::iud("INSERT INTO `color` (`color`) VALUE ('" . $color . "')");
        echo ("success");
    }
}
