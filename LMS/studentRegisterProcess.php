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
$dob = $_POST["dob"];
$mobile = $_POST["mobile"];
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
} else if (empty($dob)) {
    echo "Please select a date of birth";
}else if (empty($mobile)) {
    echo "Please enter mobile number";
}else if(!preg_match('/^[0-9]{10}+$/',$mobile)){
    echo "Invalid mobile";
} else {


    $studentRs = Database::search("SELECT * FROM `student` ORDER BY `Sno` DESC LIMIT 1 ");
    $student = $studentRs->fetch_assoc();

    $Sno = null;
    if ($student == null) {
        $Sno = "S1";
    } else {
        $number = preg_replace('/[^0-9]/', '', $student["Sno"]);
        $Sno = "S" . floatval($number) + 1;
    }

    $date=Date("Y-m-d");

    Database::iud("INSERT INTO `student` (`Sno`,`firstName`,`lastName`,`gender_id`,`status_id`,`email`,`password`,`otp`,`grade_id`,`DOB`,`registeredDate`,`mobile`)
                    VALUES ('" . $Sno . "','" . $fname . "','" . $lname . "','" . $gender . "','1','" . $email . "',
                    '" . $password . "','" . $otp . "','" . $grade . "','" . $dob . "','" . $date . "','".$mobile."') ");

    Database::iud("INSERT INTO `st_profile` (`student_Sno`,`line1`,`line2`,`city_id`,`img_path`) 
                   VALUES ('" . $Sno . "','" . $line1 . "','" . $line2 . "','" . $city . "','LMS_files_directory/profile_img/default_avatar.png') ");

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
    $mail->Subject = 'Student Portal Log In Details and Verification ';
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
