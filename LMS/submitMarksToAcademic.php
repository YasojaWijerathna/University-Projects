<?php
require "connection.php";

$Sno=$_POST["sno"];
$Tno=$_POST["tno"];
$ASno=$_POST["asno"];
$marks=$_POST["marks"];

$gradeRs=Database::search("SELECT * FROM `teacher` INNER JOIN `grade` ON `teacher`.`grade_id`=`grade`.`id` WHERE `Tno`='".$Tno."' ");
$grade=$gradeRs->fetch_assoc();


$academicRS=Database::search("SELECT * FROM `academic_officer`  WHERE `grade_id`='".$grade["id"]."'");

if($academicRS->num_rows==1){
    $academic=$academicRS->fetch_assoc();
    $Ano=$academic["Ano"];

    Database::iud("INSERT INTO `academic_has_asgn` (`student_Sno`,`teacher_Tno`,`assignmemt_id`,`academic_Ano`,`marks`,`marks_status_id`)
                   VALUES ('".$Sno."','".$Tno."','".$ASno."','".$Ano."','".$marks."','1') ");

    Database::iud("UPDATE `student_has_teacher_asgn` SET `teacher_asgn_status_id`='2'
                   WHERE `student_Sno`='".$Sno."' AND `teacher_Tno`='".$Tno."' AND `assignmemt_id`='".$ASno."' ");

}else{
    echo ("Academic Officer not found");
}

?>