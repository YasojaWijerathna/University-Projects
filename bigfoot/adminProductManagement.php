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
        <title>BigFoot | Admin Product Management</title>
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
                                <p class="bread"><span><a href="adminDashboard.php">Dashboard</a></span> / <span>Product Management</span></p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row" style="margin-bottom: 1rem;">

                    <div class="col-12">
                        <div class="heading-row d-flex overflow-auto">
                            <div class="col-lg-5 col-sm-6 text-center">
                                <span>Product Details</span>
                            </div>
                            <div class="col-lg col-sm-3 text-center">
                                <span>Brand</span>
                            </div>
                            <div class="col-lg col-sm-3 text-center">
                                <span>Gender</span>
                            </div>
                            <div class="col-lg col-sm-3 text-center"></div>
                        </div>
                    </div>

                    <?php

                    if (isset($_GET["page"])) {
                        $pageno = $_GET["page"];
                    } else {
                        $pageno = 1;
                    }

                    $results_per_page = 6;

                    $selected_rs = Database::search("SELECT * FROM `product` ");
                    $selected_num = $selected_rs->num_rows;
                    $number_of_pages = ceil($selected_num / $results_per_page);

                    $page_results = ($pageno - 1) * $results_per_page;

                    $product_rs = Database::search("SELECT *,`product`.`id` AS `pid` FROM `product` 
                INNER JOIN `brand` ON `product`.`brand_id`=`brand`.`id` 
                INNER JOIN `gender` ON `gender`.`id`= `product`.`gender_id`
                ORDER BY `product`.`id` ASC   LIMIT " . $results_per_page . " OFFSET " . $page_results . "  ");
                    $product_num = $product_rs->num_rows;

                    for ($x = 0; $x < $product_num; $x++) {
                        $product_data = $product_rs->fetch_assoc();

                        $image_rs = Database::search("SELECT * FROM `image` WHERE `product_id`='" . $product_data["pid"] . "'ORDER BY `code` ASC LIMIT 1 ");
                        $image_data = $image_rs->fetch_assoc();
                    ?>
                        <div class="col-12 overflow-auto">
                            <div class="product-row d-flex border-bottom border-2" style="border-bottom-left-radius: 0  ; border-bottom-right-radius: 0;">
                                <div class="col-lg-5  col-sm-6 d-flex justify-content-center align-items-center mb-0 " style=" padding: 0 5px; display: inline-flex;">

                                    <div class="product-img col-4">
                                        <img src="<?php echo $image_data["code"]  ?>" alt="" style="width:90px ; height: 100px;">
                                    </div>

                                    <div class="col-6 d-flex ms-2 align-items-center">
                                        <h3 style="font-size:medium;"><?php echo $product_data["title"] ?></h3>
                                    </div>


                                </div>

                                <div class="col-lg col-sm-3 d-flex justify-content-center">
                                    <div class="d-flex align-items-center">
                                        <span class="price  fs-5"><?php echo $product_data["name"] ?></span>
                                    </div>
                                </div>

                                <div class="col-lg col-sm-3 d-flex justify-content-center">
                                    <div class="d-flex align-items-center">
                                        <span class="price fs-5"><?php echo $product_data["gender_name"] ?></span>
                                    </div>
                                </div>


                                <!-- <div class="col-lg-3 col-sm-6 col-md-4  ms-md-5 ms-lg-0 d-flex justify-content-center">
                                <div class="d-flex align-items-center">
                                    <a class=" btn btn-outline-warning fs-5  "data-bs-toggle="modal" data-bs-target="#updateProduct" onclick="getProductID(<?php echo ($product_data['pid']) ?>)">Update Product Details</a>
                                </div>
                            </div> -->

                                <div class="col-lg col-sm-3 ms-sm-4 ms-lg-0 d-flex justify-content-center">
                                    <div class="d-flex align-items-center">
                                        <a class=" btn btn-outline-success fs-5" data-bs-toggle="modal" data-bs-target="#addStockModal" onclick="getProductID(<?php echo ($product_data['pid']) ?>)">Add Stock</a>
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


        <!-- Add Stock Modal -->
        <div class="modal fade" id="addStockModal" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Add Stock</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body ">

                        <div class=" col-12 mt-1 mb-2">
                            <label class=" form-label" for="size"> Shoe Size</label>
                            <Select class=" form-select" id="size" style="height: 40px;">
                                <option value="0">Select Size</option>
                                <?php
                                $size_rs = Database::search("SELECT * FROM `p_size`");
                                $size_num = $size_rs->num_rows;
                                for ($x = 0; $x < $size_num; $x++) {
                                    $size_data = $size_rs->fetch_assoc();
                                ?>
                                    <option value="<?php echo $size_data["id"]; ?>">
                                        <?php echo $size_data["size"] ?></option>
                                <?php
                                }

                                ?>
                            </Select>
                        </div>

                        <div class="mb-2 col-12 ">
                            <label class="form-label">Quantity</label>
                            <input type="text" id="qty" class="form-control">
                        </div>

                        <div class="mb-2 col-12 ">
                            <label class="form-label">Stock Price</label>
                            <input type="text" id="st_price" class="form-control">
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" onclick="addStock()">Add to Stock</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Add Stock Modal -->

        <!-- Update product Modal -->
        <div class="modal fade" id="updateProduct" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Update Product Details</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body ">

                        <div class=" col-12 mt-1 mb-2">
                            <label class=" form-label"> Title</label>
                            <input type="text" class=" form-control" id="titleU" placeholder="<Nike> xxxx">
                        </div>

                        <div class="mb-2 col-12 ">
                            <label class=" form-label">Caption</label>
                            <textarea type="text" class=" form-control overflow-y-scroll" id="caption" placeholder="Insert  Product Caption"></textarea>
                        </div>

                        <div class="mb-2 col-12 ">
                            <label class=" form-label">Description</label>
                            <textarea type="text" class=" form-control overflow-y-scroll" id="desc" placeholder="Insert  Product Description"></textarea>
                        </div>

                        <div class="col-12 mt-3">
                            <label class=" form-label">Add images</label>
                            <div class="row mt-2">
                                <div class=" col-12">

                                    <div class="row d-md-flex  justify-content-center ">
                                        <div class="col-md-4 col-5 border border-primary rounded">
                                            <img src="resources/upload.png" class="img-fluid justify-content-center d-flex" style="width: 250px;" id="img0" />
                                        </div>
                                        <div class="col-md-4 col-5  border border-primary rounded">
                                            <img src="resources/upload.png" class="img-fluid" style="width: 250px;" id="img1">
                                        </div>
                                        <div class="col-md-4 col-5  border border-primary rounded">
                                            <img src="resources/upload.png" class="img-fluid" style="width: 250px;" id="img2" />
                                        </div>
                                    </div>

                                </div>
                                <div class="offset-md-3  offset-sm-2 col-8   col-md-6 d-grid mt-3">
                                    <input type="file" class="d-none" id="imageuploader" multiple />
                                    <label for="imageuploader" class="col-12 btn btn-primary" onclick="changeProductImage();">Upload Images</label>
                                    <p class="text-center">All three images must be selected at once</p>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <!-- <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button> -->
                        <button type="button" class="btn btn-primary" onclick="updateProduct()">Update Details</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Update Product Modal -->

        <script src="script.js"></script>
        <script src="bootstrap.bundle.js"></script>
    </body>

    </html>
<?php
} else {
    header("Location:adminLogin.php");
}
?>