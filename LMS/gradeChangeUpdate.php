<?php
require "connection.php";

$Sno=$_GET["sno"];
$payment_id=$_GET["payment"];


if(isset($Sno)){
    
    Database::iud("UPDATE `grade_change` SET `payment_status_id`='2',`payment_id`='".$payment_id."' WHERE `student_Sno`='".$Sno."' ORDER BY `changed_date` DESC LIMIT 1 ");


echo("success");
}else{
    echo "Payment Not Updated";
}

?>