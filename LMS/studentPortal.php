<?php

session_start();

require "connection.php";

if (isset($_SESSION["student"])) {
    $Sno = $_SESSION['student']['Sno'];

?>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Student Portal </title>

        <link rel="stylesheet" href="bootstrap.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
        <link rel="stylesheet" href="style.css" />

        <link rel="icon" href="resources/images/logo.jpg" />
    </head>

    <body class="main_body2">
        <label class="d-none" id="user">student</label>
        <label class="d-none" id="sno"><?php echo ($Sno); ?></label>
        <div class="container-fluid">
            <div class="row">

                <div class="col-12 col-lg-2 container-fluid border-end" style="background-color: #57CCEB;">
                    <div class="row ">
                        <div class="col-12 align-items-start   ">
                            <div class="row g-1 text-center justify-content-center ">

                                <?php
                                $imgRs = Database::search("SELECT * FROM `st_profile` WHERE `student_Sno`='" . $Sno . "' ");
                                $img = $imgRs->fetch_assoc();
                                ?>
                                <div class="col-10">

                                    <img src="<?php echo ($img["img_path"]) ?>" style=" width: 120px; border-radius: 90px; margin-right: 1px; " class=" mt-3 " alt="">

                                </div>



                                <div class="col-12 mt-2">

                                    <h4 class="text-primary  fs-4 "><?php echo $_SESSION["student"]["firstName"] . " " . $_SESSION["student"]["lastName"]; ?></h4>

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

                    <div class="row" style=" background-color: #E1E0E4; height:50vmax; overflow-y:hidden ;">
                        <div class="col-12 align-items-start">
                            <div class="row g-1">
                                <div class="row g-1 text-center  ">

                                    <div class="nav flex-column nav-pills me-3 mt-3" role="tablist" aria-orientation="vertical">
                                        <nav class="nav flex-column">
                                            <a class="nav-link active border border-dark" aria-current="page" href="teacherPortal.php">Dashboard</a>
                                            <a class="nav-link border border-dark mt-2  " href="studentMarksView.php">View Assignment <br>Marks</a>
                                        </nav>
                                    </div>


                                </div>

                            </div>
                        </div>
                    </div>

                </div>

                <div class="col-12 col-lg-10 ">
                    <!-- Dashboard UI -->
                    <div class="row  ">

                        <?php

                        $gradeChangeRs = Database::search("SELECT * FROM `grade_change` WHERE `student_Sno`='" . $Sno . "' ORDER BY `changed_date` DESC LIMIT 1  ");

                        if ($gradeChangeRs->num_rows > 0) {
                            $gradeChange = $gradeChangeRs->fetch_assoc();
                            if ($gradeChange["payment_status_id"] == 1) {

                        ?>

                                <div class="text-danger fw-bold mb-2 fs-5   bg-white">
                                    <div class="row mt-2 mb-2">

                                        <div class="col-8 text-start">
                                            Please Make the enrolment fee for passing into the next grade
                                        </div>
                                        <div class="col-3">
                                            <button class="btn btn-success border border-dark rounded-5 mb-1" id="payhere-payment" onclick="PayNow2();">Pay Now</button>
                                        </div>

                                    </div>
                                </div>
                        <?php
                            }
                        }
                        ?>

                        <div class="text-dark fw-bold mb-1 mt-3">
                            <h1 class="fw-bold text-warning">Dashboard</h1>
                        </div>
                        <div class="col-12">
                            <hr />
                        </div>

                        <div class="col-11 container-fluid border border-2 border-opacity-100 border-secondary rounded-2">
                            <div class="row align-items-center justify-content-center " style="max-height: 680px; overflow-y: auto; overflow-x: hidden;">

                                <div class="col-12">
                                    <h3 class="mt-1" style="color: #EC35AF;">View Lesson Notes </h3>
                                    <hr class="border border-1 border-secondary border-opacity-100">
                                </div>

                                <div class="row  g-3 row-cols-lg-4 row-cols-sm-2">

                                    <?php
                                    $noteRs = Database::search("SELECT * FROM `student_has_teacher_notes` 
                                                                INNER JOIN `notes` ON `notes`.`id`=`student_has_teacher_notes`.`notes_id`
                                                                INNER JOIN `teacher` ON `teacher`.`Tno`=`student_has_teacher_notes`.`teacher_Tno`
                                                                INNER JOIN `subject` ON `teacher`.`subject_id`=`subject`.`id`
                                                                INNER JOIN `grade` ON `teacher`.`grade_id`=`grade`.`id`
                                                                WHERE `student_Sno`='" . $Sno . "' ORDER BY `notes_id` DESC ");

                                    $notesNum = $noteRs->num_rows;
                                    if ($notesNum > 0) {

                                        for ($x = 0; $x < $notesNum; $x++) {
                                            $note = $noteRs->fetch_assoc();
                                    ?>

                                            <div class=" col gy-1 mb-3">
                                                <div class="p-3 border border-1 border-dark rounded-4 " style="height: 280px; background-color: #F2F2F2;">

                                                    <div class="align-self-center  ">
                                                        <div class=" col-7  border border-1  rounded-2  " style="height: 30px; background-color: #50E2A1;">
                                                            <p class="text-light text-center fs-5"><?php echo ($note["sname"]) ?></p>
                                                        </div>
                                                    </div>

                                                    <div class="align-self-center mt-4  ">
                                                        <div class=" col-6  border border-1  rounded-2  " style="height: 30px; background-color: #2A84C2;">
                                                            <p class="text-light text-center">Note ID : <?php echo ($note["notes_id"]) ?></p>
                                                        </div>
                                                    </div>

                                                    <div class="row mt-1">
                                                        <div class="align-self-center mt-2  ">
                                                            <div class=" col-12  border border-1  rounded-2  " style="height: 30px; background-color: #6B686B;">
                                                                <p class="text-light text-center">Teacher :<?php echo ($note["firstName"] . " " . $note["lastName"]); ?></p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row mt-1">
                                                        <div class="align-self-center mt-1  ">
                                                            <div class=" col-12  border border-1  rounded-2  " style="height: 30px; background-color: #6B686B;">
                                                                <p class="text-light text-center"><?php echo ($note["grade"]) ?></p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row mt-1">
                                                        <div class="align-self-center mt-1  ">
                                                            <div class=" col-12  border border-1  rounded-2  " style="height: 30px; background-color: #6B686B;">
                                                                <p class="text-light text-center">Uploaded Date: <?php echo ($note["date_released"]) ?></p>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row mt-4 justify-content-center ms-2">
                                                        <div class=" mt-1 justify-content-center ms-3 ">
                                                            <div class=" col-5 ms-5 border border-1  text-center rounded-2   " style="height: 30px; background-color: #8956EA;">
                                                                <a href="<?php echo ($note["note_path"]) ?>" target="_blank" class="text-decoration-none text-light">View Note</a>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>

                                    <?php
                                        }
                                    }
                                    ?>

                                </div>

                            </div>
                        </div>

                        <div class="col-11 container-fluid border border-2 border-opacity-100 border-secondary rounded-2 mb-5 mt-5">
                            <div class="row align-items-center justify-content-center">

                                <div class="col-12">
                                    <h3 class="mt-1" style="color: #EC35AF;">View Pending Assignments </h3>
                                    <hr class="border border-1 border-secondary border-opacity-100">
                                </div>

                                <div class="row  g-3 row-cols-lg-4 row-cols-sm-2">

                                    <?php
                                    $asgnRs = Database::search("SELECT * FROM `student_has_teacher_asgn` 
                                                                INNER JOIN `assignmemt` ON `assignmemt`.`id`=`student_has_teacher_asgn`.`assignmemt_id`
                                                                INNER JOIN `teacher` ON `teacher`.`Tno`=`student_has_teacher_asgn`.`teacher_Tno`
                                                                INNER JOIN `subject` ON `teacher`.`subject_id`=`subject`.`id`
                                                                INNER JOIN `grade` ON `teacher`.`grade_id`=`grade`.`id`
                                                                WHERE `student_Sno`='" . $Sno . "' AND `student_asgn_status_id`='1'  ORDER BY `assignmemt_id` DESC ");

                                    $asgnNum = $asgnRs->num_rows;
                                    if ($notesNum > 0) {

                                        for ($x = 0; $x < $asgnNum; $x++) {
                                            $assignment = $asgnRs->fetch_assoc();
                                    ?>

                                            <div class=" col gy-1 mb-3">
                                                <div class="p-3 border border-1 border-dark rounded-4 " style="height: 320px; background-color: #F2F2F2;">

                                                    <div class="align-self-center  ">
                                                        <div class=" col-7  border border-1  rounded-2  " style="height: 30px; background-color: #50E2A1;">
                                                            <p class="text-light text-center fs-5"><?php echo ($assignment["sname"]) ?></p>
                                                        </div>
                                                    </div>

                                                    <div class="align-self-center mt-4  ">
                                                        <div class=" col-8  border border-1  rounded-2  " style="height: 30px; background-color: #2A84C2;">
                                                            <p class="text-light text-center">Assignment ID: <?php echo ($assignment["assignmemt_id"]) ?></p>
                                                            <label class="d-none " id="asno"><?php echo ($assignment["assignmemt_id"]) ?></label>
                                                        </div>
                                                    </div>

                                                    <div class="row mt-1">
                                                        <div class="align-self-center mt-2  ">
                                                            <div class=" col-12  border border-1  rounded-2  " style="height: 30px; background-color: #6B686B;">
                                                                <p class="text-light text-center">Teacher :<?php echo ($assignment["firstName"] . " " . $assignment["lastName"]); ?></p>
                                                                <label class="d-none " id="tno"><?php echo ($assignment["teacher_Tno"]) ?></label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row mt-1">
                                                        <div class="align-self-center mt-1  ">
                                                            <div class=" col-12  border border-1  rounded-2  " style="height: 30px; background-color: #6B686B;">
                                                                <p class="text-light text-center"><?php echo ($assignment["grade"]) ?></p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row mt-1">
                                                        <div class="align-self-center mt-1  ">
                                                            <div class=" col-12  border border-1  rounded-2  " style="height: 30px; background-color: #6B686B;">
                                                                <p class="text-light text-center">Uploaded Date: <?php echo ($assignment["date_released"]) ?></p>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-12  ">
                                                        <div class="row mt-4    ">
                                                            <div class=" mt-1   ">
                                                                <div class=" col-7 ms-5 border border-1  text-center rounded-2    " style="height: 30px; background-color: #DB3363;">
                                                                    <a href="<?php echo ($assignment["asgn_path"]) ?>" download="<?php echo ($assignment["sname"] . "_" . $notassignmente["assignmemt_id"]) ?>" class="text-decoration-none text-light">Download </a>

                                                                </div>
                                                            </div>



                                                            <div class=" mt-1  me-1">
                                                                <div class=" col-7 ms-5 border border-1  text-center rounded-2   " style="height: 30px; background-color: #05A308;">
                                                                    <input type="file" class="d-none" id="uploadAnswer" onchange="uploadAnswerSheet()">
                                                                    <label for="uploadAnswer" style="cursor: pointer;" class="text-decoration-none text-light">Upload Answers</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>

                                    <?php
                                        }
                                    }
                                    ?>

                                </div>

                            </div>
                        </div>

                    </div>
                </div>

            </div>
            <!-- Dashboard UI -->




        </div>
        </div>

        </div>
        </div>

        <script src="bootstrap.bundle.js"></script>
        <script src="script.js"></script>
        <script src="bootstrap.js"></script>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://www.payhere.lk/lib/payhere.js"></script>

    </body>

    </html>

<?php

} else {
    echo ("You are Not a valid user");
}

?>