<?php

session_start();
require "connection.php";

if (isset($_GET["id"])) {
	$pid = $_GET["id"];

	$product_rs = Database::search("SELECT * FROM `product` INNER JOIN `brand` ON 
    brand.id=product.brand_id WHERE product.id='" . $pid . "'");

	$product_num = $product_rs->num_rows;

	if ($product_num == 1) {
		$product_data = $product_rs->fetch_assoc();

?>

		<!DOCTYPE HTML>
		<html>

		<head>
			<title>BigFoot</title>
			<meta charset="utf-8">
			<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


			<link rel="stylesheet" href="bootstrap.css">
			<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
			<link rel="stylesheet" href="style.css">
			<link rel="icon" href="resources/logo.png">`
		</head>

		<body>

			<?php include "header.php"; ?>

			<div id="page">
				<p class="d-none" id="pageName">singleProduct</p>

				<div class="breadcrumbs">
					<div class="container">
						<div class="row">
							<div class="col">
								<p class="bread"><span><a href="home.php">Home</a></span> / <span>Product Details </span></p>
							</div>
						</div>
					</div>
				</div>
				<div style="padding: 2em 0; clear: both;">
					<div class="container">
						<div class="row " style="padding-bottom: 6em">
							<div class="col-md-8 col-12">
								<div id="carouselExampleFade" class="carousel slide carousel-fade" data-bs-ride="carousel">
									<div class="carousel-inner">

										<?php

										$image_rs = Database::search("SELECT * FROM `image` WHERE `product_id`='" . $pid . "' ");
										$image_num = $image_rs->num_rows;
										$img = array();

										for ($x = 0; $x < $image_num; $x++) {
											$image_data = $image_rs->fetch_assoc();
											$img[$x] = $image_data["code"];

											if ($x == 0) {
										?>
												<div class="carousel-item active">
													<img src="<?php echo $img["$x"] ?>" class="d-block w-100" alt="...">
												</div>
											<?php
											} else {
											?>

												<div class="carousel-item">
													<img src="<?php echo $img["$x"] ?>" class="d-block w-100" alt="...">
												</div>

										<?php
											}
										}
										?>
									</div>
									<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
										<span class="carousel-control-prev-icon" aria-hidden="true" style="background-color:black ;"></span>
										<span class="visually-hidden">Previous</span>
									</button>
									<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
										<span class="carousel-control-next-icon" aria-hidden="true" style="background-color:black ;"></span>
										<span class="visually-hidden">Next</span>
									</button>
								</div>
							</div>
							<div class="col-md-4 col-12">
								<div class="single-product-details">
									<h3 class="text-success"><?php echo $product_data["title"]; ?></h3>

									<?php
									$price_rs = Database::search("SELECT *  FROM `stock` INNER JOIN `p_size` ON `stock`.`p_size_id`=`p_size`.`id`
									WHERE `status_id`='1' AND `product_id`='" . $pid . "' ORDER BY `p_size_id` ASC, `date_added` ASC LIMIT 1;");

									$price_date = $price_rs->fetch_assoc();

									?>

									<p class="fs-3 text-warning" id="amount">Rs. <?php echo $price_date["stock_price"] ?>.00</p>
									<p><?php echo $product_data["caption"] ?></p>

									<div class="size-wrap mb-3">
										<div class="size-block mb-2" id="sizeWrap">
											<h4 class="text-info">Available Sizes</h4>
											<ul>
												<?php
												$size_rs = Database::search("SELECT * FROM `stock` INNER JOIN `p_size` ON `stock`.`p_size_id`=`p_size`.`id`  WHERE `product_id`='" . $pid . "'  ");
												$size_num = $size_rs->num_rows;


												for ($x = 0; $x < $size_num; $x++) {
													$size_data = $size_rs->fetch_assoc();
													$size[$x] = $size_data["size"];
												?>
													<li class=""><a href="#" id="<?php echo $size_data["id"]; ?>" onclick="sizeSelect(<?php echo ($size_data['id']) ?>,<?php echo ($pid) ?>);"><?php echo $size[$x]; ?></a></li>
												<?php


												}
												?>
											</ul>
										</div>
									</div>

									<div class="input-group  mb-4 d-flex align-items-center justify-content-lg-start" id="qtyInputWrap">

										<div class="col-6 qty-input">
											<input type="number" onchange="QtyPriceChange(<?php echo $price_date['stock_price'] ?>);" id="qty" class="form-control quantity" value="1" min="1" max="<?php echo ($price_date["qty"]); ?>">
										</div>
										<div class="col-6 " style="padding: 0 0 0 5px; ">
											<span class="fw-bold font-italic text-warning">Quantity Available <?php echo ($price_date["qty"]); ?></span>
										</div>
									</div>

									<div class="row">
										<div class="col-12">
											<div class="row">
												<div class="col-lg-6 col-12 ">
													<button style="width: 90%;" class="btn btn-success" type="submit" onclick="gotoShipping(<?php echo ($product_num) ?>);">Buy Now</button>
												</div>
												<div class="col-lg-6 col-12">
													<button style="width: 90%;" onclick="addToCart(<?php echo ($pid); ?>);" class="btn btn-primary "><i class="bi bi-cart2"></i> Add to Cart</a></btn>
												</div>

											</div>
										</div>
									</div>

								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-12">
								<div class="row">

									<div class="col-6  d-none">
										<input class="stock_no" value="<?php echo ($price_date["stock_no"]); ?>">
										<span id="discount">0</span>
									</div>

									<div class="">
										<a class=" btn btn-primary">Description</a>
									</div>

									<div class="col-12">
										<textarea style="width: 100%; height: 200px;"  disabled><?php echo $product_data["description"]; ?></textarea>
									</div>

								</div>
							</div>
						</div>

					</div>
				</div>

				<?php include "footer.php"; ?>
			</div>






			<script src="script.js"></script>
			<script src="bootstrap.bundle.js"></script>
		</body>

		</html>

<?php

	} else {
		echo ("Sory for the inconvinient");
		header("Location:home.php");
	}
} else {
	echo ("Something went wrong");
}

?>