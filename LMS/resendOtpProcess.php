<?php

require "connection.php";
require "SMTP.php";
require "PHPMailer.php";
require "Exception.php";

use PHPMailer\PHPMailer\PHPMailer;

$email = $_POST["e"];
$user = $_POST["u"];

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



$code = uniqid();

if ($user == "teacher") {
    Database::iud(" UPDATE `teacher` SET `otp`='" . $code . "' WHERE `email` = '" . $email . "' ");
} elseif ($user == "academic") {
    Database::iud("UPDATE `academic_officer` SET `otp`='" . $code . "' WHERE `email` = '" . $email . "' ");
} else if ($user == "student") {
    Database::iud("UPDATE `student` SET `otp`='" . $code . "' WHERE `email` = '" . $email . "'  ");
}

$mail = new PHPMailer;
$mail->IsSMTP();
$mail->Host = 'smtp.gmail.com';
$mail->SMTPAuth = true;
$mail->Username = 'dnanayakkara001@gmail.com';
$mail->Password = 'zsrksymumjhbxssw';
$mail->SMTPSecure = 'ssl';
$mail->Port = 465;
$mail->setFrom('dnanayakkara001@gmail.com', 'Admin Verification');
$mail->addReplyTo('dnanayakkara001@gmail.com', 'Admin Verification');
$mail->addAddress($email);
$mail->isHTML(true);
$mail->Subject = 'Academic Officer Portal Log IN and Verification';
$bodyContent = '<h1 style="font-weight: bold;"> Your new OTP is ' . $code . '</h1>';
$mail->Body    = $bodyContent;

if (!$mail->send()) {
    echo 'Verification code sending failed';
} else {
    echo "New OTP sent";
}
?>