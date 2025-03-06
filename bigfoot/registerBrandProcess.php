<?php

include "connection.php";
$brand = $_GET["brand"];
if (empty($brand)) {
    echo ("Please add the Title");
}else if (!preg_match('/^[a-zA-z]+$/',$brand)) {
    echo ("Invalid ,only include text");
} else {
    $brand_rs = Database::search("SELECT * FROM `brand` WHERE `name` LIKE '" . $brand . "' ");
    $brand_num = $brand_rs->num_rows;
    if ($brand_num > 0) {
        echo ("Brand Alreadt Exists");
    } else {
        Database::iud("INSERT INTO `brand` (`name`) VALUE ('" . $brand . "')");
        echo ("success");
    }
}
