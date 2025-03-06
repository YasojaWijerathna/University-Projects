<?php
include "connection.php";
$size = $_GET["size"];
if (empty($size)) {
    echo ("Please enter a new Shoe Size");
}else if (!preg_match('/^[a-zA-z]+$/',$size)) {
    echo ("Invalid ,only include text");
} else {
    $size_rs = Database::search("SELECT * FROM `p_size` WHERE `size` LIKE '" . $size . "' ");
    $size_num = $size_rs->num_rows;
    if ($size_num > 0) {
        echo ("The size is already registered");
    } else {
        Database::iud("INSERT INTO `p_size` (`size`) VALUE ('" . $size . "')");
        echo ("success");
    }
}
