<?php
session_start();
require "connection.php";

$email=$_GET["email"];

    $dateRs= Database::search("SELECT * FROM `student` INNER JOIN `st_portal` 
    ON `student`.`Sno`=`st_portal`.`student_Sno` WHERE `email`='".$email."' ");

    $expDate=$dateRs->fetch_assoc();

    $today=new DateTime();

    if($today > $expDate["expire_date"]){
        echo("fine");
    }elseif($today == $expDate["expire_date"]){
        echo("fine");
    }else if($today < $expDate["expire_date"]){
        echo("expired");
    }

?>