<?php
include "connection.php";
$mobile=$_GET["mobile"];
$user_rs = Database::search("SELECT * FROM `user` WHERE `mobile`='0".$mobile."' ");
$user_data=$user_rs->fetch_assoc();
if($user_data["status_id"]==1){
    Database::iud("UPDATE `user` SET `status_id`='2' WHERE `mobile`='0".$mobile."' ");
    echo "success";
}else if($user_data["status_id"]==2){
    Database::iud("UPDATE `user` SET `status_id`='1' WHERE `mobile`='0".$mobile."'");
    echo "success";
}
