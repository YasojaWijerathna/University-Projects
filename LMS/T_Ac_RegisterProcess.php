<?php

require "connection.php";
require "SMTP.php";
require "PHPMailer.php";
require "Exception.php";

use PHPMailer\PHPMailer\PHPMailer;

$fname = $_POST["fname"];
$lname = $_POST["lname"];
$email = $_POST["email"];
$password = $_POST["password"];
$gender = $_POST["gender"];
$line1 = $_POST["line1"];
$line2 = $_POST["line2"];
$city = $_POST["city"];
$occupation = $_POST["occupation"];
$grade = $_POST["grade"];

$otp = uniqid();

if (empty($fname)) {
    echo "Please enter a first name";
} else if (empty($lname)) {
    echo "Please enter a last name";
} else if (empty($email)) {
    echo "Please enter an email name";
} else if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    echo "Invalid email";
} else if (empty($password)) {
    echo "Please enter a password";
} else if (empty($line1)) {
    echo "Please enter address line 1";
} else if (empty($line2)) {
    echo "Please enter address line 2";
} else if ($city == 0) {
    echo "Please select a city";
} else if ($gender == 0) {
    echo "Please select  gender";
} else if (empty($grade)) {
    echo "Please select a grade ";
} else if ($occupation == 0) {
    echo "Please select an oocupation";
} else if ($occupation == 1) {

    $subject = $_POST["subject"];

    if (empty($subject)) {
        echo "Please select a subject ";
    }

    $teacherRs = Database::search("SELECT * FROM `teacher` ORDER BY `Tno` DESC LIMIT 1 ");
    $data = $teacherRs->fetch_assoc();

    $Tno = null;
    if ($data == null) {
        $Tno = "T01";
    } else {
        $number = preg_replace('/[^0-9]/', '', $data["Tno"]);
        $Tno = "T" . floatval($number) + 1;
    }


    Database::iud("INSERT INTO `teacher` (`Tno`,`firstName`,`lastName`,`grade_id`,`subject_id`,
                    `gender_id`,`status_id`,`email`,`password`,`otp`)
                    VALUES ('" . $Tno . "','" . $fname . "','" . $lname . "','" . $grade . "','" . $subject . "','" . $gender . "','1',
                    '" . $email . "','" . $password . "','" . $otp . "') ");

    Database::iud("INSERT INTO `t_profile` (`teacher_Tno`,`line1`,`line2`,`city_id`,`img_path`) 
                   VALUES ('" . $Tno . "','" . $line1 . "','" . $line2 . "','" . $city . "','LMS_files_directory/profile_img/default_avatar.png') ");


    $mail = new PHPMailer;
    $mail->IsSMTP();
    $mail->Host = 'smtp.gmail.com';
    $mail->SMTPAuth = true;
    $mail->Username = 'dnanayakkara001@gmail.com';
    $mail->Password = 'zsrksymumjhbxssw';
    $mail->SMTPSecure = 'ssl';
    $mail->Port = 465;
    $mail->setFrom('dnanayakkara001@gmail.com', 'Log In details & verifivation');
    $mail->addReplyTo('dnanayakkara001@gmail.com', 'Log In details & verifivation');
    $mail->addAddress($email);
    $mail->isHTML(true);
    $mail->Subject = 'Teacher Log In Details and Verification ';
    $bodyContent = '<h3 style="font-weight: bold;"> Please use the following detail to log into the Teacher Portal</h3>
                    <p>Username/Email : ' . $email . '</p>
                    <p>Password :' . $password . '</p>
                    <p>OTP :' . $otp . '  ';
    $mail->Body    = $bodyContent;

    if (!$mail->send()) {
        echo 'Verification code sending failed';
    } else {
        echo "Success";
    }
} else if ($occupation == 2) {

    $academicRs = Database::search("SELECT * FROM `academic_officer` ORDER BY `Ano` DESC LIMIT 1 ");
    $dataAC = $academicRs->fetch_assoc();

    $Ano = null;
    if ($dataAC == null) {
        $Ano = "AC1";
    } else {
        $number = preg_replace('/[^0-9]/', '', $dataAC["Ano"]);
        $Ano = "AC" . floatval($number) + 1;
    }



    Database::iud("INSERT INTO `academic_officer` (`Ano`,`firstName`,`lastName`,`gender_id`,`status_id`,`email`,`password`,`otp`,`grade_id`)
                    VALUES ('" . $Ano . "','" . $fname . "','" . $lname . "','" . $gender . "','1','" . $email . "',
                    '" . $password . "','" . $otp . "','" . $grade . "') ");

    Database::iud("INSERT INTO `ac_profile` (`academic_officer_Ano`,`line1`,`line2`,`city_id`,`img_path`) 
                   VALUES ('" . $Ano . "','" . $line1 . "','" . $line2 . "','" . $city . "','LMS_files_directory/profile_img/default_avatar.png') ");

    $mail = new PHPMailer;
    $mail->IsSMTP();
    $mail->Host = 'smtp.gmail.com';
    $mail->SMTPAuth = true;
    $mail->Username = 'dnanayakkara001@gmail.com';
    $mail->Password = 'zsrksymumjhbxssw';
    $mail->SMTPSecure = 'ssl';
    $mail->Port = 465;
    $mail->setFrom('dnanayakkara001@gmail.com', 'Log In details & verifivation');
    $mail->addReplyTo('dnanayakkara001@gmail.com', 'Log In details & verifivation');
    $mail->addAddress($email);
    $mail->isHTML(true);
    $mail->Subject = 'Academic Officer Portal Log In Details and Verification ';
    $bodyContent = '<h3 style="font-weight: bold;"> Please use the following detail to log into the Teacher Portal</h3>
                    <p>Username/Email : ' . $email . '</p>
                    <p>Password :' . $password . '</p>
                    <p>OTP :' . $otp . '  ';

    $mail->Body    = $bodyContent;

    if (!$mail->send()) {
        echo 'Verification code sending failed';
    } else {
        echo "Success";
    }
}
