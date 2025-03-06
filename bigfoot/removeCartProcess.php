<?php
require "connection.php";
if(isset($_GET["id"])){
    $cid=$_GET["id"];
    Database::iud("DELETE FROM `cart` WHERE `id`='".$cid."' ");
    echo "Product has been Romoved";
}else{
    echo "Something Went Wrong";
}
?>