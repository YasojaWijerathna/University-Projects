<?php

require "connection.php";
require "SMTP.php";
require "PHPMailer.php";
require "Exception.php";

use PHPMailer\PHPMailer\PHPMailer;

if(isset($_POST["e"])){

    $email=$_POST["e"];
   

    $rs= Database::search("SELECT * FROM `user` WHERE `email`='".$email."' ");
    $n=$rs->num_rows;
 

    if($n==1){
        $code=uniqid();
        $bodyContent = '<h1 style="color:green;"> Your Verification Code is '.$code.'</h1>';
        
        Database::iud("UPDATE `user` SET `verification_code`='".$code."' WHERE `email`='".$email."' ");
   
         $mail = new PHPMailer;
            $mail->IsSMTP();
            $mail->Host = 'smtp.gmail.com';
            $mail->SMTPAuth = true;
            $mail->Username = 'yasojawijerathna@gmail.com';
            $mail->Password = 'yuqzbqthpyajfdzl';
            $mail->SMTPSecure = 'ssl';
            $mail->Port = 465;
            $mail->setFrom('yasojawijerathna@gmail.com', 'Reset Password');
            $mail->addReplyTo('yasojawijerathna@gmail.com', 'Reset Password');
            $mail->addAddress($email);
            $mail->isHTML(true);
            $mail->Subject = 'BigFoot Forgot Password Verification Code';
            $mail->Body    = $bodyContent;

        if (!$mail->send()) {
            echo 'Verification code sending failed';
        } else {
            echo "Success";
        }

    }else{
        echo ("Invalid Email");
    }
    
 }

?>