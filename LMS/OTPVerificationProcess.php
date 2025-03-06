<?php

require "connection.php";

$email = $_POST["e"];
$password = $_POST["p"];
$vcode = $_POST["vcode"];
$user = $_POST["u"];
$rememberMe = $_POST["r"];

$query = "";
if ($user == "teacher") {
    $query = "SELECT * FROM `teacher` WHERE `email` = '" . $email . "' ";
} elseif ($user == "academic") {
    $query = "SELECT * FROM `academic_officer` WHERE `email` = '" . $email . "' ";
} else if ($user == "student") {
    $query = "SELECT * FROM `student` WHERE `email` = '" . $email . "'  ";
}

$rs = Database::search($query);
$data = $rs->fetch_assoc();

if ($data["otp"] == $vcode) {


    if ($user == "teacher") {
        $_SESSION["teacher"] = $data;
        Database::iud(" UPDATE `teacher` SET `status_id`='2' WHERE `email` = '" . $email . "' ");
        echo "success";
    } elseif ($user == "academic") {
        $_SESSION["academic"] = $data;
        Database::iud("UPDATE `academic_officer` SET `status_id`='2' WHERE `email` = '" . $email . "' ");
        echo "success";
    } else if ($user == "student") {

        $Sno = $data["Sno"];

        $currentDate = new DateTime();

        $interval = new DateInterval("P30D");
        $expireDate = $currentDate->add($interval);


        Database::iud("INSERT INTO `st_portal` (`student_Sno`,`payment_status_id`,`login_date`,`expire_date`) 
                       VALUES ('" . $Sno . "','1','" . $currentDate->format("Y-m-d") . "','" . $expireDate->format("Y-m-d") . "')");

        $_SESSION["student"] = $data;
        Database::iud("UPDATE `student` SET `status_id`='2' WHERE `email` = '" . $email . "'  ");

        echo ("Not payed");
    }

    if ($rememberMe == "true") {
        setcookie($user . "_email", $email, time() + (60 * 60 * 24 * 365));
        setcookie($user . "_password", $password, time() + (60 * 60 * 24 * 365));
    } else {
        setcookie("email", "", -1);
        setcookie("password", "", -1);
    }
} else {
    echo ("Invalid OTP ");
}

if ($user == "teacher") {
    Database::iud(" UPDATE `teacher` SET `otp`='' WHERE `email` = '" . $email . "' ");
} elseif ($user == "academic") {
    Database::iud("UPDATE `academic_officer` SET `otp`='' WHERE `email` = '" . $email . "' ");
} else if ($user == "student") {
    Database::iud("UPDATE `student` SET `otp`='' WHERE `email` = '" . $email . "'  ");
}
