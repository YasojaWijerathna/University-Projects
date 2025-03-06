<?php

session_start();
require "connection.php";

if (isset($_SESSION["u"])) {


    $fname = $_POST["fname"];
    $lname = $_POST["lname"];
    $email = $_POST["email"];
    $mobile = $_POST["mobile"];
    $Address = $_POST["newAddress"];
    $line1 = $_POST["line1"];
    $line2 = $_POST["line2"];
    $city_id = $_POST["city"];
    $district_id = $_POST["district"];
    $province_id = $_POST["province"];
    $shipping = $_POST["shipping"];
    $discount = $_POST["discount"];
    $num = $_POST["num"];
    $qty_array =json_decode( $_POST["qty"]);
    $stock_no_array = json_decode($_POST["stock_no"]);
   


    if ($Address==1) {
        $newAddress=false;
    } elseif ($Address==2) {
        $newAddress=true;
    }

    if (empty($fname)) {
        echo "fname";
    } elseif (empty($lname)) {
        echo "lname";
    } elseif (empty($email)) {
        echo "email";
    } else if (empty($mobile)) {
        echo "mobile";
    } else if ($newAddress) {
      
        $postalCode = $_POST["pcode"];

        if (empty($line1)) {
            echo "line1";
        } elseif (empty($line2)) {
            echo "lne2";
        } elseif ($city_id == 0) {
            echo "city";
        } elseif ($district_id == 0) {
            echo "district";
        } elseif ($province_id == 0) {
            echo "province";
        } elseif (empty($postalCode)) {
            echo "pcode";
        } else {

            Database::iud("INSERT INTO `user_has_address` (`line1`,`line2`,`postal_code`,`user_email`,`city_id`)
             VALUES ('" . $line1 . "','" . $line2 . "','" . $postalCode . "','" . $_SESSION["u"]["email"] . "','" . $city_id . "') ");

            $address_rs = Database::search("SELECT * FROM `city` INNER JOIN `district` ON `city`.`district_id`=`district`.`id`
            INNER JOIN `province` ON `district`.`province_id`=`province`.`id`  ");
            $address_data = $address_rs->fetch_assoc();

            $city = $address_data["cname"];
            $district = $address_data["dname"];
            $province = $address_data["pname"];
            $order_id = uniqid();

           
            $total = 0;
            $title=null;
            for ($x = 0; $x < $num; $x++) {
                $stock_rs = Database::search("SELECT * FROM `stock` INNER JOIN `product` ON `stock`.`product_id`=`product`.`id` 
                WHERE `stock_no`='" . $stock_no_array[$x] . "' ");
                $stock_data = $stock_rs->fetch_assoc();
                $qty = (int)$qty_array[$x];
                $total = $total +((int) $stock_data["stock_price"]*$qty);
                $title = $title . "," . $stock_data["title"];
            }

            $totalAmount = $total + (int)$shipping;
            $totalAmount=$totalAmount-(int)$discount;

            $currency = "LKR";
            $merchant_id = "1222583";
            $merchant_secret = "NDI4OTkzNDQ4MTIyMTAzOTIyMjczNTE0Nzk1NTc0MTE5MzkxNDU2Ng==";

            $hash = strtoupper(
                md5(
                    $merchant_id .
                        $order_id .
                        number_format($totalAmount, 2, '.', '') .
                        $currency .
                        strtoupper(md5($merchant_secret))
                )
            );

            $array;

            $array["oid"] = $order_id;
            $array["fname"] = $fname;
            $array["lname"] = $lname;
            $array["title"] = $title;
            $array["amount"] = $totalAmount;
            $array["shipping"] = $shipping;
            $array["email"] = $email;
            $array["mobile"] = $mobile;
            $array["stock_array"] = $stock_no_array;
            $array["qty_array"] = $qty_array;
            $array["line1"] = $line1;
            $array["line2"] = $line2;
            $array["city"] = $city;
            $array["merchant_id"] = $merchant_id;
            $array["hash"] = $hash;

            echo (json_encode($array));
        }
    } else {

        $address_rs = Database::search("SELECT * FROM `city` INNER JOIN `district` ON `city`.`district_id`=`district`.`id`
                                INNER JOIN `province` ON `district`.`province_id`=`province`.`id`  ");
        $address_data = $address_rs->fetch_assoc();

        $city = $address_data["cname"];
        $district = $address_data["dname"];
        $province = $address_data["pname"];
        $order_id = uniqid();

        // $qty = 0;
        $total = 0;
        $title=null;
        for ($x = 0; $x < $num; $x++) {
           
            $stock_rs = Database::search("SELECT * FROM `stock` INNER JOIN `product` ON `stock`.`product_id`=`product`.`id` 
                                        WHERE `stock_no`='" . $stock_no_array[$x] . "' ");
            $stock_data = $stock_rs->fetch_assoc();

            $qty = (int)$qty_array[$x];
           
            $total = $total +((int) $stock_data["stock_price"]*$qty);
            $title = $title . "," . $stock_data["title"];
        }

        

        $totalAmount = $total + (int)$shipping;
        $totalAmount=$totalAmount-(int)$discount;

        $currency = "LKR";
        $merchant_id = "1222583";
        $merchant_secret = "NDI4OTkzNDQ4MTIyMTAzOTIyMjczNTE0Nzk1NTc0MTE5MzkxNDU2Ng==";

        $hash = strtoupper(
            md5(
                $merchant_id .
                    $order_id .
                    number_format($totalAmount, 2, '.', '') .
                    $currency .
                    strtoupper(md5($merchant_secret))
            )
        );

        $array;

        $array["oid"] = $order_id;
        $array["fname"] = $fname;
        $array["lname"] = $lname;
        $array["title"] = $title;
        $array["amount"] = $totalAmount;
        $array["discount"] = $discount;
        $array["shipping"] = $shipping;
        $array["email"] = $email;
        $array["mobile"] = $mobile;
        $array["stock_array"] = $stock_no_array;
        $array["qty_array"] = $qty_array;
        $array["num"] = $num;
        $array["line1"] = $line1;
        $array["line2"] = $line2;
        $array["city"] = $city;
        $array["merchant_id"] = $merchant_id;
        $array["hash"] = $hash;

        echo (json_encode($array));
    }
} else {
    echo ("Error");
}
