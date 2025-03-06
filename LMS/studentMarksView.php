<?php

session_start();

require "connection.php";

if (isset($_SESSION["student"])) {
    $Sno = $_SESSION["student"]["Sno"];

?>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title> View Assignmetnt Marks </title>

        <link rel="stylesheet" href="bootstrap.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
        <link rel="stylesheet" href="style.css" />

        <link rel="icon" href="resources/images/logo.jpg" />
    </head>

    <label class="d-none" id="user">student</label>
    <label class="d-none" id="Sno"><?php echo ($Sno); ?></label>

    <body class="main_body2  ">

        <div class="container-fluid">
            <div class="row">

                <div class="col-12 col-lg-2 container-fluid border-end" >
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

                                    <h4 class="text-primary      fs-4   "><?php echo $_SESSION["student"]["firstName"] . " " . $_SESSION["student"]["lastName"]; ?></h4>

                                </div>
                                <div class="col-6">
                                    <button class="btn rounded-5 text-white" style="background-color: #8956ea;" onclick="editProfile();" >Edit Profile</button>
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

                <div class="col-12 col-lg-10">

                    <div class="row ">

                        <div class="row mt-5 justify-content-center">

                            <div class="col-2">
                                <input type="text" id="Asno" class="form-control" placeholder="Assignment No">
                            </div>

                            <div class="col-3">
                                <input type="text" id="Tname" class=" form-control" placeholder="Teacher's Name">
                            </div>

                            <div class="col-2">
                                <button class=" btn btn-secondary fw-bold col-12" onclick="searchAsssignemntMarks()">Search</button>
                            </div>

                        </div>

                        <hr class=" border border-2 opacity-100 border-success mt-5 mb-0">
                        <div class="row   justify-content-center fw-bold text-center " style="font-size: x-large; color: #FF41E8;  ">
                            <div class="row">
                                <div class="col-3 ">
                                    <Span>Assignment ID</Span>
                                </div>
                                <div class="col-3 justify-content-center">
                                    <span>Teacher's Name</span>
                                </div>
                                <div class="col-lg-2 ">
                                    <span>Subject</span>
                                </div>
                                <div class="col-3 text-center px-3">
                                    <span>Marks</span>
                                </div>
                                <div class="col-1">
                                    <span></span>
                                </div>


                            </div>

                        </div>
                        <hr class=" border border-2 opacity-100 border-success mt-0">

                        <div class="row " id="viewArea">
                            <?php

                            $asgnRs = Database::search("SELECT * FROM `academic_has_asgn` 
                                                        INNER JOIN `teacher` ON `teacher`.`Tno`=`academic_has_asgn`.`teacher_Tno`
                                                        INNER JOIN `subject` ON `teacher`.`subject_id`=`subject`.`id`
                                                       WHERE `student_Sno`='" . $Sno . "' ORDER BY `assignmemt_id` ASC");



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