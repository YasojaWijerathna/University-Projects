<?php

include "connection.php";

$districtId = $_POST["did"];
$subTotal = $_POST["subtotal"];
$discount = $_POST["discount"];
$delivary_Colombo = 250;
$delivary_other = 400;

if ($districtId == 1) {
    $total=(int)$subTotal+$delivary_Colombo-(int)$discount;
?>
    <li><span>Subtotal</span> <span class="d-none" id="subtotal"><?php echo ($subTotal); ?>
        </span><span class="float-end">Rs.<?php echo ($subTotal); ?>.00</span></li>

    <li><span>Discount</span><span class="d-none" id="discount"><?php echo ($discount); ?></span>
            <span class="float-end">Rs.<?php echo ($discount); ?>.00</span></li>

    <li><span>Shipping</span><span class="d-none" id="shipping"><?php echo ($$delivary_Colombo); ?></span>
    <span id="shippingCost" class="float-end">Rs.<?php echo ($delivary_Colombo) ?>.00</span></li></li>

    <li><span>Order Total</span> <span class="d-none" id="total"><?php echo ($total); ?></span>
            <span class="float-end">Rs.<?php echo ($total); ?>.00</span></li>
    
<?php
} else {
    $total=(int)$subTotal+$delivary_other-(int)$discount;
?>
    <li><span>Subtotal</span> <span class="d-none" id="subtotal"><?php echo ($subTotal); ?></span> 
    <span class="float-end">Rs.<?php echo ($subTotal); ?>.00</span></li>

    <li><span>Discount</span><span class="d-none" id="discount"><?php echo ($discount); ?></span>
            <span class="float-end">Rs.<?php echo ($discount); ?>.00</span></li>

    <li><span>Shipping</span><span class="d-none" id="shipping"><?php echo ($delivary_other); ?></span>
    <span id="shippingCost" class="float-end">Rs.<?php echo ($delivary_other) ?>.00</span></li></li>

    <li><span>Order Total</span> <span class="d-none" id="total"><?php echo ($total); ?></span>
            <span class="float-end">Rs.<?php echo ($total); ?>.00</span></li>
<?php
}
