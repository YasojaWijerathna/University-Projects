<?php
include "connection.php";
$size_id = $_GET["sid"];
$pid = $_GET["pid"];
$price_rs = Database::search("SELECT `stock_price`  FROM `stock` WHERE `p_size_id`='" . $size_id . "' 
AND `product_id`='" . $pid . "'  ");
$price_date = $price_rs->fetch_assoc();
?>
Rs. <?php echo ($price_date["stock_price"])?>.00