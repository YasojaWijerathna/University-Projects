<?php

include "connection.php";
$product_id=$_GET["pid"];
$size_id=$_GET["sid"];

$stock_rs = Database::search("SELECT * FROM `stock` WHERE `product_id`='".$product_id."' AND `p_size_id`='".$size_id."' ");
$stock_data=$stock_rs->fetch_assoc();

if($stock_data["status_id"]==1){
    Database::iud("UPDATE `stock` SET `status_id`='2' WHERE `product_id`='".$product_id."' AND `p_size_id`='".$size_id."'");
    echo "success";
}else if($stock_data["status_id"]==2){
    Database::iud("UPDATE `stock` SET `status_id`='1' WHERE `product_id`='".$product_id."' AND `p_size_id`='".$size_id."'");
    echo "success";
}
