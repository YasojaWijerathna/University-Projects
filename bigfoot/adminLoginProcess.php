<?php
include "connection.php";
session_start();

$username=$_POST["username"];
$password=$_POST["password"];

if(empty($username)){
    echo("Please Enter Your username");
}else if(strlen($username)>30){
    echo("Username must have less than 30 characteres");
}else if(empty($password)){
    echo("Please Enter Your Password");
}else if(strlen($password)<0||strlen($password)>20){
    echo ("Invalid Password");
}else{

    $admin_rs=Database::search("SELECT * FROM `admin` WHERE `username` = '".$username."' 
    AND `password` = '".$password."' ");
    $n=$admin_rs->num_rows;

    if($n==1){
        
        $data= $admin_rs->fetch_assoc();

        $_SESSION["admin"]=$data;
        echo ("success");

    }else{
        echo ("Invalid username or Password");
    }
}    
