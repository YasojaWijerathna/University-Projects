<?php
session_start();
require "connection.php";
?>
<!DOCTYPE HTML>
<html>

<head>
    <title>BigFoot | Wishlist</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">



    <link rel="stylesheet" href="bootstrap.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="style.css">

    <link rel="icon" href="resources/logo.png">

</head>

<body>
    <?php include "header.php";

    if (isset($_SESSION["u"])) {
        $user = $_SESSION["u"]["email"];


    ?>


        <div id="page">


            <div class="breadcrumbs">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <p class="bread"><span><a href="home.php">Home</a></span> / <span>Purchase History</span></p>
                        </div>
                    </div>
                </div>
            </div>



            <div class="container">

                <div class="row " style="padding-bottom: 6em">
                    <div class="col-md-12">

                        <?php
                        $invoice_rs = Database::search("SELECT * FROM `invoice` WHERE `user_email`='" . $_SESSION["u"]["email"] . "'  ");
                        $invoice_num = $invoice_rs->num_rows;



                        if ($invoice_num == 0) {
                        ?>

                            <div class="heading-row d-flex">
                                <div class="col text-center">
                                    <span>Invoice ID</span>
                                </div>
                                <div class="col-5 text-left">
                                    <span>Product Details</span>
                                </div>
                                <div class="col text-center">
                                    <span>Price</span>
                                </div>
                                <div class="col text-center">
                                    <span>Quantity</span>
                                </div>
                                <div class="col-2 text-center">
                                    <span>Total</span>
                                </div>

                                <div class="col text-center  ">
                                    <span>Date of Purchase</span>
                                </div>
                            </div>

                            <div class="col-12 col-lg-12">
                                <div class="row">
                                    <div class="col-12 emptyView"></div>
                                    <div class="col-12 text-center">
                                        <label class="form-label fs-1 fw-bold">You have no items in your Purchase History yet.</label>
                                    </div>
                                    <div class="offset-lg-4 col-12 col-lg-4 d-grid mb-3">
                                        <a href="home.php" class="btn btn-warning fs-3 fw-bold">Start Shopping</a>
                                    </div>
                                </div>
                            </div>


                            <?php
                        } else {

                            for ($y = 0; $y < $invoice_num; $y++) {
                                $invoice_data = $invoice_rs->fetch_assoc();

                                $order_id=$invoice_data["id"];
                                $strdate=strtotime($invoice_data["date"]);
                                $date=date(("Y-M-d"),$strdate);
                                

                                $invoice_itm_rs = Database::search("SELECT * FROM `invoice_item` INNER JOIN `invoice` ON 
                                `invoice`.`id`=`invoice_item`.`invoice_id`   WHERE `invoice_id`='" . $order_id . "' ");
                                $invoice_itm_num = $invoice_itm_rs->num_rows;
                            ?>


                                <div class="border border-2 rounded-4 p-3 mb-3">

                                    <div class="row">
                                        <div class="col-12 mb-3">
                                            <div class="row">
                                                <div class=" col-md-6">
                                                    <div class="row p-2">
                                                        <div class="col-md-5 col-sm-6 col-lg-3  border border-2 rounded-2" style="background-color: #dcdbdb;">
                                                            <span class="text-center fs-5 fw-bold">Invoice #</span>
                                                        </div>
                                                        <div class="col-md-5 col-sm-6 col-lg-3 border border-2 rounded-2">
                                                            <span class="text-center fs-6"><?php echo ($order_id); ?></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class=" col-md-6  ">
                                                    <div class="row p-2 justify-content-end ">
                                                        <div class="col-md-5 col-sm-6 col-lg-3 border border-2 rounded-2" style="background-color: #dcdbdb; ">
                                                            <span class="text-center fs-5 fw-bold" style="width: 100%;"> Date</span>
                                                        </div>
                                                        <div class="col-md-5 col-sm-6 col-lg-3 border border-2 rounded-2 ">
                                                            <span class="text-center fs-6"><?php echo ($date); ?></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="heading-row d-flex overflow-auto">

                                        <div class="col-md-5 col-12  text-center">
                                            <span>Product Details</span>
                                        </div>
                                        <div class="col-md col-5 text-center">
                                            <span>Price</span>
                                        </div>
                                        <div class="col-md col-5 text-center">
                                            <span>Quantity</span>
                                        </div>
                                        <div class="col-md col-5 text-center">
                                            <span>Total</span>
                                        </div>


                                    </div>

                                    <?php
                                    $purchase_date = DateTime::createFromFormat("Y-m-d h:i:s", $invoice_data["date"]);
                                    for ($x = 0; $x < $invoice_itm_num; $x++) {
                                        $invoice_itm_data = $invoice_itm_rs->fetch_assoc();

                                        $stock_rs = Database::search("SELECT * FROM `stock` 
                                        INNER JOIN `p_size` ON `stock`.`p_size_id`=`p_size`.`id`
                                        INNER JOIN  `product` ON `stock`.`product_id`=`product`.`id`
                                        WHERE   `stock_no`='" . $invoice_itm_data["stock_no"] . "' ");

                                        $stock_data = $stock_rs->fetch_assoc();

                                        $image_rs = Database::search("SELECT * FROM `image` WHERE `product_id`='" . $stock_data["product_id"] . "'  ");
                                        $image_data = $image_rs->fetch_assoc();

                                        $p_total = (int)$stock_data["stock_price"] * (int) $invoice_itm_data["qty"];



                                    ?>
                                        <div class="product-row d-flex overflow-auto">



                                            <div class="col-md-5 col-12 d-flex justify-content-center align-items-center mb-0 " style=" padding: 0 5px; display: inline-flex;">

                                                <div class="product-img col-4">
                                                    <img src="<?php echo $image_data["code"]  ?>" alt="" style="width:90px ; height: 100px;">
                                                </div>

                                                <div class="col-6 d-flex align-items-center">
                                                    <h3 style="font-size:large;"><?php echo $stock_data["title"] . ' - Size:' . $stock_data["size"] ?></h3>
                                                </div>


                                            </div>

                                            <div class="col-md col-5 d-flex justify-content-center">
                                                <div class="d-flex align-items-center">
                                                    <span class="price  " style="font-size:medium;">Rs.<?php echo $stock_data["stock_price"] ?>.00</span>
                                                </div>
                                            </div>

                                            <div class="col-md col-5 d-flex justify-content-center">
                                                <div class="d-flex align-items-center">
                                                    <input type="text" id="quantity" name="quantity" class="form-control input-number text-center" disabled value="<?php echo $invoice_itm_data["qty"] ?>">
                                                </div>
                                            </div>

                                            <div class="col-md col-5 d-flex justify-content-center">
                                                <div class="d-flex align-items-center">
                                                    <span class="price  " style="font-size:medium;">Rs.<?php echo $p_total ?>.00</span>
                                                </div>
                                            </div>





                                        </div>
                                     
                                    <?php
                                    }
                                    ?>
                                </div>
                        <?php
                            }
                        }
                        ?>
                    </div>
                    <?php

                    ?>
                </div>
            </div>

        <?php

        include "footer.php";
    } else {
        echo "Not a registered user";
    }
        ?>



        <script src="script.js"></script>
        <script src="bootstrap.bundle.js"></script>
</body>

</html>