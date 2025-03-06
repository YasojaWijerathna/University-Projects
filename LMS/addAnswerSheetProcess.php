<?php
require "connection.php";

$Tno = $_POST["tno"];
$Sno = $_POST["sno"];
$ASno = $_POST["asno"];
$file = $_FILES["file"];


$allowed_file_ext = array(
    "application/pdf", "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
    "application/msword", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
    "application/x-zip-compressed"
);

$file_ext = $file["type"];

if (!in_array($file_ext, $allowed_file_ext)) {
    echo "Please select a valid file type";
} else {
    $new_file_extention;

    if ($file_ext == "application/pdf") {
        $new_file_extention = ".pdf";
    } elseif ($file_ext == "application/msword") {
        $new_file_extention = ".doc";
    } elseif ($file_ext == "application/vnd.openxmlformats-officedocument.wordprocessingml.document") {
        $new_file_extention = ".docx";
    } elseif ($file_ext == "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") {
        $new_file_extention = ".xlsx";
    } elseif ($file_ext == "application/x-zip-compressed") {
        $new_file_extention = ".zip";
    }

    $new_file_name = "LMS_files_directory/answerSheet/" . $Sno . "_" . pathinfo($file["name"], PATHINFO_FILENAME) . $new_file_extention;
    move_uploaded_file($file["tmp_name"], $new_file_name);

    Database::iud("UPDATE `student_has_teacher_asgn` SET `student_asgn_status_id`='2' , `answer`='" . $new_file_name . "'
     WHERE `student_Sno`='" . $Sno . "' AND `teacher_Tno`='" . $Tno . "' AND  `assignmemt_id`='" . $ASno . "' ");
   

    echo ("AnswerSheet Successfully Uploaded");
}
