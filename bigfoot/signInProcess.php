<?php
session_start();


require "connection.php";

$email=$_POST["e"];
$password=$_POST["p"];
$rememberMe=$_POST["r"];

if(empty($email)){
    echo("Please Enter Your Email");
}else if(strlen($email)>100){
    echo("Email must have less than 100 characteres");
}else if(!filter_var($email,FILTER_VALIDATE_EMAIL)){
    echo("Invalid Email Address");
}else if(empty($password)){
    echo("Please Enter Your Password");
}else if(strlen($password)<5||strlen($password)>20){
    echo ("Invalid Password");
}else{

    $user_rs=Database::search("SELECT * FROM `user` WHERE `email` = '".$email."' 
    AND `password` = '".$password."' ");
    $n=$user_rs->num_rows;
  

    if($n==1){
        $data= $user_rs->fetch_assoc();
       if($data["status_id"]==1){
        
        $_SESSION["u"]=$data;
        echo ("success");
     

        if($rememberMe== "true"){
            setcookie("email",$email,time()+(60*60*24*365));
            setcookie("password",$password,time()+(60*60*24*365));
        }else{
            setcookie("email","",-1);
            setcookie("password","",-1);
        }
       }else{
        echo "Your Account is Disabled.Please Contact Website Admin";
       }
    }else{
        echo ("Invalid Email or Password");
    }
}    



?>