<?php
require "connection.php";

$Sno = $_GET["Sno"];
$Sname = $_GET["Sname"];
$Tno = $_GET["Tno"];
$Subject = $_GET["Subject"];

$query = "SELECT *, `student`.`firstName` AS `fname`, `student`.`lastName` AS `lname` FROM `academic_has_asgn` 
        INNER JOIN `student` ON `academic_has_asgn`.`student_Sno`=`student`.`Sno`
        INNER JOIN `grade` ON `student`.`grade_id`=`grade`.`id`
        INNER JOIN `teacher` ON `academic_has_asgn`.`teacher_Tno`=`teacher`.`Tno`
        INNER JOIN `subject` ON `teacher`.`subject_id`=`subject`.`id`    ";


 if (empty($Sname) && empty($Tno) && empty($Subject)) {
    $query = $query . " WHERE `student_Sno` ='" . $Sno . "' ";
} else if (empty($Sno) && empty($Tno) && empty($Subject)) {
    $query = $query . " WHERE (`student`.`firstName` LIKE '%" . $Sname . "%' OR `student`.`lastName`  LIKE '%" . $Sname . "%') ";
} else if (empty($Sname) && empty($Sno) && empty($Subject)) {
    $query = $query . " WHERE `teacher_Tno` ='" . $Tno . "' ";
} else if (empty($Sno) && empty($Sname) && empty($Tno) ) {
    $query = $query . " WHERE `subject_id` ='" . $Subject . "' ";
} else if (empty($Sname) &&  empty($Subject)) {
    $query = $query . " WHERE `student_Sno` ='" . $Sno . "' AND `teacher_Tno` ='" . $Tno . "' ";
} else if (empty($Sname) &&  empty($Tno)) {
    $query = $query . " WHERE `student_Sno` ='" . $Sno . "' AND `subject_id` ='" . $Subject . "' ";
} else {
    echo ("Please type a Student No or Student Name or Teacher No or select a Subject ");
}


    
    $resultRs = Database::search($query);

    $resultNum = $resultRs->num_rows;

    for ($x = 0; $x < $resultNum; $x++) {
        $result = $resultRs->fetch_assoc();
?>
        <div class=" row row-cols-6 text-center  fs-5" style="color: #F66D08;">

            <div class="col-2 ms-0">
                <Span><?php echo ($result["assignmemt_id"]) ?></Span>
            </div>
            <div class="col-1" style="margin-left: 10px;">
                <span><?php echo ($result["Tno"]) ?> </span>
            </div>
            <div class="col-3 ">
                <span><?php echo ($result["fname"]) . " " . $result["lname"] ?></span>
            </div>
            <div class="col-2">
                <span><?php echo ($result["sname"]) ?> </span>
            </div>
            <div class="col-2 text-">
                <span><?php echo ($result["grade"]) ?> </span>
            </div>
            <div class="col-1" style="margin-left: 50px;">
                <span><?php echo ($result["marks"]) ?> </span>
            </div>

        </div>
        <hr>

<?php
    
}
?>