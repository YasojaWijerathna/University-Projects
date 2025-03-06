<?php

include "connection.php";

$brand_array;
$saleQty_array;
$array;

$brand_rs = Database::search("SELECT * FROM `brand` ");
$brand_num = $brand_rs->num_rows;

for ($x = 0; $x < $brand_num; $x++) {
    $brand_data = $brand_rs->fetch_assoc();
    $brand_array[$x] = $brand_data["name"];

    $sale_rs = Database::search("SELECT SUM(`invoice_item`.`qty`)AS `sum` FROM `invoice` 
    INNER JOIN `invoice_item` ON `invoice`.`id`=`invoice_item`.`invoice_id` 
    INNER JOIN `stock` ON `invoice_item`.`stock_no`=`stock`.`stock_no` 
    INNER JOIN `product` ON `stock`.`product_id`=`product`.`id` WHERE `brand_id`='" . $brand_data["id"] . "' ");
    $sale_data=$sale_rs->fetch_assoc();

    if ($sale_data["sum"] == null) {
        $saleQty_array[$x] = 0;
    } else {
        $saleQty_array[$x] = $sale_data["sum"];
    }
}

$array["brand"] = $brand_array;
$array["saleQty"] = $saleQty_array;

echo (json_encode($array));
