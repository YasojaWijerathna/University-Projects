<?php

session_start();
require "connection.php";



if(isset($_SESSION["student"])){

    $Sno=$_SESSION["student"]["Sno"];
    $order_id=uniqid();
    $array;

    $studentRs=Database::search("SELECT * FROM `student` INNER JOIN `st_profile` ON `student`.`Sno`=`st_profile`.`student_Sno`
                              INNER JOIN `st_portal` ON `student`.`Sno`=`st_portal`.`student_Sno` 
                              INNER JOIN `city`  ON `city`.`id`=`st_profile`.`city_id`  WHERE `Sno`='".$Sno."'");

    $student=$studentRs->fetch_assoc();

    $merchant_id="1221457";
    $merchant_secret="MTE4MTQ4NTM0ODIzMDE5NDAxMjMyMTE1MDE0MDQ0NDA1MzUyMDAyNg==";
    $currency="LKR";
    $amount="3000.00";

    $hash = strtoupper(
        md5(
            $merchant_id . 
            $order_id . 
            number_format($amount, 2, '.', '') . 
            $currency .  
            strtoupper(md5($merchant_secret)) 
        ) 
    );

   
    $array["Sno"]=$Sno;
    $array["merchant_id"]=$merchant_id;
    $array["merchant_secret"]=$merchant_secret;
    $array["hash"]=$hash;
    $array["amount"]=$amount;
    $array["order_id"]=$order_id;
    $array["fname"]=$student["firstName"];
    $array["lname"]=$student["lastName"];
    $array["mobile"]=$student["mobile"];
    $array["email"]=$student["email"];
    $array["address"]=$student["line1"]." ".$student["line2"];
    $array["city"]=$student["cname"];

   
    echo(json_encode($array));
    
}else{
    echo("no");
}

?>