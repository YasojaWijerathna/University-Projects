<?php

include "connection.php";

$size_id = $_POST["size"];
$qty = $_POST["qty"];
$price = $_POST["price"];
$pid = $_POST["pid"];
if (empty($qty)) {
    echo ("Please enter the Stock Quntity");
} else if (!preg_match('/^[0-9]+$/', $qty)) {
    echo ("Invalid qty");
} else if (empty($price)) {
    echo ("Please enter the Stock Price");
}  else if (!preg_match('/^[0-9]+$/', $price)) {
    echo ("Invalid Amount");
} else if ($size_id == 0) {
    echo ("Please select a size");
} else {
    $stock_rs = Database::search("SELECT * FROM `stock` WHERE `p_size_id` = '" . $size_id . "' 
    AND `product_id`='" . $pid . "' AND `stock_price`='" . $price . "' ");
    $stock_num = $stock_rs->num_rows;

    $product_rs=Database::search("SELECT * FROM `product` WHERE `id`='".$pid."' ");
    $dateTime = date("Y-m-d H:i:s");
    
    if ($stock_num > 0) {
        Database::iud("UPDATE `stock` SET `qty`=`qty`+'" . $qty . "' ,`date_added`='".$dateTime."'  WHERE `p_size_id` = '" . $size_id . "' 
    AND `product_id`='" . $pid . "' AND `stock_price`='" . $price . "'  ");
    echo ("success");

    } else {
        $product_data=$product_rs->fetch_assoc();

        $d = new DateTime();
        $tz = new DateTimeZone("Asia/Colombo");
        $d->setTimeZone($tz);
        $date=date("m_d");
        

        $stock_no=sprintf('%04u', $pid)."_".sprintf('%04u', $size_id)."_".$date;

        Database::iud("INSERT INTO `stock` (`stock_no`,`product_id`,`qty`,`stock_price`,`status_id`,`p_size_id`,`date_added`) 
        VALUES ('" . $stock_no . "','" . $pid . "','" . $qty . "','" . $price . "','1','" . $size_id . "','" . $dateTime. "')");

        if($product_data["status_id"]==2){
            Database::iud("UPDATE `product` SET `status_id`='1' WHERE `id`='".$pid."' ");
        }

        echo ("success");
    }
}
