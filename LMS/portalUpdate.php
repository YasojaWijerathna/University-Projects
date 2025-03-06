<?php
require "connection.php";

$Sno=$_GET["sno"];
$payment_id=$_GET["payment"];


if(isset($Sno)){
    
$expRs=Database::search("SELECT * FROM `st_portal` WHERE `student_Sno`='".$Sno."' ");
$exp=$expRs->fetch_assoc();

$oldExp=new DateTime($exp["expire_date"]);
$interval = new DateInterval("P1Y");

$newExp=$oldExp->add($interval);


Database::iud("UPDATE `st_portal` SET `payment_status_id`='2' , `payment_id`='".$payment_id."' ,
              `expire_date`='".$newExp->format("Y-m-d")."' WHERE `student_Sno`='".$Sno."' ");

echo("success");
}else{
    echo "Payment Not Updated";
}

?>