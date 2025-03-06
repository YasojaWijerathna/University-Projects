<?php

session_start();
require "connection.php";

$brand = $_POST["b"];
$title = $_POST["t"];
$color = $_POST["cl"];
$caption = $_POST["ca"];
$gender = $_POST["g"];
$desc = $_POST["desc"];

if ($brand == "0") {
    echo ("Please select a Brand");
} else if (empty($title)) {
    echo ("Please add the Title");
} else if (strlen($title) >= 100) {
    echo ("Title should have less than 100 characters");
} else if ($color == "0") {
    echo ("Please select a Colour");
} else if ($gender == "0") {
    echo ("Please select a Gender");
} else if (empty($caption)) {
    echo ("Please add the Caption");
} else if (empty($desc)) {
    echo ("Please add the Description");
} else {

    $d = new DateTime();
    $tz = new DateTimeZone("Asia/Colombo");
    $d->setTimeZone($tz);
    $date = $d->format("Y-m-d H:i:s");

    $product_id = uniqid(6);
    $status = 2;

    $length = sizeof($_FILES);

    if ($length <= 3 && $length > 0) {
        $allowed_image_extentions = array("image/jpg", "image/jpeg", "image/png", "image/svg+xml");

        Database::iud("INSERT INTO `product` (`id`,`brand_id`,`title`,`color_id`,`gender_id`,`description`,`caption`,`status_id`,`datetime_added`)
                    VALUES ('" . $product_id . "','" . $brand . "','" . $title . "','" . $color . "','" . $gender . "','" . $desc . "','" . $caption . "',
                    '" . $status . "','" . $date . "' )");


        for ($x = 0; $x < $length; $x++) {
            if (isset($_FILES["image" . $x])) {

                $image_file = $_FILES["image" . $x];
                $file_extention = $image_file["type"];


                if (in_array($file_extention, $allowed_image_extentions)) {

                    $new_img_extention;

                    if ($file_extention == "image/jpg") {
                        $new_img_extention = ".jpg";
                    } else  if ($file_extention == "image/jpeg") {
                        $new_img_extention = ".jpeg";
                    } else  if ($file_extention == "image/png") {
                        $new_img_extention = ".png";
                    } else if ($file_extention == "image/svg+xml") {
                        $new_img_extention = ".svg";
                    }

                    $file_name;
                    if ($gender == 1) {
                        $file_name = "resources/men/" . $title . "_" . $x . "_" . $product_id . $new_img_extention;
                    } else if ($gender == 2) {
                        $file_name = "resources/women/" . $title . "_" . $x . "_" .$product_id  . $new_img_extention;
                    }

                    move_uploaded_file($image_file["tmp_name"], $file_name);

                    Database::iud("INSERT INTO `image` (`code`,`product_id`) VALUES ('" . $file_name . "','" . $product_id . "') ");
                    
                } else {
                    echo ("Not an allowed file type");
                }
            }
        }
        echo ("Product Added Successfully");
    } else {
        echo ("Invalid image count.Please include 3 images");
    }
}
