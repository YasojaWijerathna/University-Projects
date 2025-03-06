<?php
require "connection.php";

$ASno = $_GET["asno"];
$name = $_GET["name"];
$Sno = $_GET["sno"];
$Tno = $_GET["tno"];

$query = "SELECT * FROM `student_has_teacher_asgn` INNER JOIN `student` ON `student`.`Sno`=`student_has_teacher_asgn`.`student_Sno` 
WHERE (`teacher_Tno`='" . $Tno . "' AND (`teacher_asgn_status_id`='1' AND `student_asgn_status_id`='2' ))  ";

if($ASno != "" && $Sno != "" && $name !=""){
    $query = $query . " AND (`assignmemt_id`='" . $ASno . "' AND  `student_Sno`='" . $Sno . "'AND (`firstName` LIKE '%" . $name . "%' OR `lastName` LIKE '%" . $name . "%' ) ";
} else if ($ASno != "" && $Sno != "") {
    $query = $query . " AND (`assignmemt_id`='" . $ASno . "' AND  `student_Sno`='" . $Sno . "') ";
} else if ($name !="" && $Sno != "") {
    $query = $query . " AND (`student_Sno`='" . $Sno . "' AND (`firstName` LIKE '%" . $name . "%' OR `lastName` LIKE '%" . $name . "%' )) ";
} else if ($ASno != "") {
    $query = $query . " AND `assignmemt_id`='" . $ASno . "'";
} elseif($Sno !="") {
    $query = $query . " AND`student_Sno`='" . $Sno . "'";
}else if($name !=""){
    $query=$query." AND (`firstName` LIKE '%" . $name . "%' OR `lastName` LIKE '%" . $name . "%' )";
}else{

}


$asgnRs = Database::search($query . " ORDER BY  `assignmemt_id`");

$asgnNum = $asgnRs->num_rows;

if ($asgnNum >= 1) {

    for ($x = 0; $x < $asgnRs->num_rows; $x++) {
        $assignment = $asgnRs->fetch_assoc();
?>
        <div class="row  justify-content-center fw-bold text-center mt-3 me-0 mb-2   " style="font-size: larger; color: #FF8751;  ">
            <div class="row ms-1  ">

                <div class="col-2 ">
                    <Span id="asno<?php echo ($x); ?>"><?php echo ($assignment["assignmemt_id"]); ?></Span>
                </div>

                <div class="col-3 justify-content-center">
                    <span><?php echo ($assignment["firstName"] . " " . $assignment["lastName"])  ?></span>
                    <label class="d-none" id="sno<?php echo ($x); ?>"><?php echo ($assignment["Sno"]); ?></label>
                </div>

                <div class="col-lg-2 ">
                    <a href="<?php echo ($assignment["answer"]); ?>" target="_blank" class="btn btn-secondary">View</a>
                </div>

                <div class="col-5">
                    <div class="row">

                        <div class="col-12">
                            <div class="row justify-content-center">

                                <div class="col-4 text-center justify-content-center px-3">
                                    <input type="text" id="marks<?php echo ($x); ?>" class="form-control text-center fs-5 col-8">
                                </div>
                                <div class="col-4">
                                    <div class="row justify-content-end ">
                                        <button class="col-9 btn btn-success" onclick="submitMarks(<?php echo ($x); ?>);">Submit Marks</button>
                                    </div>
                                </div>
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

            <Div class="col-12 text-center">
                <p>Either there are no uploads yet or marks have already been assigned</p>
            </Div>

        </div>
    </div>
<?php
}

?>