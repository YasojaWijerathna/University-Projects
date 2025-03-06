<?php

session_start();

require "connection.php";
$user = $_GET["user"];

if (isset($user)) {

    $cityRS = Database::search("SELECT * FROM `city`");
    $genderRS = Database::search("SELECT * FROM `gender`");
    $subjectRs = Database::search("SELECT * FROM `subject`");
    $gradeRs = Database::search("SELECT * FROM `grade`");

    if ($user == "student") {
        $Sno = $_SESSION["student"]["Sno"];
    } else if ($user == "teacher") {
        $Tno = $_SESSION["teacher"]["Tno"];
    } else if ($user == "academic") {
        $Ano = $_SESSION["academic"]["Ano"];
    }



?>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Update Profile </title>

        <link rel="stylesheet" href="bootstrap.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
        <link rel="stylesheet" href="style.css" />

        <link rel="icon" href="resources/images/logo.jpg" />
    </head>

    <label class="d-none" id="user"><?php echo ($user) ?></label>

    <?php

    if ($user == "student") {
    ?>
    <label class="d-none" id="userNo"><?php echo ($Sno) ?></label>
    <?php
    } else if ($user == "teacher") {
    ?>
    <label class="d-none" id="userNo"><?php echo ($Tno) ?></label>
    <?php
    } else if ($user == "academic") {
    ?>
    <label class="d-none" id="userNo"><?php echo ($Tno) ?></label>
    <?php
    }

    ?>

    <body class="main_body2">
        <div class="container-fluid">
            <div class="row justify-content-center">


                <div class="col-12 col-lg-11 ">
                    <!-- Main UI -->
                    <div class="row  ">


                        <div class=" col-md-3 border-end ">
                            <div class=" d-flex flex-column align-items-center text-center p-3 py-5">

                                <?php

                                $data;
                                if ($user == "student") {

                                    $detailsRS = Database::search("SELECT * FROM `student` INNER JOIN `st_profile` ON `student`.`Sno`=`st_profile`.`student_Sno`
                                                                    INNER JOIN `gender` ON `gender`.`id`=`student`.`gender_id`
                                                               WHERE `Sno`='" . $Sno . "' ");
                                    $data = $detailsRS->fetch_assoc();
                                } else if ($user == "teacher") {

                                    $detailsRS = Database::search("SELECT * FROM `teacher` INNER JOIN  `t_profile` ON `teacher`.`Tno`=`t_profile`.`teacher_Tno`
                                                                    INNER JOIN `gender` ON `gender`.`id`=`teacher`.`gender_id`
                                                                   WHERE `teacher_Tno`='" . $Tno . "' ");
                                    $data = $detailsRS->fetch_assoc();
                                } else if ($user == "academic") {

                                    $detailsRS = Database::search("SELECT * FROM `academic_officer` INNER JOIN `ac_profile` ON `academic_officer`.`Ano`=`ac_profile`.`academic_officer_Ano`
                                                                    INNER JOIN `gender` ON `gender`.`id`=`teacher`.`gender_id`
                                                                    WHERE `academic_officer_Ano`='" . $Ano . "' ");
                                    $data = $detailsRS->fetch_assoc();
                                }

                                ?>

                                <div class="col-10" style=" margin-top: 200px;">

                                    <img src="<?php echo ($data["img_path"]) ?>" id="viewImg" style=" width: 150px; border-radius: 90px; margin-right: 1px; " class=" mt-3 " alt="">

                                </div>

                                <input type="file" class="d-none " id="profileImage">
                                <label class="btn text-light mt-3" for="profileImage" style="background-color: #BB42EA;" onclick="changeImage();">Update Profile Image</label>

                            </div>
                        </div>

                        <div class="border-end col-md-9">
                            <div class=" p-3 py-5">

                                <div class=" d-flex justify-content-between  align-items-center mb-3">
                                    <h4 class="fw-bold">Profile Settings</h4>
                                </div>

                                <div class="row mt-4">

                                    <div class="row mt-0">
                                        <div class=" col-6">
                                            <div class="row">

                                                <?php
                                                if ($user == "student") {
                                                ?>
                                                    <div class="col-3 mt-2 ">
                                                        <span class=" form-label">Student No</span>
                                                    </div>
                                                    <div class="col-3 p-0">
                                                        <input type="text" class=" form-control" id="sno" readonly value="<?php echo ($Sno) ?>">
                                                    </div>
                                                <?php
                                                } else if ($user == "teacher") {
                                                ?>
                                                    <div class="col-3 mt-2 ">
                                                        <span class=" form-label">Teacher No</span>
                                                    </div>
                                                    <div class="col-3 p-0">
                                                        <input type="text" class=" form-control" id="tno" readonly value="<?php echo ($Tno) ?>">
                                                    </div>
                                                <?php
                                                } else if ($user == "academic") {
                                                ?>
                                                    <div class="col-3 mt-2 ">
                                                        <span class=" form-label">Academic Officer No</span>
                                                    </div>
                                                    <div class="col-3 p-0">
                                                        <input type="text" class=" form-control" id="ano" readonly value="<?php echo ($Ano) ?>">
                                                    </div>
                                                <?php
                                                }
                                                ?>

                                            </div>
                                        </div>

                                    </div>

                                    <div class="row mt-3 ">
                                        <div class=" col-6">
                                            <label class=" form-label"> First Name</label>
                                            <input type="text" class=" form-control" id="fname" value="<?php echo ($data["firstName"]) ?>">
                                        </div>

                                        <div class=" col-6">
                                            <label class=" form-label"> Last Name</label>
                                            <input type="text" class=" form-control" id="lname" value="<?php echo ($data["lastName"]) ?>">
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class=" col-12 mt-1">
                                            <label class=" form-label">Username/Email</label>
                                            <input type="text" class=" form-control" id="email" value="<?php echo ($data["email"]) ?>">
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class=" col-12 mt-1">
                                            <label class=" form-label">Password</label>
                                            <div class="input-group mb-3">
                                                <input type="password" class=" form-control" id="np" value="<?php echo ($data["password"]) ?>">
                                                <span class="input-group-text bg-primary" id="basic-addon2">
                                                    <i class=" bi bi-eye-slash-fill text-white " id="picon" onclick="showPasswordEye();" id="nbp"></i>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mt-1">

                                        <?php
                                        if ($user == "student") {
                                        ?>
                                            <div class="col-6">
                                                <label class=" form-label">Date of Birth</label>
                                                <input type="date" id="dob" class="form-control" readonly value="<?php echo ($data["DOB"]) ?>">
                                            </div>
                                        <?php
                                        } 
                                        ?>

                                        <div class=" col-6  ">

                                            <label class="d-none" id="oldGrade"><?php echo $grade["id"] ?></label>


                                            <label class=" form-label">Grade</label>
                                            <select name="" id="grade" class="form-select">
                                                <option value="0"><?php echo( $data["grade_id"]) ?></option>
                                                
                                            </select>
                                        </div>

                                    </div>

                                    <div class="row mt-2" id="teacherRow">

                                        <?php
                                        $gednder = $genderRS->fetch_assoc();

                                        ?>

                                        <div class=" col-6 ">
                                            <label class=" form-label">Gender</label>
                                            <select name="" id="gender" class="form-select">
                                                <option value=""><?php echo $data["type"] ?></option>
                                            </select>
                                        </div>

                                        <?php
                                        if ($user == "student") {
                                        ?>
                                            <div class="col-6">
                                                <label class=" form-label">Mobile</label>
                                                <input type="text" id="mobile" class="form-control" value="<?php echo ($data["mobile"]) ?>">
                                            </div>

                                        <?php
                                        }
                                        ?>


                                    </div>


                                    <div class="row">
                                        <div class=" col-12 mt-3">
                                            <label class=" form-label">Address line 1</label>
                                            <input type="text" class=" form-control" id="line1" value="<?php echo ($data["line1"]) ?>">
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class=" col-12 mt-1">
                                            <label class=" form-label">Address line 2</label>
                                            <input type="text" class=" form-control" id="line2" value="<?php echo ($data["line2"]) ?>">
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
                                                <option value="<?php echo $cityData["id"] ?>" <?php
                                                                                                if ($cityData["id"] == $data["city_id"]) {
                                                                                                ?> selected <?php
                                                                                                        }
                                                                                                            ?>><?php echo $cityData["cname"] ?></option>
                                            <?php
                                            }
                                            ?>
                                        </Select>
                                    </div>




                                    <div class="row mt-4 justify-content-center">
                                        <div class=" col-5 mt-3">
                                            <button class="btn btn-primary col-12" onclick="updateProfile();">Update My Profile</button>
                                        </div>

                                    </div>

                                </div>

                            </div>
                        </div>


                    </div>
                    <!-- Main UI -->
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