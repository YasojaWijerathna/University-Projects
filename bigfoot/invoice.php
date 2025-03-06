<?php
include "connection.php";
$order_id = $_GET["oid"];
$qty_array = json_decode($_GET["qty"]);
$shipping = $_GET["shipping"];
$discount = $_GET["discount"];
$date = date("Y-m-d H:i");
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BigFoot | Invoice</title>
    <link rel="stylesheet" href="bootstrap.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="style.css">

    <link rel="icon" href="resources/logo.png">
</head>

<body style="padding: 0.5rem 3rem;">

    <div class="container mt-3 mb-2">
        <div class="col-12 btn-toolbar justify-content-end ">
            <button class="btn btn-dark me-2" onclick="printInvoice();"><i class="bi bi-printer-fill"></i> Print</button>
        </div>
    </div>

    <div class="container border border-2 rounded-3">
        <div class="row" id="invoice-body">

            <div class="col-12  head-logo-div ">
                <div class="d-flex justify-content-center ">
                    <a href="home.php"> <img src="resources/bigfoot_logo.png" alt="" class="invoice-logo"></a>
                </div>
            </div>

            <div class="col-12  mt-5 border-bottom border-3 mb-3">

                <div class="row">
                    <div class=" col-md-6 d-sm-flex justify-content-sm-center justify-content-md-start p-0">
                        <ul class="p-0 float-md-start ps-3  mb-0 ">
                            <p><i class="bi bi-house-fill"></i> Maradana,Colombo 10, Sri Lanka</p>
                            <p class="ms-sm-4 ms-md-0"><i class="bi bi-at"></i> BigFoot@gmail.com</p>
                        </ul>
                    </div>
                    <div class=" col-md-6 d-sm-flex justify-content-sm-center justify-content-md-end p-0">
                        <ul class="float-md-end pe-3 me-sm-5 mb-0">
                            <p><i class="bi bi-telephone-fill"></i> +94 112 356 356</p>
                            <p><i class="bi bi-printer-fill"></i> +94 112 356 356</p>
                        </ul>
                    </div>
                </div>
            </div>

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
                            <div class="col-md-5  col-lg-3 border border-2 rounded-2" style="background-color: #dcdbdb; ">
                                <span class="text-center fs-5 fw-bold" style="width: 100%;"> Date</span>
                            </div>
                            <div class="col-md-5  col-lg-3 border border-2 rounded-2 ">
                                <span class="text-center fs-6"><?php echo ($date); ?></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-12">
                <div class="heading-row d-flex overflow-auto border-bottom border-2 mb-3 ">
                    <div class="col-lg col-md-2 text-center ">
                        <span class="fw-bolder">Product No</span>
                    </div>
                    <div class="col-lg-5 col-md-4  text-center">
                        <span class="fw-bolder">Product Details</span>
                    </div>

                    <div class="col-lg col-md-2 text-center">
                        <span class="fw-bolder">Price</span>
                    </div>
                    <div class="col-lg col-md-2  text-center">
                        <span class="fw-bolder">Quantity</span>
                    </div>
                    <div class="col-lg  col-md-2   text-center">
                        <span class="fw-bolder">Total</span>
                    </div>
                </div>

                <?php
                $invoice_rs = Database::search("SELECT * FROM `invoice` INNER JOIN `invoice_item` ON 
                `invoice`.`id`=`invoice_item`.`invoice_id` WHERE `invoice`.`id`='" . $order_id . "' ");
                $invoice_num = $invoice_rs->num_rows;


                $subtotal = 0;
                for ($x = 0; $x < $invoice_num; $x++) {

                    $invoice_data = $invoice_rs->fetch_assoc();

                    $stock_rs = Database::search("SELECT * FROM `stock` INNER JOIN `product` ON `stock`.`product_id`=`product`.`id` 
                                        WHERE `stock_no`='" . $invoice_data["stock_no"] . "' ");
                    $stock_data = $stock_rs->fetch_assoc();


                    $qty = (int)$qty_array[$x];
                    $subtotal = $subtotal + ((int)$stock_data["stock_price"] * $qty);
                ?>
                    <div class="product-row d-flex overflow-auto border-bottom rounded-0 border-1 mb-3">
                        <div class="col-lg col-md-2 text-center">
                            <span><?php echo ($stock_data["stock_no"]); ?></span>
                        </div>
                        <div class="col-lg-5 col-md-4 text-center">
                            <span><?php echo ($stock_data["title"]); ?></span>
                        </div>

                        <div class="col-lg col-md-2  text-center">
                            <span><?php echo ($stock_data["stock_price"]); ?></span>
                        </div>
                        <div class="col-lg col-md-2 text-center">
                            <span><?php echo ($qty); ?></span>
                        </div>
                        <div class="col-lg  col-md-2  text-center">
                            <span><?php echo ((int)$stock_data["stock_price"] * $qty); ?></span>
                        </div>
                    </div>

                <?php
                }
                ?>

                <div class="col-12 mb-3">
                    <div class="row">

                        <div class="col-md-6 offset-md-6 mt-3 ">
                            <div class="row p-2 justify-content-end " style="padding:0px;">
                                <div class="col-md-5   col-lg-4 border border-2 rounded-2" style="background-color: #dcdbdb; ">
                                    <span class="text-center fs-5 fw-bold" style="width: 100%;"> Sub Total</span>
                                </div>
                                <div class="col-md-7  col-lg-4 border border-2 rounded-2 ">
                                    <span class="text-center fs-6">Rs.<?php echo ($subtotal); ?>.00</span>
                                </div>
                            </div>
                            <div class="row p-2 justify-content-end p-0">
                                <div class="col-md-5   col-lg-4 border border-2 rounded-2" style="background-color: #dcdbdb; ">
                                    <span class="text-center fs-5 fw-bold" style="width: 100%;"> Shipping</span>
                                </div>
                                <div class="col-md-7  col-lg-4 border border-2 rounded-2 ">
                                    <span class="text-center fs-6">Rs.<?php echo ($shipping); ?>.00</span>
                                </div>
                            </div>
                            <div class="row p-2 justify-content-end p-0">
                                <div class="col-md-5  col-lg-4 border border-2 rounded-2" style="background-color: #dcdbdb; ">
                                    <span class="text-center fs-5 fw-bold" style="width: 100%;"> Discount</span>
                                </div>
                                <div class="col-md-7  col-lg-4 border border-2 rounded-2 ">
                                    <span class="text-center fs-6">Rs.<?php echo ($discount); ?>.00</span>
                                </div>
                            </div>
                            <div class="row p-2 justify-content-end p-0">
                                <div class="col-md-5  col-lg-4 border border-2 rounded-2" style="background-color: #dcdbdb; ">
                                    <span class="text-center fs-5 fw-bold" style="width: 100%;"> Net Amount</span>
                                </div>
                                <div class="col-md-7     col-lg-4 border border-2 rounded-2 ">
                                    <span class="text-center fs-6">Rs.<?php echo ($subtotal + (int)$shipping - (int)$discount); ?>.00</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


            </div>

        </div>
    </div>

    <script src="script.js"></script>
    <script src="bootstrap.bundle.js"></script>
</body>

</html>