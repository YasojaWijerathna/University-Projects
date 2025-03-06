<?php
require "connection.php";

$Tno=$_POST["tno"];
$file=$_FILES["file"];



$allowed_file_ext=array("application/pdf","application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                        "application/msword","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                        "application/x-zip-compressed");

$file_ext=$file["type"];

if(!in_array($file_ext,$allowed_file_ext)){
    echo "Please select a valid file type";
}else{
    $new_file_extention ;

    if($file_ext== "application/pdf"){
        $new_file_extention= ".pdf";
    }elseif($file_ext == "application/msword"){
        $new_file_extention= ".doc";
    }elseif($file_ext== "application/vnd.openxmlformats-officedocument.wordprocessingml.document"){
        $new_file_extention = ".docx";
    }elseif($file_ext == "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"){
        $new_file_extention = ".xlsx";
    }elseif($file_ext=="application/x-zip-compressed"){
        $new_file_extention = ".zip";
    }

    $new_file_name= "LMS_files_directory/assignments/".$Tno."_".pathinfo($file["name"],PATHINFO_FILENAME).$new_file_extention ;
    move_uploaded_file($file["tmp_name"],$new_file_name);

    $date=Date("Y-m-d");

    $academicRs = Database::search("SELECT * FROM `assignmemt` ORDER BY `id` DESC LIMIT 1 ");
    $data = $academicRs->fetch_assoc();

    $ASno = null;
    if ($data == null) {
        $ASno = "AS1";
    } else {
        $number = preg_replace('/[^0-9]/', '', $data["id"]);
        $ASno = "AS" . floatval($number) + 1;
    }
   

    Database::iud("INSERT INTO `assignmemt` (`id`,`asgn_path`,`date_released`) VALUES ('".$ASno."','".$new_file_name."','".$date."') ");


    $gradeRs=Database::search("SELECT * FROM `teacher` INNER JOIN `grade` ON `teacher`.`grade_id`=`grade`.`id` WHERE `Tno`='".$Tno."' ");
    $grade=$gradeRs->fetch_assoc();
    
    $studentRs=Database::search("SELECT * FROM `student` INNER JOIN `grade` ON `student`.`grade_id`=`grade`.`id` WHERE `grade`.`id`='".$grade["id"]."' ");
    
    for($x=0;$x<$studentRs->num_rows;$x++){
    $student=$studentRs->fetch_assoc();
    
    Database::iud("INSERT INTO `student_has_teacher_asgn` (`student_Sno`,`teacher_Tno`,`assignmemt_id`,`teacher_asgn_status_id`,`student_asgn_status_id`) 
                   VALUES ('".$student["Sno"]."','".$Tno."','".$ASno."','1','1')");


    }

    echo("Assignments Successfully Uploaded");

}   

?>