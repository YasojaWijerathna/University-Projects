<?php

session_start();

require "connection.php";

if (isset($_SESSION["teacher"])) {
    $Tno = $_SESSION["teacher"]["Tno"];

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

    <label class="d-none" id="user">teacher</label>
    <label class="d-none" id="tno"><?php echo ($Tno); ?></label>

    <body class="main_body2  ">

        <div class="container-fluid">
            <div class="row">
                <div class="col-12 col-lg-2 container-fluid border-end">
                    <div class="row ">
                        <div class="col-12 align-items-start   ">
                            <div class="row g-1 text-center justify-content-center ">

                                <?php
                                $imgRs = Database::search("SELECT * FROM `t_profile` WHERE `teacher_Tno`='" . $Tno . "' ");
                                $img = $imgRs->fetch_assoc();
                                ?>
                                <div class="col-10">

                                    <img src="<?php echo ($img["img_path"]) ?>" style=" width: 120px; border-radius: 90px; margin-right: 1px; " class=" mt-3 " alt="">

                                </div>



                                <div class="col-12 mt-2">

                                    <h4 class="text-primary      fs-4   "><?php echo $_SESSION["teacher"]["firstName"] . " " . $_SESSION["teacher"]["lastName"]; ?></h4>

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

                    <div class="row" style=" background-color: #E1E0E4; height:40vmax; overflow-y:hidden ;">
                        <div class="col-12 align-items-start">
                            <div class="row g-1">
                                <div class="row g-1 text-center  ">

                                    <div class="nav flex-column nav-pills me-3 mt-3" role="tablist" aria-orientation="vertical">
                                        <nav class="nav flex-column">
                                            <a class="nav-link  border border-dark" aria-current="page" href="teacherPortal.php">Dashboard</a>
                                            <a class="nav-link  active border border-dark mt-2" href="assignmentMarks_View.php">Assignmetnt View & <br>Submit Marks</a>

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

                            <div class="col-2">
                                <input type="text" id="Sno" class="form-control" placeholder="Student No">
                            </div>

                            <div class="col-3">
                                <input type="text" id="Sname" class=" form-control" placeholder="Student's Name">
                            </div>

                            <div class="col-2">
                                <button class=" btn btn-secondary fw-bold col-12" onclick="searchStudentAsssignemnt()">Search</button>
                            </div>

                        </div>

                        <hr class=" border border-2 opacity-100 border-success mt-5 mb-0">
                        <div class="row   justify-content-center fw-bold text-center " style="font-size: x-large; color: #FF41E8;  ">
                            <div class="row">
                                <div class="col-2 ">
                                    <Span>Assignment <br>ID</Span>
                                </div>
                                <div class="col-3 justify-content-center">
                                    <span>Student's Name</span>
                                </div>
                                <div class="col-lg-2 ">
                                    <span>Answer Sheet</span>
                                </div>
                                <div class="col-3 text-center px-3">
                                    <span>Marks</span>
                                </div>
                                <div class="col-2">
                                    <span></span>
                                </div>


                            </div>

                        </div>
                        <hr class=" border border-2 opacity-100 border-success mt-0">

                        <div class="row " id="viewArea" style="overflow-y: auto;">
                            <?php

                            $asgnRs = Database::search("SELECT * FROM `student_has_teacher_asgn` 
                                                       INNER JOIN `student` ON `student_has_teacher_asgn`.`student_Sno`=`student`.`Sno`
                                                       WHERE `teacher_Tno`='" . $Tno . "' AND (`teacher_asgn_status_id`='1' AND `student_asgn_status_id`='2' )
                                                       ORDER BY `assignmemt_id` ASC");



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