<?php

require "connection.php";

$Ano=$_GET["Ano"];
$Tno=$_GET["Tno"];
$Sno=$_GET["Sno"];
$ASno=$_GET["ASno"];

if(isset($Tno)){

    Database::iud("UPDATE `academic_has_asgn` SET `marks_status_id`='2' WHERE `academic_Ano`='".$Ano."'  
                   AND `assignmemt_id`='".$ASno."' AND `student_Sno`='".$Sno."'   ");

}


?>