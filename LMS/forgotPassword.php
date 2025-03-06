<?php

require "connection.php";
require "SMTP.php";
require "PHPMailer.php";
require "Exception.php";

use PHPMailer\PHPMailer\PHPMailer;

$email = $_POST["e"];
$user = $_POST["u"];

if (empty($email)) {
    echo ("Please Enter Your Email");
} else if (strlen($email) > 100) {
    echo ("Email must have less than 100 characteres");
} else if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    echo ("Invalid Email Address");
} else {




    $query = "";
    if ($user == "teacher") {
        $query == "SELECT * FROM `teacher` WHERE `email` = '" . $email . "' ";
    } elseif ($user = "academic") {
        $query = "SELECT * FROM `academic_officer` WHERE `email` = '" . $email . "' ";
    } else if ($user == "student") {
        $query = "SELECT * FROM `student` WHERE `email` = '" . $email . "'  ";
    }

    $rs = Database::search($query);
    $n = $rs->num_rows;


    if ($n == 1) {
        $code=uniqid();

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
        $mail->setFrom('dnanayakkara001@gmail.com', 'Forgot Password Verification');
        $mail->addReplyTo('dnanayakkara001@gmail.com', 'Forgot Password Verification');
        $mail->addAddress($email);
        $mail->isHTML(true);
        $mail->Subject = 'Forgot Password Verification Code';
        $bodyContent = '<h1 style="font-weight: bold; color:green;"> Your new OTP is ' . $code . '</h1>';
        $mail->Body    = $bodyContent;
        
        if (!$mail->send()) {
            echo 'Verification code sending failed';
        } else {
            echo "New OTP sent";
        }

    }
}
?>
