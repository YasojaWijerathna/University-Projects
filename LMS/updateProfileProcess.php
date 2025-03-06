<?php


session_start();
require "connection.php";

if (isset($_POST["u"])) {

    $user = $_POST["u"];
    $fname = $_POST["fn"];
    $lname = $_POST["ln"];
    $email = $_POST["e"];
    $password = $_POST["p"];

    $line1 = $_POST["l1"];
    $line2 = $_POST["l2"];
    $city = $_POST["c"];

    $Ano;
    $Tno;
    $mobile;
    $dob;
    $Sno;
    if ($user == "student") {
        $mobile = $_POST["m"];
        $dob = $_POST["dob"];
        $Sno = $_POST["Sno"];
    } else if ($user == "teacher") {
        $Tno = $_POST["Tno"];
    } else if ($user == "academic") {
        $Ano = $_POST["Ano"];
    }


    if ($user == "student") {
        Database::iud("UPDATE `student` SET `firstName`='" . $fname . "',`lastName`='" . $lname . "',`email`='" . $email . "',`password`='" . $password . "', 
                        `mobile`='" . $mobile . "',`DOB`='" . $dob . "' WHERE `Sno` ='" . $Sno . "' ");
    } else if ($user == "teacher") {

        Database::iud("UPDATE `teacher` SET `firstName`='" . $fname . "',`lastName`='" . $lname . "',`email`='" . $email . "',`password`='" . $password . "'
                       WHERE `Tno` ='" . $Tno . "' ");
    } else if ($user == "academic") {

        Database::iud("UPDATE `academic_officer` SET `firstName`='" . $fname . "',`lastName`='" . $lname . "',`email`='" . $email . "',`password`='" . $password . "'
                       WHERE `Ano` ='" . $Ano . "' ");
    }




    if (isset($_FILES["image"])) {

        $image = $_FILES["image"];

        $allowed_img_extentions = array("image/jpg", "image/jpeg", "image/png", "image/svg+xml");

        $file_ex = $image["type"];

        if (!in_array($file_ex, $allowed_img_extentions)) {
            echo "Please select a valid file type";
        } else {
            $new_file_extention;

            if ($file_ex == "image/jpg") {
                $new_file_extention = ".jpg";
            } elseif ($file_ex == "image/jpeg") {
                $new_file_extention = ".jpeg";
            } elseif ($file_ex == "image/png") {
                $new_file_extention = ".png";
            } elseif ($file_ex == "image/svg+xml") {
                $new_file_extention = ".svg";
            }

            $file_name = "LMS_files_directory/profile_img/" . $fname . "_profileImg".uniqid() . $new_file_extention;

            move_uploaded_file($image["tmp_name"], $file_name);


            if ($user == "student") {
                Database::iud("UPDATE `st_profile` SET `line1`='" . $line1 . "',`line2`='" . $line2 . "',`city_id`='" . $city . "',`img_path`='" . $file_name . "' WHERE `student_Sno` ='" . $Sno . "' ");
            } else if ($user == "teacher") {

                Database::iud("UPDATE `t_profile` SET `line1`='" . $line1 . "',`line2`='" . $line2 . "',`city_id`='" . $city . "',`img_path`='" . $file_name . "' WHERE `teacher_Tno` ='" . $Tno . "' ");
            } else if ($user == "academic") {

                Database::iud("UPDATE `ac_profile` SET `line1`='" . $line1 . "',`line2`='" . $line2 . "',`city_id`='" . $city . "',`img_path`='" . $file_name . "' WHERE `academic_officer_Ano` ='" . $Sno . "' ");
            }
        }
    } else {

        if ($user == "student") {
            Database::iud("UPDATE `st_profile` SET `line1`='" . $line1 . "',`line2`='" . $line2 . "',`city_id`='" . $city . "' WHERE `student_Sno` ='" . $Sno . "' ");
        } else if ($user == "teacher") {

            Database::iud("UPDATE `t_profile` SET `line1`='" . $line1 . "',`line2`='" . $line2 . "',`city_id`='" . $city . "' WHERE `teacher_Tno` ='" . $Tno . "' ");
        } else if ($user == "academic") {

            Database::iud("UPDATE `ac_profile` SET `line1`='" . $line1 . "',`line2`='" . $line2 . "',`city_id`='" . $city . "' WHERE `academic_officer_Ano` ='" . $Sno . "' ");
        }
    }

    echo ("success");
}
