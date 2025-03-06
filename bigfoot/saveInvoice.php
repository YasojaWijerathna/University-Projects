<?php
session_start();
require "connection.php";
if (isset($_SESSION["u"])) {
    $order_id = $_POST["oid"];
    $stock_array = json_decode($_POST["stock_array"]);
    $qty_array = json_decode($_POST["qty_array"]);
    $amount = $_POST["amount"];
    $discount = $_POST["discount"];
    $email = $_POST["email"];
    $num = $_POST["num"];
    $array;
    $date = date("Y-m-d H:i:s");
    Database::iud("INSERT INTO `invoice`(`id`,`date`,`total`,`user_email`,`discount`) VALUES 
    ('" . $order_id . "','" . $date . "','" . $amount . "','" . $email . "','" . $discount . "')");
    for ($x = 0; $x < $num; $x++) {
        // echo( $stock_array[$x]);
        $stock_no = $stock_array[$x];
        $qty = $qty_array[$x];
        Database::iud("INSERT INTO `invoice_item`(`stock_no`,`qty`,`invoice_id`) VALUES
        ('" .  $stock_no . "','" . $qty . "','" . $order_id . "')");
        $stock_rs = Database::search("SELECT * FROM `stock` WHERE `stock_no`='" . $stock_no . "'");
        $stock_data = $stock_rs->fetch_assoc();
        if($stock_data["qty"]<=$qty){
           $qty=$stock_data["qty"];
           Database::iud("UPDATE `stock` SET `qty`='0', `status_id`='2' WHERE `stock_no`='".$stock_no."' ");
        }else{
            $newQty=(int)$stock_data["qty"]-$qty;
            Database::iud("UPDATE `stock` SET `qty`='".$newQty."'  WHERE `stock_no`='".$stock_no."'  ");
        }
    }
    echo ("added");
}
