<?php
require "connection.php";

$ASno = $_GET["Asno"];
$Sno = $_GET["sno"];
$name = $_GET["name"];



$query = "SELECT * FROM `academic_has_asgn` INNER JOIN `teacher` ON `teacher`.`Tno`=`academic_has_asgn`.`teacher_Tno`
          INNER JOIN `subject` ON `teacher`.`subject_id`=`subject`.`id` WHERE `student_Sno`='" . $Sno . "' ";

if ($ASno != "" && $name != "") {
    $query = $query . " AND (`assignmemt_id`='" . $ASno . "' AND (`firstName` LIKE '%" . $name . "%' OR `lastName` LIKE '%" . $name . "%' ) ";
} else if ($ASno != "") {
    $query = $query . " AND `assignmemt_id`='" . $ASno . "'";
} else if ($name != "") {
    $query = $query . " AND (`firstName` LIKE '%" . $name . "%' OR `lastName` LIKE '%" . $name . "%' )";
} else {
}


$asgnRs = Database::search($query . " ORDER BY  `assignmemt_id`");

$asgnNum = $asgnRs->num_rows;

if ($asgnNum >= 1) {

    for ($x = 0; $x < $asgnRs->num_rows; $x++) {
        $assignment = $asgnRs->fetch_assoc();

        if ($assignment["marks_status_id"] == 1) {
            $marks = "Pending";
        } else if ($assignment["marks_status_id"] == 2) {
            $marks = $assignment["marks"];
        }
?>
        <div class="row  justify-content-center fw-bold text-center mt-3 me-0 mb-2   " style="font-size: larger; color: #FF8751;  ">
            <div class="row ms-1  ">

                <div class="col-3 ">
                    <Span><?php echo ($assignment["assignmemt_id"]); ?></Span>
                </div>

                <div class="col-3 justify-content-center">
                    <span><?php echo ($assignment["firstName"] . " " . $assignment["lastName"])  ?></span>

                </div>

                <div class="col-lg-2 ">
                    <Span><?php echo ($assignment["sname"]); ?></Span>
                </div>

                <div class="col-4">
                    <div class="row">

                        <div class="col-12">
                            <div class="row justify-content-center">

                                <div class="col-4 text-center justify-content-center px-3">
                                    <input type="text" class="form-control text-center  col-8" value="<?php echo ($marks); ?>">
                                </div>
                                <div class="col-2">
                                    <div class="row justify-content-end ">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            </div>
            <hr>
    <?php
    }
}
    ?>