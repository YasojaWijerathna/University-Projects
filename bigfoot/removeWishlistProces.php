<?php
require "connection.php";
if(isset($_GET["id"])){
    $wish_id=$_GET["id"];
    $wish_rs=Database::search("SELECT * FROM `wishlist` WHERE `id`='".$wish_id."' ");
    $wish_num=$wish_rs->num_rows;
    $wish_data=$wish_rs->fetch_assoc();
    if($wish_num==0){
        echo ("Something went wrong");
    }else{
        Database::iud("DELETE FROM `wishlist` WHERE `id`='".$wish_id."' ");
        echo("success"); 
    }
}else{
    echo "Please Select a Product";
}
?>