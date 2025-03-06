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
    <title>BigFoot | Admin Stock Management</title>
    <link rel="stylesheet" href="bootstrap.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
    <link rel="stylesheet" href="style.css">
    <link rel="icon" href="resources/logo.png">
</head>

<body>
    <div class="container-fluid" style="padding: 0;">
        <?php include "adminSideMenu.php"; ?>

        <div class="col-sm-12 col-md-9 product-Management">
            <div class="breadcrumbs">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <p class="bread"><span><a href="adminDashboard.php">Dashboard</a></span> / <span>Stock Management</span></p>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row " style="margin-bottom: 1rem;">

                <div class="col-12">
                    <div class="heading-row d-flex overflow-auto">
                        <div class="col-lg col-sm-3 text-center">
                            <span>Stock No</span>
                        </div>
                        <div class="col-lg-4 col-sm-6 text-center">
                            <span>Product Details</span>
                        </div>
                        <div class="col-lg col-sm-3 text-center">
                            <span>Size</span>
                        </div>
                        <div class="col-lg col-sm-3 text-center">
                            <span>Price</span>
                        </div>
                        <div class="col-lg col-sm-3 text-center">
                            <span>Quantity</span>
                        </div>
                        <div class="col-lg col-sm-3 text-center">

                        </div>

                    </div>
                </div>

                <?php

                if (isset($_GET["page"])) {
                    $pageno = $_GET["page"];
                } else {
                    $pageno = 1;
                }

                $results_per_page = 6;

                $selected_rs = Database::search("SELECT * FROM `stock` ");
                $selected_num = $selected_rs->num_rows;
                $number_of_pages = ceil($selected_num / $results_per_page);

                $page_results = ($pageno - 1) * $results_per_page;

                $stock_rs = Database::search("SELECT *,`product`.`id` AS `pid`,`stock`.`status_id` AS`status` FROM `stock` 
                INNER JOIN `product` ON `product`.`id`= `stock`.`product_id`
                INNER JOIN `p_size` ON `stock`.`p_size_id`=`p_size`.`id` 
                ORDER BY `stock_no` ASC   LIMIT " . $results_per_page . " OFFSET " . $page_results . "  ");
                $stock_num = $stock_rs->num_rows;

                for ($x = 0; $x < $stock_num; $x++) {
                    $stock_data = $stock_rs->fetch_assoc();

                    $image_rs = Database::search("SELECT * FROM `image` WHERE `product_id`='" . $stock_data["pid"] . "'ORDER BY `code` ASC LIMIT 1 ");
                    $image_data = $image_rs->fetch_assoc();
                ?>
                    <div class="col-12 overflow-auto">
                        <div class="product-row d-flex border-bottom border-2 " style="border-bottom-left-radius: 0  ; border-bottom-right-radius: 0;">

                            <div class="col-lg col-sm-3 d-flex justify-content-center">
                                <div class="d-flex align-items-center">
                                    <span class="  fs-5"><?php echo $stock_data["stock_no"] ?></span>
                                </div>
                            </div>

                            <div class="col-lg-4  col-sm-6 d-flex justify-content-center align-items-center mb-0 " style=" padding: 0 5px; display: inline-flex;">

                                <div class="product-img col-4">
                                    <img src="<?php echo $image_data["code"]  ?>" alt="" style="width:90px ; height: 100px;">
                                </div>

                                <div class="col-6 d-flex ms-2 align-items-center">
                                    <h3 style="font-size:medium;"><?php echo $stock_data["title"] ?></h3>
                                </div>


                            </div>

                            <div class="col-lg col-sm-3 d-flex justify-content-center">
                                <div class="d-flex align-items-center">
                                    <span class="  fs-5"><?php echo $stock_data["size"] ?></span>
                                </div>
                            </div>

                            <div class="col-lg col-sm-3 d-flex justify-content-center">
                                <div class="d-flex align-items-center">
                                    <span class="price fs-5">Rs.<?php echo $stock_data["stock_price"] ?>.00</span>
                                </div>
                            </div>

                            <div class="col-lg col-sm-3 d-flex justify-content-center">
                                <div class="d-flex align-items-center">

                                    <span class=" fs-5"><?php echo $stock_data["qty"] ?></span>
                                </div>
                            </div>


                            <div class="col-lg col-sm-3  d-flex justify-content-center">
                                <div class="d-flex align-items-center">

                                    <?php
                                    if ($stock_data["status"] == 2) {

                                    ?>
                                        <span class=" btn btn-outline-success fs-5  " href="#" onclick="changeStockStatus(<?php echo $stock_data['pid'] . ',' . $stock_data['p_size_id'] ?>);">Enable</span>
                                    <?php
                                    } else if ($stock_data["status"] == 1) {

                                    ?>
                                        <span class=" btn btn-outline-danger fs-5" href="#" onclick="changeStockStatus(<?php echo $stock_data['pid'] . ',' . $stock_data['p_size_id'] ?>);">Disable</span>

                                    <?php
                                    }
                                    ?>
                                </div>
                            </div>


                        </div>
                    </div>

                <?php
                }
                ?>

                <div class="row mt-3">
                    <div class="col-md-12 text-center">
                        <nav aria-label="Page navigation example">
                            <ul class="pagination pagination-lg justify-content-center">
                                <li class="page-item">
                                    <a class="page-link" href="
                                            <?php if ($pageno <= 1) {
                                                echo ("#");
                                            } else {
                                                echo ".page=" . ($pageno - 1);
                                            } ?>" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <?php
                                for ($x = 1; $x <= $number_of_pages; $x++) {
                                    if ($x == $pageno) {
                                ?>
                                        <li class="page-item active">
                                            <a class="page-link" href="<?php echo "?page=" . ($x); ?>"><?php echo ($x); ?></a>
                                        </li>
                                    <?php
                                    } else {
                                    ?>
                                        <li class="page-item ">
                                            <a class="page-link" href="<?php echo "?page=" . ($x); ?>"><?php echo ($x); ?></a>
                                        </li>
                                <?php
                                    }
                                }

                                ?>
                                <li class="page-item">
                                    <a class="page-link" href="
                                            <?php if ($pageno >= $number_of_pages) {
                                                echo ("#");
                                            } else {
                                                echo ".page=" . ($pageno + 1);
                                            } ?>" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>

            </div>
        </div>

    </div>
    <script src="script.js"></script>
    <script src="bootstrap.bundle.js"></script>
</body>

</html>
<?php
}else{
    header("Location:adminLogin.php");
}
?>