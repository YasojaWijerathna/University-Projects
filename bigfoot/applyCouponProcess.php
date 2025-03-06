<?php
include "connection.php";
$coupon = $_POST["coupon"];
$total = $_POST["total"];
$cart_num = $_POST["cart"];
$rs = Database::search("SELECT * FROM `discount` 
WHERE `coupon`='".$coupon."' ");
$num = $rs->num_rows;
if ($num == 1) {
    $discountData = $rs->fetch_assoc();
    $percentage = 0;
    $discount = 0;
    if (!empty($discountData["percentage"])) {
        $percentage = $discountData["percentage"];
        $discount = $total * ($percentage / 100);
    } else if (!empty($discountData["value"])) {
        $discount = $discountData["value"];
    }
    $netTotal=$total-$discount;
?>
    <div class="sub">
        <div class="row">
            <div class="col-6 text-left">
                <p>Subtotal:</p>
                <p>No. of Products :</p>
                <p>Discount:</p>
            </div>
            <div class="col-6 text-center">
                <p>Rs.<?php echo $total ?>.00</p>
                <p><?php echo $cart_num ?></p>
                <p>Rs.<?php echo $discount ?>.00</p>
                <p class="d-none" id="discount"><?php echo $discount ?></p>
            </div>
        </div>
    </div>
    <div class="grand-total text-center">
        <div class="row">
            <div class="col-6 text-left">
                <p>Total :</p>
            </div>
            <div class="col-6 text-center">
                <p>Rs.<?php echo $netTotal ?>.00</p>
            </div>
        </div>
    </div>
<?php
} else {
    echo "Invalid";
}
