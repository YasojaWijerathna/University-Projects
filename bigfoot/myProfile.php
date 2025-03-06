<?php

session_start();
require "connection.php";

?>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>BigFoot | User Profile</title>

    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="bootstrap.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

    <link rel="icon" href="resources/logo.png">
</head>

<body>

    <div class=" container-fluid" id="page">
        <div class="row">

            <?php include "header.php" ?>

            <?php

            if (isset($_SESSION["u"])) {
                $email = $_SESSION["u"]["email"];
               

                $user_rs = Database::search("SELECT * FROM `user` INNER JOIN `gender` ON `user`.`gender_id`=`gender`.`id` WHERE `email`='" . $email . "'  ");
                $image_rs = Database::search("SELECT * FROM `profile_image` WHERE `user_email`='" . $email . "' ");

                $address_rs = Database::search("SELECT * FROM `user_has_address`
            INNER JOIN `city` ON user_has_address.city_id=city.id
            INNER JOIN `district` ON city.district_id=district.id 
            INNER JOIN `province` ON district.province_id=province.id WHERE `user_email`='" . $email . "' ");


                $user = $user_rs->fetch_assoc();
                $image_details = $image_rs->fetch_assoc();
                $address_details = $address_rs->fetch_assoc();
               
            ?>


                <div class=" col-12 ">
                    <div class=" row">

                        <div class=" col-12 bg-body rounded mt-4 mb-4">
                            <div class="row g-3 d-flex justify-content-center">

                                <div class=" col-lg-5 col-10 profileBox">
                                    <div class=" d-flex flex-column align-items-center text-center p-3 py-5 mt-5">

                                        <?php

                                        if (empty($image_details["path"])) {
                                        ?>
                                            <img src="resources/avatar/avatar1.png" class="rounded mt-5" style="width: 150px;" id="viewImg">

                                        <?php

                                        } else {
                                        ?>

                                            <img src="<?php echo  $image_details["path"]; ?>" class="rounded mt-5" style="width: 150px;" id="viewImg">

                                        <?php
                                        }

                                        ?>


                                        <span class=" fw-bold"><?php echo $user["fname"] . " " . $user["lname"]; ?></span>
                                        <span class="  fw-bold text-black-50"><?php echo $email; ?></span>

                                        <input type="file" class="d-none " id="profileImage">
                                        <label class="btn mt-3" style="background-color:#007bff ; color: white;" for="profileImage" onclick="changeImage();">Update Profile Image</label>

                                    </div>


                                    <div class="card " style="margin-top: 140px;" id="profileCard">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-12">
                                                    <h6 class="mb-0">Full Name :</h6>
                                                </div>
                                                <div class="col-sm-9 text-secondary">
                                                    <?php echo $user["fname"] . " " . $user["lname"]; ?>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-12">
                                                    <h6 class="mb-0">Email :</h6>
                                                </div>
                                                <div class="col-sm-9 text-secondary">
                                                    <?php echo $email; ?>
                                                </div>
                                            </div>
                                            <hr>

                                            <div class="row">
                                                <div class="col-12">
                                                    <h6 class="mb-0">Mobile :</h6>
                                                </div>
                                                <div class="col-sm-9 text-secondary">
                                                    <?php echo $user["mobile"]; ?>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-12">
                                                    <h6 class="mb-0">Address :</h6>
                                                </div>
                                                <div class="col-sm-9 text-secondary">
                                                    <?php
                                                    if (empty($address_details["line1"])) {
                                                    ?>
                                                        <p>No detail</p>
                                                    <?php
                                                    } else {
                                                    ?>
                                                    <p><?php echo $address_details["line1"] . "," . $address_details["line2"] . "," . $address_details["cname"];  ?></p>
                                                    <?php
                                                    }
                                                    ?>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-12">
                                                    <h6 class="mb-0">Registerd Date :</h6>
                                                </div>
                                                <div class="col-sm-9 text-secondary">
                                                    <?php echo date("Y-m-d",strtotime($user["joined_date"])); ?>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-12">
                                                    <a class="btn" style="background-color:#007bff ; color: white;" onclick="editProfile();">Edit Profile</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>

                                <div class=" col-md-7 d-none" id="profileEdit">
                                    <div class=" p-3 py-5">

                                        <div class=" d-flex justify-content-between  align-items-center mb-3">
                                            <h4 class="fw-bold">Profile Settings</h4>
                                        </div>

                                        <div class="row mt-4">

                                            <div class=" col-6">
                                                <label class=" form-label"> First Name</label>
                                                <input type="text" class=" form-control" id="fname" value="<?php echo $user["fname"]; ?>">
                                            </div>

                                            <div class=" col-6">
                                                <label class=" form-label"> Last Name</label>
                                                <input type="text" class=" form-control" id="lname" value="<?php echo $user["lname"]; ?>">
                                            </div>


                                            <div class=" col-12 mt-1">
                                                <label class=" form-label">Mobile</label>
                                                <input type="text" class=" form-control" id="mobile" value="<?php echo $user["mobile"]; ?>">
                                            </div>

                                            <div class=" col-12 mt-1">
                                                <label class=" form-label">Password</label>
                                                <div class="input-group mb-3">
                                                    <input type="password" class=" form-control" id="np" readonly value=" <?php echo $user["password"]; ?>">
                                                    <span class="input-group-text bg-primary" onclick="showPassword();" id="basic-addon2">
                                                        <i class="bi bi-eye text-white"  id="nbp"></i>
                                                    </span>
                                                </div>
                                            </div>

                                            <div class=" col-12 mt-1">
                                                <label class=" form-label">Email</label>
                                                <input type="text" class=" form-control" id="email" readonly value="<?php echo $user["email"]; ?>">
                                            </div>

                                            <div class=" col-12 mt-1">
                                                <label class=" form-label">Redisterd Date</label>
                                                <input type="text" class=" form-control" value=" <?php echo date("Y-m-d",strtotime($user["joined_date"])); ?>"  readonly>
                                            </div>

                                            <?php

                                            if (!empty($address_details["line1"])) {
                                            ?>
                                                <div class=" col-12 mt-1">
                                                    <label class=" form-label">Address line 1</label>
                                                    <input type="text" class=" form-control" id="line1" value="<?php echo $address_details["line1"]; ?>">
                                                </div>

                                            <?php
                                            } else {
                                            ?>
                                                <div class=" col-12 mt-1">
                                                    <label class=" form-label">Address line 1</label>
                                                    <input type="text" id="line1" class="  form-control" >
                                                </div>

                                            <?php
                                            }


                                            ?>

                                            <?php

                                            if (!empty($address_details["line2"])) {
                                            ?>
                                                <div class=" col-12 mt-1">
                                                    <label class=" form-label">Address line 2</label>
                                                    <input type="text" id="line2" class=" form-control" value="<?php echo $address_details["line2"]; ?>">
                                                </div>

                                            <?php
                                            } else {
                                            ?>
                                                <div class=" col-12 mt-1">
                                                    <label class=" form-label">Address line 2</label>
                                                    <input id="line2" type="text" class=" form-control" >
                                                </div>

                                            <?php
                                            }
                                            $province_rs = Database::search("SELECT * FROM `province` ");
                                            $district_rs = Database::search("SELECT * FROM `district` ");
                                            $city_rs = Database::search("SELECT * FROM `city` ");

                                            ?>

                                            <div class=" col-6 mt-1">
                                                <label for="province" class=" form-label">Province</label>
                                                <Select class=" form-select" id="province">
                                                    <option value="0">Select Province</option>

                                                    <?php
                                                    $province_num = $province_rs->num_rows;
                                                    for ($x = 0; $x < $province_num; $x++) {
                                                        $province_data = $province_rs->fetch_assoc();
                                                    ?>

                                                        <option value="<?php echo $province_data["id"]; ?>" <?php
                                                                                                            if (!empty($address_details["province_id"])) {
                                                                                                                if ($province_data["id"] == $address_details["province_id"]) {
                                                                                                            ?> selected <?php
                                                                                                                    }
                                                                                                                }
                                                                                                                        ?>> <?php echo $province_data["pname"] ?></option>

                                                    <?php
                                                    }

                                                    ?>

                                                </Select>
                                            </div>

                                            <div class=" col-6 mt-1">
                                                <label class=" form-label">District</label>
                                                <Select class=" form-select" id="district">
                                                    <option value="0">Select District</option>

                                                    <?php
                                                    $district_num = $district_rs->num_rows;
                                                    for ($x = 0; $x < $district_num; $x++) {
                                                        $district_data = $district_rs->fetch_assoc();
                                                    ?>

                                                        <option value="<?php echo $district_data["id"]; ?>" <?php
                                                                                                            if (!empty($address_details["district_id"])) {
                                                                                                                if ($district_data["id"] == $address_details["district_id"]) {
                                                                                                            ?> selected <?php
                                                                                                                    }
                                                                                                                }
                                                                                                                        ?>> <?php echo $district_data["dname"] ?></option>

                                                    <?php
                                                    }

                                                    ?>
                                                </Select>
                                            </div>

                                            <div class=" col-6 mt-1">
                                                <label class=" form-label">City</label>
                                                <Select class=" form-select" id="city">
                                                    <option value="0">Select City</option>

                                                    <?php
                                                    $city_num = $city_rs->num_rows;
                                                    for ($x = 0; $x < $city_num; $x++) {
                                                        $city_data = $city_rs->fetch_assoc();
                                                    ?>

                                                        <option value="<?php echo $city_data["id"]; ?>" <?php
                                                                                                        if (!empty($address_details["city_id"])) {
                                                                                                            if ($city_data["id"] == $address_details["city_id"]) {
                                                                                                        ?> selected <?php
                                                                                                                }
                                                                                                            }
                                                                                                                    ?>> <?php echo $city_data["cname"] ?></option>
                                                    <?php
                                                    }

                                                    ?>
                                                </Select>
                                            </div>

                                            <?php

                                            if (!empty($address_details["postal_code"])) {
                                            ?>
                                                <div class=" col-6 mt-1">
                                                    <label class=" form-label">Postal Code</label>
                                                    <input type="text" class=" form-control" id="pcode" value="<?php echo $address_details["postal_code"]; ?>">
                                                </div>

                                            <?php
                                            } else {
                                            ?>

                                                <div class=" col-6 mt-1">
                                                    <label class=" form-label">Postal Code</label>
                                                    <input type="text" id="pcode" class=" form-control">
                                                </div>

                                            <?php
                                            }

                                            if (!empty($user["gender_id"])) {
                                            ?>
                                                <div class=" col-12 mt-1">
                                                    <label class=" form-label">Gender</label>
                                                    <input type="text" class=" form-control" readonly value="<?php echo $user["gender_name"]; ?>">
                                                </div>

                                            <?php
                                            } else {
                                            ?>

                                                <div class=" col-12 mt-1">
                                                    <label class=" form-label">Gender</label>
                                                    <input type="text" class=" form-control" readonly>
                                                </div>

                                            <?php
                                            }
                                            ?>





                                            <div class=" col-12 mt-3">
                                                <button type="button" class="btn " style="background-color:#007bff ; color: white;" onclick="updateProfile();">Update Profile</button>
                                            </div>


                                        </div>

                                    </div>
                                </div>

                                <div class=" col-md-6 d-none d-md-block justify-content-center align-content-center" style="margin-left:70px ;" id="Ads">
                                    <div class="row">

                                        <div class="card text-bg-dark" style="padding: 0;">
                                            <img src="images/img_bg_1.jpg" class="" alt="...">
                                            <div class="card-img-overlay">
                                                <div class="container-fluid">
                                                    <div class="row " style="margin-top: 100px; ">
                                                        <div class="col-sm-6 offset-sm-3 text-center ">
                                                            <div class="">
                                                                <div class="description d-non d-lg-block align-items-center">
                                                                    <h1 class="head-1">Men's</h1>
                                                                    <h2 class="head-2">Shoes</h2>
                                                                    <h2 class="head-3">Collection</h2>
                                                                    <p class=""><span>New trending shoes</span></p>
                                                                    <p><a href="#" class="btn btn-primary head-4">Shop Collection</a></p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>`

                                    </div>

                                    <div class="row">

                                        <div class="card text-bg-dark" style="padding: 0;">
                                            <img src="images/women.jpg" style="width: 100%;" class="" alt="...">
                                            <div class="card-img-overlay">
                                                <div class="container-fluid">
                                                    <div class="row " style="margin-top: 100px; ">
                                                        <div class="col-sm-6 offset-sm-3 text-center ">
                                                            <div class="">
                                                                <div class="desc">
                                                                    <h1 class="head-1">Women's</h1>
                                                                    <h2 class="head-2">Shoes</h2>
                                                                    <h2 class="head-3">Collection</h2>
                                                                    <p class="category"><span>New trending shoes</span></p>
                                                                    <p><a href="#" class="btn btn-primary head-4">Shop Collection</a></p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>`

                                    </div>

                                </div>

                            </div>
                        </div>

                    </div>
                </div>

            <?php
            } else {
                header("Location:index.php");
            }

            ?>



            <?php include "footer.php" ?>
        </div>
    </div>



    <script src="script.js"></script>
    <script src="bootstrap.bundle.js"></script>
</body>

</html>