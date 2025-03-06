<?php



require "connection.php";

$email = $_POST["e"];
$np = $_POST["n"];
$rnp = $_POST["r"];
$vcode = $_POST["v"];
$user = $_POST["u"];

if (empty($email)) {
    echo ("Missing Email Address");
} else if (empty($np)) {
    echo ("Please type a New Password");
} else if (strlen($np) < 5 || strlen($np) > 20) {
    echo ("Invalid New Password");
} else if (empty($rnp)) {
    echo ("Please Retype your Password");
} else if ($np != $rnp) {
    echo ("Password does not matched");
} else if (empty($vcode)) {
    echo ("Please enter your Verification Code");
} else {

    $query = "";
    if ($user == "teacher") {
        $query = "SELECT * FROM `teacher` WHERE `email` = '" . $email . "' ";
    } elseif ($user == "academic") {
        $query = "SELECT * FROM `academic_officer` WHERE `email` = '" . $email . "' ";
    } else if ($user == "student") {
        $query = "SELECT * FROM `student` WHERE `email` = '" . $email . "'  ";
    }

    $rs = Database::search($query);
    $data = $rs->fetch_assoc();


    if ($data["otp"] == $vcode) {
        
    
        if($user=="teacher"){
           
            Database::iud(" UPDATE `teacher` SET `password`='".$np."' WHERE `email` = '" . $email . "' ");
    
        }elseif ($user=="academic"){
           
            Database::iud("UPDATE `academic_officer` SET `password`='".$np."' WHERE `email` = '" . $email . "' ");
    
        }else if($user=="student"){
            
            Database::iud("UPDATE `student` SET `password`='".$np."' WHERE `email` = '" . $email . "'  ");
                    
        }
    
        echo "success";

        if ($user == "teacher") {
            Database::iud(" UPDATE `teacher` SET `otp`='' WHERE `email` = '" . $email . "' ");
        } elseif ($user == "academic") {
            Database::iud("UPDATE `academic_officer` SET `otp`='' WHERE `email` = '" . $email . "' ");
        } else if ($user == "student") {
            Database::iud("UPDATE `student` SET `otp`='' WHERE `email` = '" . $email . "'  ");
        }
    
    } else {
        echo ("Invalid Email or Verification Code");
    }
        
}
