<?php
require "connection.php";
$email = $_POST["e"];
$np = $_POST["n"];
$rnp = $_POST["r"];
$vcode = $_POST["v"];
if(empty($email)){
    echo ("Missing Email Address");
}else if(empty ($np)){
    echo ("Please type a New Password");
}else if(strlen($np) < 5 || strlen($np) > 20){
    echo ("Invalid New Password");
}else if(empty($rnp)){
    echo ("Please Retype your Password");
}else if($np != $rnp){
    echo ("Password does not matched");
}else if(empty($vcode)){
    echo ("Please enter your Verification Code");
}else{
    $rs = Database::search("SELECT * FROM `user` WHERE 
    `email`='".$email."' AND `verification_code`='".$vcode."'");
    $n = $rs->num_rows;
    if($n == 1){
        Database::iud("UPDATE `user` SET `password`='".$np."' WHERE 
        `email`='".$email."'");
        echo ("Success");
    }else{
        echo ("Invalid Email or Verification Code");
    }
}