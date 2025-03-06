<?php
include "connection.php";
$size_id = $_GET["sid"];
$pid = $_GET["pid"];
$price_rs = Database::search("SELECT *  FROM `stock` WHERE `p_size_id`='" . $size_id . "' 
AND `product_id`='" . $pid . "'  ");
$price_date = $price_rs->fetch_assoc();
?>
<div class="col-6 qty-input">
    <input type="number" onchange="QtyPriceChange(<?php echo $price_date['stock_price'] ?>);" 
     id="qty" class="form-control quantity " style="height:100% ; " 
     value="1" min="1" max="<?php echo ($price_date["qty"]); ?>">
</div>
<div class="col-6 " style="padding: 0 0 0 5px; ">
    <span class="fw-bold font-italic text-warning">Quantity Available 
      <?php echo ($price_date["qty"]); ?></span>
</div>