<?php
session_start();
include "connection.php";


if (isset($_SESSION["u"])) {

    $page =  $_POST["page"];
    $num =  $_POST["num"];
    $discount =  $_POST["discount"];
    $qty_array = json_decode($_POST["qty"]);


    if ($page == "singleProduct") {
        $size_id =  $_POST["size_id"];
        $pid =  $_POST["pid"];
    } else if ($page == "cart") {
        $stock_no_array = json_decode($_POST["stock_no"]);
    }

    $stock_no;
    $qty;
    $stock_price;
    $product_name;

?>
    <div class="shipping-cart p-4">
        <h2>Cart Total</h2>
        <ul class="mb-3">

            <?php

            $subTotal = 0;
            for ($x = 0; $x < $num; $x++) {
                $stock_data;


                if ($page == "singleProduct") {
                    $size_id =  $_POST["size_id"];
                    $pid =  $_POST["pid"];

                    $stock_rs = Database::search("SELECT * FROM `stock` INNER JOIN `product` ON `stock`.`product_id`=`product`.`id` 
                    INNER JOIN `p_size` ON `stock`.`p_size_id`=`p_size`.`id`  WHERE `product_id`='" . $pid . "' AND `p_size_id`='" . $size_id . "' ");
                    $stock_data = $stock_rs->fetch_assoc();

                    $stock_no = $stock_data["stock_no"];
                  
                } else if ($page == "cart") {
                    $stock_no = $stock_no_array[$x];

                    $stock_rs = Database::search("SELECT * FROM `stock` INNER JOIN `product` ON `stock`.`product_id`=`product`.`id` 
                    INNER JOIN `p_size` ON `stock`.`p_size_id`=`p_size`.`id`  WHERE `stock_no`='" . $stock_no . "' ");
                    $stock_data = $stock_rs->fetch_assoc();
                }

                if ($stock_data["qty"] <= $qty_array[$x]) {
                    $qty = $stock_data["qty"];
                } else {
                    $qty = $qty_array[$x];
                }

                $product_name = $stock_data["title"];
                $stock_price = $stock_data["stock_price"];
                $size = $stock_data["size"];
                $subTotal =(int)$subTotal +((int) $qty * (int)$stock_price);
            ?>
                <li><span><?php echo ($qty); ?></span> x<span> <?php echo ($product_name); ?></span> - <span><?php echo ($size); ?></span>
                    <span class="float-end">Rs.<?php echo ($stock_price * $qty); ?>.00</span>
                </li>
                <input class="d-none stock_no" value="<?php echo ($stock_no); ?>">
                <input class="d-none qty" value="<?php echo ($qty); ?>">


            <?php
            }

            $total = $subTotal - $discount;

            ?>

            <div id="cost-breakdown">
                <li><span>Subtotal</span> <span class="d-none" id="subtotal"><?php echo ($subTotal); ?>
                    </span><span class="float-end">Rs.<?php echo ($subTotal); ?>.00</span></li>

                <li><span>Discount</span><span class="d-none" id="discount"><?php echo ($discount); ?></span>
                    <span class="float-end">Rs.<?php echo ($discount); ?>.00</span>
                </li>

                <li><span>Shipping</span><span class="d-none" id="shipping">0</span>
                <span id="shippingCost" class="float-end">Rs.0.00</span></li>

                <li><span>Order Total</span> <span class="d-none" id="total"><?php echo ($total); ?></span>
                    <span class="float-end">Rs.<?php echo ($total); ?>.00</span>
                </li>
            </div>
        </ul>
    </div>
<?php
} else {
    header("Location:index.php");
}
