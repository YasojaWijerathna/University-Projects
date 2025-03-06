<?php

include "connection.php";

$brand_id = $_GET["bid"];
$gender_id = $_GET["gid"];


$brand_rs = Database::search("SELECT * FROM `brand` WHERE `id`= '" . $brand_id . "' ");
$brand_num = $brand_rs->num_rows;


$pageno;
if (isset($_GET["page"])) {
    $pageno = $_GET["page"];
} else {
    $pageno = 1;
}

$selected_rs = Database::search("SELECT * FROM `product`  WHERE `gender_id`='" . $gender_id . "' 
AND `brand_id`='" . $brand_id . "'  AND `status_id`='1' ");
$selected_num = $selected_rs->num_rows;

if ($selected_num > 0) {

    $results_per_page = 6;
    $number_of_pages = ceil($selected_num / $results_per_page);

    $page_results = ((int)$pageno - 1) * $results_per_page;

    $product_rs = Database::search("SELECT * FROM `product`  WHERE `gender_id`='" . $gender_id . "' AND `brand_id`='" . $brand_id . "'
    AND `status_id`='1' ORDER BY `datetime_added` DESC LIMIT " . $results_per_page . " OFFSET " . $page_results . "  ");
    $product_num = $product_rs->num_rows;
?>
    <div class="row d-flex justify-content-sm-center justify-content-lg-start" style="padding-bottom: 4em">
        <?php

        for ($z = 0; $z < $product_num; $z++) {
            $product_data = $product_rs->fetch_assoc();



            $price_rs = Database::search("SELECT `stock_price`  FROM `stock` WHERE `status_id`='1' 
        AND `product_id`='" . $product_data["id"] . "' ORDER BY `p_size_id` ASC, `date_added` ASC LIMIT 1;");

            $price_date = $price_rs->fetch_assoc();
        ?>

            <div class="col-lg-4 col-md-5 col-sm-10 mb-4 text-center">
                <div class="product-card border">
                    <a href="<?php echo "singleProduct.php?id=" . ($product_data["id"]) ?>" class="prod-img">
                        <?php
                        $image_rs = Database::search("SELECT * FROM `image` WHERE `product_id`='" . $product_data["id"] . "'  ");
                        $image_data = $image_rs->fetch_assoc();
                        ?>
                        <img src="<?php echo $image_data["code"]; ?>" class="img-fluid" style="height:280px ; width: 100%;">
                    </a>
                    <div class="description">
                        <h2><a href="<?php echo "singleProduct.php?id=" . ($product_data["id"]) ?>"><?php echo $product_data["title"]; ?></a></h2>
                        <span class="price">Rs.<?php echo $price_date["stock_price"]; ?>.00</span>
                        <a href="<?php echo "singleProduct.php?id=" . ($product_data["id"]) ?>" class="btn btn-success">Buy Now</a>
                        <div class="row d-flex justify-content-center" style="margin: 0 -15px ;">

                            <div class="col-8">
                                <a href="#" onclick="addToCart(<?php echo ($product_data['id']); ?>);" class="btn btn-outline-secondary">
                                    <i class="bi bi-cart3"></i>
                                </a>

                                <?php
                                if (isset($_SESSION["u"])) {

                                    $wishlist_rs = Database::search("SELECT * FROM `wishlist` WHERE `product_id`='" . $product_data["id"] . "' AND 
                            `user_email`='" . $_SESSION["u"]["email"] . "'");
                                    $wishlist_num = $wishlist_rs->num_rows;

                                    if ($wishlist_num == 1) {
                                ?>
                                        <a href="#" onclick='addToWishlist(<?php echo $product_data["id"]; ?>); ' class="btn btn btn-outline-dark">
                                            <i id="heart<?php echo $product_data["id"]; ?>" class="bi bi-suit-heart-fill text-dark"></i>
                                        </a>
                                    <?php
                                    } else {
                                    ?>
                                        <a href="#" onclick='addToWishlist(<?php echo $product_data["id"]; ?>); ' class="btn btn btn-outline-dark">
                                            <i id="heart<?php echo $product_data["id"]; ?>" class="bi bi-suit-heart-fill text-danger"></i>
                                        </a>
                                    <?php
                                    }
                                } else {
                                    ?>
                                    <a href="#" onclick='addToWishlist(<?php echo $product_data["id"]; ?>); ' class="btn btn btn-outline-dark">
                                        <i id="heart<?php echo $product_data["id"]; ?>" class="bi bi-suit-heart-fill text-danger"></i>
                                    </a>

                                <?php
                                }
                                ?>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

        <?php
        }
        ?>
        <div class="row">
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

    <?php
} else {
    ?>
        <div class="col-12 ">
            <div class="row">
                <div class="col-12 noMatchImage"></div>

                <div class="col-12 mb-2 text-center">
                    <label class="form-label fs-1 fw-bold">No Items Match Your Selection</label>
                </div>

                <div class="offset-lg-4 col-12 col-lg-4 mb-4 d-grid">

                    <?php
                    if ($gender_id == 1) {
                    ?>
                        <a href="men.php" style="word-break: normal;"  class="btn btn-outline-info  fs-4 fw-bold">Select A different Brand</a>
                    <?php
                    } else if ($gender_id == 2) {
                    ?>
                        <a href="women.php"  style="word-break: break-all;" class="btn btn-outline-info  fs-4  fw-bold">Select A different Brand</a>
                    <?php
                    }
                    ?>

                </div>
            </div>
        </div>
    <?php
}
    ?>