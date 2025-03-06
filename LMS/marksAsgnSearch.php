<?php
require "connection.php";

$ACno = $_GET["Ano"];
$name = $_GET["name"];
$Tno = $_GET["Tno"];

$query = "SELECT * FROM `academic_has_asgn` INNER JOIN `teacher` ON `teacher`.`Tno`=`academic_has_asgn`.`teacher_Tno`  WHERE `academic_Ano`='" . $ACno . "'  ";

if ($name != "" && $Tno != "") {
    $query = $query . " AND (`teacher_Tno`='" . $Tno . "' AND (`firstName` LIKE '%" . $name . "%' OR `lastName` LIKE '%" . $name . "%' )) ";
} elseif ($Tno != "") {
    $query = $query . " AND`teacher_Tno`='" . $Tno . "'";
} else if ($name != "") {
    $query = $query . " AND (`firstName` LIKE '%" . $name . "%' OR `lastName` LIKE '%" . $name . "%' )";
} else {
}


$teachercRs = Database::search($query . " ORDER BY `teacher_Tno` ");
$teachercNum = $teachercRs->num_rows;

if ($teachercNum >= 1) {

    for ($x = 0; $x < $teacherLength["length"]; $x++) {
        $teacher = $teachercRs->fetch_assoc();
?>
        <div class="col-10 border border-dark border-3 border-opacity-100 rounded-3 mt-5  over" style="max-height: 400px;">
            <div class="row justify-content-center">

                <h3 class="mt-3 mb-3 text-warning ">Teacher : <span class=" text-info"><?php echo ($teacher["firstName"] . " " . $teacher["lastName"]); ?></span></h3>
                <label class="d-none" id="teacher<?php echo ($y); ?>"><?php echo ($teacher["Tno"]); ?></label>

                <div class="col-11 mt-1">
                    <div class="row">

                        <div class="row  mb-0 justify-content-center fw-bold text-center fs-4 " style=" color: #FF41E8;  ">
                            <div class="row">
                                <div class="col-3 ">
                                    <Span>Assignment ID</Span>
                                </div>
                                <div class="col-3 justify-content-center">
                                    <span>Student's Name</span>
                                </div>

                                <div class="col-3 text-center px-3">
                                    <span>Marks</span>
                                </div>
                                <div class="col-3">

                                </div>

                            </div>

                        </div>
                        <hr class=" border border-2 opacity-100 border-success mt-0 mb-0">

                        <div class="row ">
                            <?php

                            $asgnRs = Database::search("SELECT * FROM `academic_has_asgn` 
                                                       INNER JOIN `student` ON `academic_has_asgn`.`student_Sno`=`student`.`Sno`
                                                       WHERE `teacher_Tno`='" . $teacher["Tno"] . "' AND `academic_Ano`='" . $ACno . "' AND `marks_status_id`='1'
                                                       ORDER BY `assignmemt_id` ASC");

                            if ($asgnRs->num_rows > 0) {

                                for ($x = 0; $x < $asgnRs->num_rows; $x++) {
                                    $assignment = $asgnRs->fetch_assoc();
                                   

                            ?>
                                    <div class="row  justify-content-center fw-bold text-center mt-3 me-0 mb-2   " style="font-size: larger; color: #FF8751;  ">
                                        <div class="row ms-1  ">

                                            <div class="col-3 ">
                                                <Span id="asgn<?php echo $y; ?><?php echo $x; ?>"><?php echo ($assignment["assignmemt_id"]); ?></Span>
                                            </div>

                                            <div class="col-3 justify-content-center">
                                                <span><?php echo ($assignment["firstName"] . " " . $assignment["lastName"])  ?></span>
                                                <label class="d-none" id="sno<?php echo $y; ?><?php echo ($x); ?>"><?php echo ($assignment["student_Sno"]); ?></label>
                                            </div>


                                            <div class="col-6">
                                                <div class="col-12">
                                                    <div class="row justify-content-center">
                                                        <div class="col-4 text-center px-3 ms-3 ">
                                                            <span><?php echo ($assignment["marks"]); ?></span>
                                                        </div>
                                                        <div class="col-6">
                                                            <button class="btn btn-success " onclick="releaseMarks(<?php echo ($y); ?>,<?php echo ($x); ?>);">Release Marks</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>

                                <?php
                                }
                            } else {
                                ?>
                                <div class="row  justify-content-center fw-bold text-center mt-3 me-0 mb-2   " style="font-size: larger; color: #FF8751;  ">
                                    <div class="row ms-1  ">

                                        <Div class="col-12">
                                            <p class="text-black">No Marks received</p>
                                        </Div>

                                    </div>
                                </div>

                            <?php
                            }
                            ?>
                        </div>

                    </div>
                </div>

            </div>
        </div>
<?php
    }
}

?>