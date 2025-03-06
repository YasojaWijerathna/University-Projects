<h4 class="text-info">Available Sizes</h4>
<ul>
<?php
include "connection.php";

$size_id = $_GET["sid"];
$pid=$_GET["pid"];

$size_rs = Database::search("SELECT * FROM `stock` INNER JOIN `p_size`
 ON `stock`.`p_size_id`=`p_size`.`id`  WHERE `product_id`='" . $pid . "'  ");
$size_num = $size_rs->num_rows;


for ($x = 0; $x < $size_num; $x++) {
    $size_data = $size_rs->fetch_assoc();
    $size[$x] = $size_data["size"];
    if ($size_id == $size_data["id"]) {
?>
        <li class="bg-primary"><a href="#" id="<?php echo $size_data["id"]; ?>" 
        onclick="sizeSelect(<?php echo ($size_data['id']) ?>,<?php echo ($pid) ?>);">
        <?php echo $size[$x]; ?></a></li>
    <?php
    } else {
    ?>
    <li class=""><a href="#" id="<?php echo $size_data["id"]; ?>" 
    onclick="sizeSelect(<?php echo ($size_data['id']) ?>,<?php echo ($pid) ?>);">
    <?php echo $size[$x]; ?></a></li>
<?php
    }
}
?>
</ul>