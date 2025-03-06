<?php
session_start();

include "connection.php";
if (isset($_SESSION["admin"])) {
?>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>BigFoot | Admin Dashboard</title>
        <link rel="stylesheet" href="bootstrap.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
        <link rel="stylesheet" href="style.css">
        <link rel="icon" href="resources/logo.png">
    </head>

    <body onload="loadSalesChart();loadBrandChart();">

        <div class="container-fluid" style="padding: 0;">
            <?php include "adminSideMenu.php"; ?>

            <div class="col-sm-12  col-md-9 dashboard">
                <div class="row">

                    <?php
                    $today = date_create(date("Y-m-d"));
                    $oldDate = date_sub($today, date_interval_create_from_date_string("30 days"));

                    $sales_rs = Database::search("SELECT SUM(`total`) AS `sale_amount` ,SUM(`qty`) AS `sales`  FROM `invoice`
                 INNER JOIN `invoice_item` ON `invoice`.`id`=`invoice_item`.`invoice_id` 
                 WHERE (`date` BETWEEN '" . date_format($oldDate, "Y-m-d") . "' AND '" . date("Y-m-d") . "' )");
                    $sales_data = $sales_rs->fetch_assoc();

                    $user_rs = Database::search("SELECT COUNT(`email`) AS `count` FROM `user` 
                WHERE (`joined_date` BETWEEN '" . date_format($oldDate, "Y-m-d") . "' AND '" . date("Y-m-d") . "' )");
                    $user_data = $user_rs->fetch_assoc();

                    ?>

                    <div class="col-md col-12 mb-3">
                        <div class="col-12 justify-content-center d-flex">

                            <div class="card data-card">
                                <div class="card-body">
                                    <h5 class="card-title">Monthly Sale</h5>

                                    <p class="card-text">Rs. <?php echo ($sales_data["sale_amount"]) ?>.00</p>

                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="col-md col-12 mb-3">
                        <div class="col-12 justify-content-center d-flex">

                            <div class="card data-card">
                                <div class="card-body">
                                    <h5 class="card-title">Products Sold</h5>

                                    <p class="card-text"> <?php echo ($sales_data["sales"]) ?></p>

                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="col-md col-12 mb-3">
                        <div class="col-12 justify-content-center d-flex">

                            <div class="card data-card">
                                <div class="card-body">
                                    <h5 class="card-title">New Registration </h5>

                                    <p class="card-text align-bottom"><?php echo ($user_data["count"]) ?></p>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="row  chartRow">
                    <div class=" col-12 sales-chart-div">
                        <canvas id="salesChart"></canvas>
                    </div>
                </div>
                <div class="row ">
                    <div class="col-lg-4  col-12">
                        <div class="row chartRow">
                            <div class="col-12 brand-chart-div">
                                <canvas id="brandChart"></canvas>
                            </div>
                        </div>

                    </div>

                    <div class="col-lg-8 mt-4 mt-lg-0">

                        <div class="heading-row d-flex justify-content-center" style="margin-bottom: 0;">

                            <div class="col text-center">
                                <span>Invoice ID</span>
                            </div>
                            <div class="col-4 text-center">
                                <span>Customer Name</span>
                            </div>
                            <div class="col-4 text-center">
                                <span>Customer Email</span>
                            </div>
                            <div class="col text-center">
                                <span>Amount</span>
                            </div>
                        </div>

                        <?php
                        $invoice_rs = Database::search("SELECT * FROM `invoice` INNER JOIN `user` ON `invoice`.`user_email`=`user`.`email`
                     ORDER BY DATE DESC LIMIT 8");
                        $invoice_num = $invoice_rs->num_rows;

                        for ($x = 0; $x < $invoice_num; $x++) {
                            $invoice_data = $invoice_rs->fetch_assoc();
                        ?>
                            <div class="row admin-dashboard-invoice">
                                <div class="col text-center">
                                    <span><?php echo $invoice_data["id"] ?></span>
                                </div>
                                <div class="col-4 text-center">
                                    <span><?php echo $invoice_data["fname"] . " " . $invoice_data["lname"] ?></span>
                                </div>
                                <div class="col-4 text-center">
                                    <span><?php echo $invoice_data["email"] ?></span>
                                </div>
                                <div class="col text-center">
                                    <span>Rs.<?php echo $invoice_data["total"] ?>.00</span>
                                </div>
                            </div>
                        <?php
                        }
                        ?>
                    </div>
                </div>

            </div>


        </div>


        <script src="script.js"></script>
        <script src="bootstrap.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    </body>

    </html>
<?php
}else{
    header("Location:adminLogin.php");
}
?>