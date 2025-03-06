<?php
session_start();


require "connection.php";

$email = $_POST["e"];
$password = $_POST["p"];
$user = $_POST["u"];
$rememberMe = $_POST["r"];



if (empty($email)) {
    echo ("Please Enter Your Email");
} else if (strlen($email) > 100) {
    echo ("Email must have less than 100 characteres");
} else if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    echo ("Invalid Email Address");
} else if (empty($password)) {
    echo ("Please Enter Your Password");
} else {

    $query = "";
    if ($user == "teacher") {
        $query = "SELECT * FROM `teacher` WHERE `email` = '" . $email . "' AND `password` = '" . $password . "' ";
    } elseif ($user == "academic") {
        $query = "SELECT * FROM `academic_officer` WHERE `email` = '" . $email . "' AND `password` = '" . $password . "' ";
    } else if ($user == "student") {
        $query = "SELECT * FROM `student` WHERE `email` = '" . $email . "'  AND `password` = '" . $password . "' ";
    }

    $rs = Database::search($query);
    $n = $rs->num_rows;

    if ($n == 1) {

        $data = $rs->fetch_assoc();

        if ($data["status_id"] == 1) {
            echo ("unverified");
        } else {
            if ($user == "teacher") {
                $_SESSION["teacher"] = $data;
                Database::iud(" UPDATE `teacher` SET `status_id`='2' WHERE `email` = '" . $email . "' ");

                echo ("success");
            } elseif ($user == "academic") {
                $_SESSION["academic"] = $data;
                Database::iud("UPDATE `academic_officer` SET `status_id`='2' WHERE `email` = '" . $email . "' ");

                echo ("success");
            } else if ($user == "student") {
              
                $paymentRs = Database::search("SELECT * FROM `st_portal` WHERE `student_Sno`='".$data["Sno"]."' ");
                $payment = $paymentRs->fetch_assoc();

                if ($payment["payment_status_id"] == 1) {
                    $_SESSION["student"] = $data;
                    echo ("Not payed");
                } else {
                    $_SESSION["student"] = $data;
                    Database::iud("UPDATE `student` SET `status_id`='2' WHERE `email` = '" . $email . "'  ");
                    echo ("success");
                }
            }

            if ($rememberMe == "true") {
                setcookie($user . "_email", $email, time() + (60 * 60 * 24 * 365));
                setcookie($user . "_password", $password, time() + (60 * 60 * 24 * 365));
            } else {
                setcookie("email", "", -1);
                setcookie("password", "", -1);
            }
        }
    } else {
        echo ("Invalid Email or Password");
    }
}
