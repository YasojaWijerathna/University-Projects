<?php

require "connection.php";
session_start();

if (isset($_SESSION["u"])) {
    if (isset($_GET["pid"])) {
        $pid = $_GET["pid"];
        $size_id = $_GET["sid"];
        $email = $_SESSION["u"]["email"];


        if ($size_id == 0) {
            $stock_rs = Database::search("SELECT * FROM `stock` WHERE `product_id`='" . $pid . "' ORDER BY `p_size_id` LIMIT 1 ");
        } else {
            $stock_rs = Database::search("SELECT * FROM `stock` WHERE `product_id`='" . $pid . "' AND `p_size_id`='" . $size_id . "' ");
        }

        $stock_data = $stock_rs->fetch_assoc();
        $stock_no = $stock_data["stock_no"];


        $cart_rs = Database::search("SELECT *,`stock`.`qty` AS `stock_qty`,`cart`.`qty` AS `cart_qty` FROM `cart` INNER JOIN `stock` ON `cart`.`stock_no`=`stock`.`stock_no`
         WHERE `cart`.`stock_no`='" . $stock_no . "' AND `user_email`='" . $email . "' ");
        $cart_num = $cart_rs->num_rows;



        if ($cart_num == 1) {
            $cart_data = $cart_rs->fetch_assoc();
            $product_qty = $cart_data["stock_qty"];
            $current_qty = $cart_data["cart_qty"];
            $new_qty = (int)$current_qty + 1;

            if ($product_qty > $current_qty) {
                Database::iud("UPDATE `cart` SET `qty`='" . $new_qty . "' WHERE `stock_no`='" . $stock_no . "' AND `user_email`='" . $email . "' ");
                echo ("Update Successfully");
            } else {
                echo "Invalid Quantity";
            }
        } else {

            Database::iud("INSERT INTO `cart` (`user_email`,`stock_no`,`qty`) VALUES ('" . $email . "','" . $stock_no . "','1') ");
            echo ("New Product Added to Cart");
        }
    } else {
        echo "Something went wrong";
    }
} else {
    echo "User not found";
}
