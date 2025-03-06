<?php

session_start();

require "connection.php";

if (isset($_SESSION["admin"])) {

?>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Manage Academic Officers | Admin </title>

        <link rel="stylesheet" href="bootstrap.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
        <link rel="stylesheet" href="style.css" />

        <link rel="icon" href="resources/images/logo.jpg" />
    </head>

    <body class="main_body2  ">
        <label class="d-none" id="user">admin</label>
        <div class="container-fluid">
            <div class="row">

                <div class="col-12 col-lg-2 container-fluid border-end">
                    <div class="row ">
                        <div class="col-12 align-items-start   ">
                            <div class="row g-1 text-center justify-content-center ">

                                <?php
                                $imgRs = Database::search("SELECT * FROM `admin_profile` WHERE `admin_email`='" . $_SESSION["admin"]["email"] . "' ");
                                $img = $imgRs->fetch_assoc();
                                ?>
                                <div class="col-10">

                                    <img src="<?php echo ($img["img_path"]) ?>" style=" width: 120px; border-radius: 90px; margin-right: 1px; " class=" mt-3 " alt="">

                                </div>



                                <div class="col-12 mt-2">

                                    <h4 class="text-primary      fs-4   "><?php echo $_SESSION["admin"]["firstName"] . " " . $_SESSION["admin"]["lastName"]; ?></h4>

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
                                            <a class="nav-link  border border-dark" aria-current="page" href="adminPanel.php">Teacher & Academic <br>Registrations</a>
                                            <a class="nav-link border border-dark mt-2" href="studentManaging.php">Manage Students</a>
                                            <a class="nav-link border border-dark mt-2" href="teacherManaging.php">Manage Teachers</a>
                                            <a class="nav-link active border border-dark mt-2" href="academicManaging.php">Manage Academic Officers</a>
                                            <a class="nav-link border border-dark mt-2" href="checkResults.php">Check Results</a>
                                        </nav>
                                    </div>


                                </div>

                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-12 col-lg-10">

                    <div class="row ">

                        <div class="col-12 text-center">
                            <label class="form-label text-danger text-decoration-underline fw-bold fs-1">Manage Academic Officers</label>

                        </div>

                        <div class="row  justify-content-center">

                            <div class="col-2">
                                <input type="text" id="acno" class="form-control" placeholder="Teacher No">
                            </div>

                            <div class="col-5">
                                <input type="text" id="acname" class=" form-control" placeholder="Username/Emali">
                            </div>

                            <div class="col-2">
                                <button class=" btn btn-secondary fw-bold col-12" onclick="searchAcademic()">Search</button>
                            </div>

                        </div>

                        <hr class=" border border-2 opacity-100 border-success mt-5 mb-0">
                        <div class="row   justify-content-center fw-bold text-center " style="font-size: x-large; color: #FF41E8;  ">
                            <div class="row">
                                <div class="col-1 ">
                                    <Span>Ano</Span>
                                </div>
                                <div class="col-3 justify-content-center">
                                    <span>Academic Officer's Name</span>
                                </div>
                                <div class="col-lg-4 ">
                                    <span>Email</span>
                                </div>

                                <div class="col-2 textce">
                                    <span>Grade</span>
                                </div>
                                <div class="col-2">
                                    <span>Status</span>
                                </div>
                            </div>

                        </div>
                        <hr class=" border border-2 opacity-100 border-success mt-0">

                        <div class="row " id="viewArea">
                            <?php

                            $academicRs = Database::search("SELECT * FROM `academic_officer` 
                                                       INNER JOIN `grade` ON `academic_officer`.`grade_id`=`grade`.`id`
                                                       INNER JOIN `status` ON `academic_officer`.`status_id`=`status`.`id`  
                                                       ORDER BY `Ano`");

                            $academicNum = $academicRs->num_rows;

                            for ($x = 0; $x < $academicNum; $x++) {
                                $academic = $academicRs->fetch_assoc();
                            ?>
                                <div class="row  justify-content-center  text-center mt-0 mb-2  fs-5  " style=" color: #F66D08;  ">
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