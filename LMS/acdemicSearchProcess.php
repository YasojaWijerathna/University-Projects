<?php
require "connection.php";

$ACno = $_GET["acno"];
$name = $_GET["name"];


$teacherRs = Database::search("SELECT * FROM `academic_officer` 
                            INNER JOIN `grade` ON `academic_officer`.`grade_id`=`grade`.`id`
                            INNER JOIN `status` ON `academic_officer`.`status_id`=`status`.`id` 
                            WHERE `Ano`='" . $ACno . "' OR  (`firstName` LIKE '%" . $name . "%' OR `lastName` LIKE '%" . $name . "%' ) ");

if ($teacherRs->num_rows > 0) {
    $teacher = $teacherRs->fetch_assoc();
?>
    <div class="row  justify-content-center fw-bold text-center mt-3 mb-2  " style="font-size: larger; color: #FF8751;  ">
        <div class="col-1 ">
            <Span><?php echo $academic["Ano"] ?></Span>
        </div>
        <div class="col-3 ">
            <span><?php echo $academic["firstName"] . " " . $academic["lastName"] ?></span>
        </div>
        <div class="col-lg-4 ">
            <span><?php echo $academic["email"] ?></span>
        </div>

        <div class="col-2">
            <span><?php echo $academic["grade"] ?></span>
        </div>
        <div class="col-2">
            <span><?php echo $academic["status"] ?></span>
        </div>
    </div>

<?php
}
?>