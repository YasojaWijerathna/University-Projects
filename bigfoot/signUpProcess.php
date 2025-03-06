<?php
require "connection.php";
$fname=$_POST["f"];
$lname=$_POST["l"];
$email=$_POST["e"];
$password=$_POST["p"];
$mobile=$_POST["m"];
$gender=$_POST["g"];
if(empty($fname)){
    echo ("Please Enter Your Frist Name");
}else if(strlen($fname)>50){
    echo ("Frist Name must have less than 50 characters");
}else if(empty($lname)){
    echo ("Please Enter Your Last Name");
}else if(strlen($lname)>50){
    echo ("Last Name must have less than 50 characters");
}else if(empty($email)) {
    echo ("Please Enter Your Email");
}else if(strlen($email)>100){
    echo ("Emailmust have less than 50 characters");
}else if(!filter_var($email,FILTER_VALIDATE_EMAIL)){
    echo ("Invalid Email Address");
}else if(empty($password)){
    echo("Please Enter Your Password");
}else if(strlen($password)<5||strlen($password)>20){
    echo ("Password Length must be between 5-20 characters");
}else if(empty($mobile)) {
    echo ("Please Enter Your Mobile");
}else if(strlen($mobile)!=10){
    echo("Mobile Number must containt 10 characters");
}else if (!preg_match("/07[0,1,2,4,5,6,7,8][0-9]/",$mobile)){
    echo ("Invalid Mobile Number");
}else if($gender==0) {
    echo ("Please Select a Gender");
}else{
    $rs=Database::search("SELECT * FROM `user` WHERE `email`='".$email."' OR `mobile`='".$mobile."' ");
    $n=$rs->num_rows;
    if($n>0){
        echo("User with same Email or Mobile exists");
    }else{
        $d= new DateTime();
        $tz= new DateTimeZone("Asia/Colombo");
        $d->setTimezone($tz);
        $date=$d->format("Y-m-d H:i:s");
        Database::iud("INSERT INTO `user`
        (`fname`,`lname`,`email`,`password`,`mobile`,`joined_date`,`status_id`,`gender_id`)
         VALUES ('".$fname."','".$lname."','".$email."','".$password."',
         '".$mobile."','".$date."','1','".$gender."') ");
         echo("success");
    }   
}
?>