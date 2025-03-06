<?php

session_start();

require "connection.php";

if (isset($_SESSION["academic"])) {
    $Ano = $_SESSION["academic"]["Ano"];

?>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Assignmetnt View & Submit Marks</title>

        <link rel="stylesheet" href="bootstrap.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
        <link rel="stylesheet" href="style.css" />

        <link rel="icon" href="resources/images/logo.jpg" />
    </head>


    <label class="d-none" id="ano"><?php echo ($Ano); ?></label>
    <label class="d-none" id="user">academic</label>

    <body class="main_body2  ">

        <div class="container-fluid">
            <div class="row">

                <div class="col-12 col-lg-2 container-fluid border-end">
                    <div class="row ">
                        <div class="col-12 align-items-start   ">
                            <div class="row g-1 text-center justify-content-center ">

                                <?php
                                $imgRs = Database::search("SELECT * FROM `ac_profile` WHERE `academic_officer_Ano`='" . $Ano . "' ");
                                $img = $imgRs->fetch_assoc();
                                ?>
                                <div class="col-10">

                                    <img src="<?php echo ($img["img_path"]) ?>" style=" width: 120px; border-radius: 90px; margin-right: 1px; " class=" mt-3 " alt="">

                                </div>



                                <div class="col-12 mt-2">

                                    <h4 class="text-primary      fs-4   "><?php echo $_SESSION["academic"]["firstName"] . " " . $_SESSION["academic"]["lastName"]; ?></h4>

                                </div>
                                <div class="col-6">
                                    <button class="btn rounded-5 text-white" style="background-color: #8956ea;" onclick="editProfile();">Edit Profile</button>
                                </div>

                                <div class="col-12 mt-5 justify-content-center">

                                    <button class="btn btn-outline-secondary col-6" onclick="logOut();"> Log Out</button>

                                </div>
                            </div>

                            <hr class="border border-2  opacity-100 border-warning" />

                        </div>

                    </div>

                    <div class="row" style=" background-color: #E1E0E4; height:33vmax; overflow-y:hidden ;">
                        <div class="col-12 align-items-start">
                            <div class="row g-1">
                                <div class="row g-1 text-center  ">

                                    <div class="nav flex-column nav-pills me-3 mt-3" role="tablist" aria-orientation="vertical">
                                        <nav class="nav flex-column">
                                            <a class="nav-link   border border-dark" aria-current="page" href="adminPanel.php">Registrations</a>
                                            <a class="nav-link active border border-dark mt-2" href="academicMarksView.php">View & Release <br> Assignment Marks</a>
                                        </nav>
                                    </div>


                                </div>

                            </div>
                        </div>
                    </div>
                </div>


                <div class="col-12 col-lg-10">

                    <div class="row justify-content-center">

                        <div class="row mt-5 justify-content-center">


                            <div class="col-2">
                                <input type="text" id="Tno" class="form-control" placeholder="Teacher No">
                            </div>

                            <div class="col-3">
                                <input type="text" id="Tname" class=" form-control" placeholder="Teacher's Name">
                            </div>

                            <div class="col-2">
                                <button class=" btn btn-secondary fw-bold col-12" onclick="searchMarksAsgn()">Search</button>
                            </div>

                        </div>

                        <div class=" row justify-content-center" id="viewArea">

                            <?php
                            $teachercRs = Database::search("SELECT * ,`teacher`.`firstName` AS `fname` , `teacher`.`lastName` AS `lname`  FROM `academic_officer` 
                                                            INNER JOIN `grade` ON `grade`.`id`=`academic_officer`.`grade_id`  
                                                            INNER JOIN `teacher` ON `grade`.`id`=`teacher`.`grade_id`
                                                            WHERE `Ano`='" . $Ano . "'");


                            for ($y = 0; $y < $teachercRs->num_rows; $y++) {
                                $teacher = $teachercRs->fetch_assoc();
                            ?>

                                <div class="col-10 border border-dark border-3 border-opacity-100 rounded-3 mt-5  over" style="max-height: 400px;">
                                    <div class="row justify-content-center">

                                        <h3 class="mt-3 mb-3 text-warning ">Teacher : <span class=" text-info"><?php echo ($teacher["fname"] . " " . $teacher["lname"]); ?></span></h3>
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
                                                       WHERE `teacher_Tno`='" . $teacher["Tno"] . "' AND `academic_Ano`='" . $Ano . "' AND `marks_status_id`='1'
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

                                                    <label class="d-none" id="assignmentArray"><?php echo (json_encode($assignmentNo)); ?></label>

                                                </div>

                                            </div>
                                        </div>

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
        </div>

        <script src="bootstrap.bundle.js"></script>
        <script src="script.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    </body>

    </html>

<?php

} else {
    echo ("You are Not a valid user");
}

?>