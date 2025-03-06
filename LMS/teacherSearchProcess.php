<?php
require "connection.php";

$tno = $_GET["tno"];
$name = $_GET["name"];


$teacherRs = Database::search("SELECT * FROM `teacher` INNER JOIN `subject` ON `teacher`.`subject_id`=`subject`.`id` 
                            INNER JOIN `grade` ON `teacher`.`grade_id`=`grade`.`id`
                            INNER JOIN `status` ON `teacher`.`status_id`=`status`.`id` 
                            WHERE `Tno`='" . $tno . "' OR  (`firstName` LIKE '%" . $name . "%' OR `lastName` LIKE '%" . $name . "%' ) ");

if ($teacherRs->num_rows > 0) {
    $teacher = $teacherRs->fetch_assoc();
?>
    <div class="row  justify-content-center fw-bold text-center mt-3 mb-2  " style="font-size: larger; color: #FF8751;  ">
        <div class="col-1 ">
            <Span><?php echo $teacher["Tno"] ?></Span>
        </div>
        <div class="col-3 justify-content-center">
            <span><?php echo $teacher["firstName"] . " " . $teacher["lastName"] ?></span>
        </div>
        <div class="col-lg-3 ">
            <span><?php echo $teacher["email"] ?></span>
        </div>
        <div class="col-2">
            <span><?php echo $teacher["sname"] ?></span>
        </div>
        <div class="col-2">
            <span><?php echo $teacher["grade"] ?></span>
        </div>
        <div class="col-1">
            <span><?php echo $teacher["status"] ?></span>
        </div>
    </div>

<?php
}
?>