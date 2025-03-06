<?php

session_start();
require "connection.php";

if (!isset($_POST["email"])) {
    echo "Please Enter an Email";
} else if (!isset($_POST["password"])) {
    echo "Please Enter Password";
} else {

    $email = $_POST["email"];
    $password=$_POST["password"];

    $admin = Database::search("SELECT * FROM `admin` WHERE `email`='" . $email . "' AND `password`='".$password."' ");
    $num = $admin->num_rows;

    if ($num == 1) {

        $data = $admin->fetch_assoc();
        $_SESSION["admin"] = $data;
        echo "success";
    } else {
        echo "Invalid Email or Password";
    }
}
