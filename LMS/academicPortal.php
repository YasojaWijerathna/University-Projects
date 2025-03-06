<?php

session_start();

require "connection.php";

if (isset($_SESSION["academic"])) {
    $Ano=$_SESSION["academic"]["Ano"];
    $cityRS = Database::search("SELECT * FROM `city`");
    $genderRS = Database::search("SELECT * FROM `gender`");
    $subjectRs = Database::search("SELECT * FROM `subject`");
    $gradeRs = Database::search("SELECT * FROM `grade`");
?>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Academic Officer Portal </title>

        <link rel="stylesheet" href="bootstrap.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
        <link rel="stylesheet" href="style.css" />

        <link rel="icon" href="resources/images/logo.jpg" />
    </head>



    <body class="main_body2">
        <label class="d-none" id="user">academic</label>
        <div class="container-fluid">
            <div class="row">

                <div class="col-12 col-lg-2 container-fluid border-end">
                    <div class="row ">
                        <div class="col-12 align-items-start   ">
                            <div class="row g-1 text-center justify-content-center ">

                                <?php
                                $imgRs = Database::search("SELECT * FROM `ac_profile` WHERE `academic_officer_Ano`='" .$Ano . "' ");
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
                                            <a class="nav-link active  border border-dark" aria-current="page" href="adminPanel.php">Registrations</a>
                                            <a class="nav-link border border-dark mt-2" href="academicMarksView.php">View & Release <br> Assignment Marks</a>
                                        </nav>
                                    </div>


                                </div>

                            </div>
                        </div>
                    </div>
                </div>



                <div class="col-12 col-lg-10 ">
                    <!-- Registration UI -->
                    <div class="row  ">

                        <div class="text-dark fw-bold mb-1 mt-3">
                            <h2 class="fw-bold text-warning">Register Students</h2>
                        </div>
                        <div class="col-12">
                            <hr />
                        </div>
                        <div class=" col-11 ms-5 mx-2 " id="profileEdit">
                            <div class=" p-3 ">

                                <div class="row mt-0">

                                    <div class="row mt-3">
                                        <div class=" col-6">
                                            <label class=" form-label"> First Name</label>
                                            <input type="text" class=" form-control" id="fname" placeholder="Kevin">
                                        </div>

                                        <div class=" col-6">
                                            <label class=" form-label"> Last Name</label>
                                            <input type="text" class=" form-control" id="lname" placeholder="Hart">
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class=" col-12 mt-1">
                                            <label class=" form-label">Username/Email</label>
                                            <input type="text" class=" form-control" id="email" placeholder="abc@gmail.com">
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class=" col-12 mt-1">
                                            <label class=" form-label">Password</label>
                                            <div class="input-group mb-3">
                                                <input type="password" class=" form-control" id="np" placeholder="*********">
                                                <span class="input-group-text bg-primary" id="basic-addon2">
                                                    <i class=" bi bi-eye-slash-fill text-white " id="picon" onclick="showPasswordEye();" id="nbp"></i>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mt-1">
                                        <div class="col-6">
                                            <label class=" form-label">Date of Birth</label>
                                            <input type="date" id="dob" class="form-control">
                                        </div>



                                        <div class=" col-6  ">
                                            <label class=" form-label">Grade</label>
                                            <select name="" id="grade" class="form-select">
                                                <option value="0">Select Grade</option>
                                                <?php

                                                $gradeRs = Database::search("SELECT * FROM `grade`");
                                                $gradeNum = $gradeRs->num_rows;

                                                for ($x = 0; $x < $gradeNum; $x++) {
                                                    $grade = $gradeRs->fetch_assoc();
                                                ?>
                                                    <option value="<?php echo $grade["id"] ?>"><?php echo $grade["grade"] ?></option>

                                                <?php
                                                }
                                                ?>
                                            </select>
                                        </div>

                                    </div>

                                    <div class="row mt-2" id="teacherRow">

                                        <div class=" col-6 ">
                                            <label class=" form-label">Gender</label>
                                            <select name="" id="gender" class="form-select">
                                                <option value="">Select Gender</option>
                                                <?php

                                                $genderNum = $genderRS->num_rows;

                                                for ($x = 0; $x < $genderNum; $x++) {
                                                    $gednder = $genderRS->fetch_assoc();
                                                ?>
                                                    <option value="<?php echo $gednder["id"] ?>"><?php echo $gednder["type"] ?></option>

                                                <?php
                                                }
                                                ?>
                                            </select>
                                        </div>

                                        <div class="col-6">
                                            <label class=" form-label">Mobile</label>
                                            <input type="text" id="mobile" class="form-control" placeholder="07********">
                                        </div>

                                    </div>


                                    <div class="row">
                                        <div class=" col-12 mt-3">
                                            <label class=" form-label">Address line 1</label>
                                            <input type="text" class=" form-control" id="line1">
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class=" col-12 mt-1">
                                            <label class=" form-label">Address line 2</label>
                                            <input type="text" class=" form-control" id="line2">
                                        </div>
                                    </div>

                                    <div clas></div>
                                    <div class=" col-6 mt-1">
                                        <label class=" form-label">City</label>
                                        <Select class=" form-select" id="city">
                                            <option value="0">Select City</option>
                                            <?php

                                            $cityNum = $cityRS->num_rows;
                                            for ($y = 0; $y < $cityNum; $y++) {
                                                $cityData = $cityRS->fetch_assoc();
                                            ?>
                                                <option value="<?php echo $cityData["id"] ?>"><?php echo $cityData["cname"] ?></option>
                                            <?php
                                            }
                                            ?>
                                        </Select>
                                    </div>
                                </div>






                                <div class="row  mt-5 justify-content-center">
                                    <div class=" col-3 ms-5     ">
                                        <button type="button" class="btn " style="background-color:#007bff ; color: white;" onclick="studentRegistration();">Register % Invite </button>
                                    </div>
                                </div>




                            </div>

                        </div>
                    </div>
                    <!-- Registration UI -->




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