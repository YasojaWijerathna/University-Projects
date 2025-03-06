<?php

require "connection.php";
require "SMTP.php";
require "PHPMailer.php";
require "Exception.php";

use PHPMailer\PHPMailer\PHPMailer;

$Sno = $_POST["Sno"];
$oldGrade = $_POST["oldGrade"];
$newGrade = $_POST["newGrade"];
$oldEmail = $_POST["oldEmail"];
$newEmail = $_POST["newEmail"];
$oldPassword = $_POST["oldPassword"];
$newPassword = $_POST["newPassword"];

$fname = $_POST["fname"];
$lname = $_POST["lname"];
$mobile = $_POST["mobile"];
$line1 = $_POST["line1"];
$line2 = $_POST["line2"];
$city = $_POST["city"];

if ($newEmail != $oldEmail || $newPassword != $oldPassword) {

    $email = array(".$newEmail.", ".$oldEmail.");

    for ($x = 0; $x < count($email); $x++) {
        $mail = new PHPMailer;
        $mail->IsSMTP();
        $mail->Host = 'smtp.gmail.com';
        $mail->SMTPAuth = true;
        $mail->Username = 'dnanayakkara001@gmail.com';
        $mail->Password = 'zsrksymumjhbxssw';
        $mail->SMTPSecure = 'ssl';
        $mail->Port = 465;
        $mail->setFrom('dnanayakkara001@gmail.com', 'Log In details ');
        $mail->addReplyTo('dnanayakkara001@gmail.com', 'Log In details ');
        $mail->addAddress($email[$x]);
        $mail->isHTML(true);
        $mail->Subject = 'Student Portal Username/Email and Password Updated';
        $bodyContent = '<h3 style="color: #ff8751;">Your Username/Email and Password for Student Portal has benn updated</h3
                    <p style="font-weight: bold; margin-top: 30px;"> Please use the following detail to log into the Teacher Portal</p>
                    <p>Username/Email : ' . $email . '</p>
                    <p>Password :' . $password . '</p>
                    <p>OTP :' . $otp . '  ';

        $mail->Body    = $bodyContent;

        if (!$mail->send()) {
            echo 'Verification code sending failed';
        } else {
            echo "New Username/Email or Password sent";
        }
    }
} 
if ($newGrade != $oldGrade) {

    $date = Date("Y-m-d");

    Database::iud("INSERT INTO `grade_change` (`student_Sno`,`payment_status_id`,`changed_date`)
                   VALUES ('" . $Sno . "','1','" . $date . "')   ");

    $mail = new PHPMailer;
    $mail->IsSMTP();
    $mail->Host = 'smtp.gmail.com';
    $mail->SMTPAuth = true;
    $mail->Username = 'dnanayakkara001@gmail.com';
    $mail->Password = 'zsrksymumjhbxssw';
    $mail->SMTPSecure = 'ssl';
    $mail->Port = 465;
    $mail->setFrom('dnanayakkara001@gmail.com', 'Enrollment Into New Grade');
    $mail->addReplyTo('dnanayakkara001@gmail.com', 'Enrollment Into New Grade');
    $mail->addAddress($newEmail);
    $mail->isHTML(true);
    $mail->Subject = 'Enrollment Into New Grade ';
    $bodyContent = '<p style="font-size: large;"> You have been passed into the next grade</p>
                    <p style="font-size: large;">Please make the enrollment fee of Rs.5000 within 2 months thorugh the Student Portal</p>

                    <button style="border-radius:5px; margin-top: 30px; background-color: green; height: 30px; width: 80px;">
                        <a href="http://localhost/LMS/studentSignIn.php" style="color: white; text-decoration: none; font-size: medium;" >Pay Now </a>
                    </button>';

    $mail->Body    = $bodyContent;

    if (!$mail->send()) {
        echo 'Verification code sending failed';
    }
}

Database::iud("UPDATE `student` SET `firstName`='".$fname."',`lastName`='".$lname."',`email`='".$newEmail."', `grade_id`='".$newGrade."',
              `password`='".$newPassword."',`mobile`='".$mobile."' WHERE `Sno`='".$Sno."' ");

Database::iud("UPDATE `st_profile` SET `line1`='".$line1."',`line2`='".$line2."',`city_id`='".$city."' WHERE `student_Sno`='".$Sno."' ");

?>