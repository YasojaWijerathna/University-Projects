<?php


session_start();
require "connection.php";

if (isset($_POST["e"])) {


    $fname = $_POST["fn"];
    $lname = $_POST["ln"];
    $email = $_POST["e"];
    $oldEmail = $_POST["oe"];
    $password = $_POST["p"];
    $line1 = $_POST["l1"];
    $line2 = $_POST["l2"];
    $city = $_POST["c"];


    echo ($oldEmail);



    Database::iud("UPDATE `admin` SET `firstName`='" . $fname . "',`lastName`='" . $lname . "',`email`='" . $email . "',`password`='" . $password . "'
                       WHERE `email` ='" . $oldEmail . "' ");




    if (isset($_FILES["image"])) {

        $image = $_FILES["image"];

        $allowed_img_extentions = array("image/jpg", "image/jpeg", "image/png", "image/svg+xml");

        $file_ex = $image["type"];

        if (!in_array($file_ex, $allowed_img_extentions)) {
            echo "Please select a valid file type";
        } else {
            $new_file_extention;

            if ($file_ex == "image/jpg") {
                $new_file_extention = ".jpg";
            } elseif ($file_ex == "image/jpeg") {
                $new_file_extention = ".jpeg";
            } elseif ($file_ex == "image/png") {
                $new_file_extention = ".png";
            } elseif ($file_ex == "image/svg+xml") {
                $new_file_extention = ".svg";
            }

            $file_name = "LMS_files_directory/profile_img/" . $fname . "_profileImg" . uniqid() . $new_file_extention;

            move_uploaded_file($image["tmp_name"], $file_name);




            Database::iud("UPDATE `admin_profile` SET `admin_email`='" . $email . "',`line1`='" . $line1 . "',`line2`='" . $line2 . "',`city_id`='" . $city . "',`img_path`='" . $file_name . "' WHERE `admin_email` ='" . $oldEmail . "' ");
        }
    } else {


        Database::iud("UPDATE `admin_profile` SET `admin_email`='" . $email . "',`line1`='" . $line1 . "',`line2`='" . $line2 . "',`city_id`='" . $city . "' WHERE `admin_email` ='" . $oldEmail . "' ");
    }


    echo ("success");
}
