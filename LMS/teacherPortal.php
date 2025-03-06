<?php

session_start();

require "connection.php";

if (isset($_SESSION["teacher"])) {
    $Tno = $_SESSION['teacher']['Tno'];

?>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Teacher Portal </title>

        <link rel="stylesheet" href="bootstrap.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
        <link rel="stylesheet" href="style.css" />

        <link rel="icon" href="resources/images/logo.jpg" />
    </head>

    <body class="main_body2">

        <label class="d-none" id="user">teacher</label>
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

                    <div class="row" style=" background-color: #E1E0E4; height:25vmax; overflow-y:hidden ;">
                        <div class="col-12 align-items-start">
                            <div class="row g-1">
                                <div class="row g-1 text-center  ">

                                    <div class="nav flex-column nav-pills me-3 mt-3" role="tablist" aria-orientation="vertical">
                                        <nav class="nav flex-column">
                                            <a class="nav-link active border border-dark" aria-current="page" href="teacherPortal.php">Dashboard</a>
                                            <a class="nav-link border border-dark mt-2" href="assignmentMarks_View.php">Assignmetnt View & <br>Submit Marks</a>

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

                        <div class="text-dark fw-bold mb-1 mt-3">
                            <h1 class="fw-bold text-warning">Dashboard</h1>
                        </div>
                        <div class="col-12">
                            <hr />
                        </div>

                        <div class="col-12 col-lg-5 container-fluid border border-2 border-opacity-100 border-secondary rounded-2">
                            <div class="row align-items-center justify-content-center">

                                <div class="col-12">
                                    <h3>Add Lesson Notes </h3>
                                    <hr class="border border-1 border-secondary border-opacity-100">
                                </div>

                                <div class="row justify-content-center">

                                    <div class="col-6 ms-5 justify-content-center">
                                        <input type="file" class="d-none" onchange="fileNameChange1();" id="addNote">
                                        <label for="addNote">
                                            <img src="resources/images/add-file.png" height="180px">
                                        </label>
                                        <label for="" class="form-label d-none ms-3" id="fileName1"></label>
                                    </div>

                                </div>

                                <div class="row mt-4 ms-5 justify-content-center mb-3">

                                    <div class="col-6 ms-1">
                                        <button class="btn btn-warning" onclick="uploadNotes();">Upload Notes</button>
                                    </div>

                                </div>

                            </div>
                        </div>

                        <div class="col-12 col-lg-5 container-fluid border border-2 border-opacity-100 border-secondary rounded-2">
                            <div class="row align-items-center justify-content-center">

                                <div class="col-12">
                                    <h3>Add New Assignments</h3>
                                    <hr class="border border-1 border-secondary border-opacity-100">
                                </div>

                                <div class="row justify-content-center">

                                    <div class="col-6 ms-5 justify-content-center">
                                        <input type="file" class="d-none" onchange="fileNameChange2();" id="addAsgn">
                                        <label for="addAsgn">
                                            <img src="resources/images/add-file.png" height="180px">
                                        </label>
                                        <label for="" class="form-label d-none ms-3" id="fileName2"></label>
                                    </div>

                                </div>

                                <div class="row mt-4 ms-3 justify-content-center mb-3">

                                    <div class="col-6 ms-2">
                                        <label for="" class=" d-none " id="Tno"><?php echo ($Tno); ?></label>
                                        <button class="btn btn-warning" onclick="uploadAssignments();">Upload Assignments</button>
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
    </body>

    </html>

<?php

} else {
    echo ("You are Not a valid user");
}

?>