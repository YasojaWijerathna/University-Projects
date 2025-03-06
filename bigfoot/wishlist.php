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



	<link rel="stylesheet" href="style.css">
	<link rel="stylesheet" href="bootstrap.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

	<link rel="icon" href="resources/logo.png">

</head>

<body>
	<?php include "header.php";

	if (isset($_SESSION["u"])) {
		$user = $_SESSION["u"]["email"];


	?>


		<div class="container" style="max-width: 1250px;" id="page">


			<div class="breadcrumbs">
				<div class="container">
					<div class="row">
						<div class="col">
							<p class="bread"><span><a href="home.php">Home</a></span> / <span>Wislist</span></p>
						</div>
					</div>
				</div>
			</div>



			<div id="page">

				<div class="row " style="padding-bottom: 6em">
					<div class="col-md-12">

						<?php
						$wish_rs = Database::search("SELECT * FROM `wishlist` WHERE `user_email`='" . $_SESSION["u"]["email"] . "'");
						$wish_num = $wish_rs->num_rows;

						if ($wish_num == 0) {
						?>

							<div class="col-12 col-lg-12">
								<div class="row">
									<div class="col-12 emptyView"></div>
									<div class="col-12 text-center">
										<label class="form-label fs-1 fw-bold">You have no items in your wishlist yet.</label>
									</div>
									<div class="offset-lg-4 col-12 col-lg-4 d-grid mb-3">
										<a href="home.php" class="btn btn-warning fs-3 fw-bold">Start Shopping</a>
									</div>
								</div>
							</div>


						<?php
						} else {


						?>

							<div class="heading-row d-flex overflow-auto">
								<div class="col-md-6 col-sm-8	 text-center">
									<span>Product Details</span>
								</div>
								<div class="col-md col-sm-3 text-center">
									<span>Price</span>
								</div>
								<div class="col-md col-sm-6 text-center">
								</div>

								<div class="col-md col-sm-3  text-center  ">
									<span>Remove</span>
								</div>
							</div>

							<?php

							for ($x = 0; $x < $wish_num; $x++) {
								$wish_data = $wish_rs->fetch_assoc();

								$product_rs = Database::search("SELECT *  FROM `product` WHERE `status_id`='1' AND `id`='" . $wish_data["product_id"] . "'  ");
								$product_data = $product_rs->fetch_assoc();

								$image_rs = Database::search("SELECT * FROM `image` WHERE `product_id`='" . $product_data["id"] . "'  ");
								$image_data = $image_rs->fetch_assoc();

								$price_rs = Database::search("SELECT * FROM `stock` WHERE `status_id`='1' 
								AND `product_id`='" . $product_data["id"] . "' ORDER BY `p_size_id` ASC, `date_added` ASC LIMIT 1;");

								$price_data = $price_rs->fetch_assoc();

							?>
								<div class="product-row d-flex overflow-auto">
									<div class="col-sm-8 col-md-6 d-flex align-items-center justify-content-center mb-0 " style=" padding: 0 5px; display: inline-flex;">

										<div class="product-img col-4">
											<img src="<?php echo $image_data["code"]  ?>" alt="" style="width:90px ; height: 100px;">
										</div>

										<div class="col-6 ms-2 d-flex align-items-center">
											<h3 style="font-size:large;"><?php echo $product_data["title"] ?></h3>
										</div>


									</div>

									<div class="col-md col-sm-3  d-flex justify-content-center">
										<div class="d-flex align-items-center">
											<span class="price  " style="font-size:medium;">Rs.<?php echo $price_data["stock_price"] ?>.00</span>
										</div>
									</div>

									<div class="col-md col-sm-6 justify-content-center d-flex  align-items-center " style="display: inline-flex; word-break: break-all;">
					
										<?php
										$cart_rs = Database::search("SELECT * FROM `cart` WHERE `user_email`='" . $user . "' AND `stock_no`='" . $price_data["stock_no"] . "'   ");
										$cart_num = $cart_rs->num_rows;

										if ($cart_num == 0) {
										?>
											<div class="">
												<a class=" btn btn-outline-secondary " style="font-size: 1.3rem;" onclick="addToCart(<?php echo ($product_data['id']); ?>);">Add To Cart</a>
											</div>
										<?php
										} else {
										?>
											<div class="">
												<a class=" btn btn-outline-secondary disabled" style="font-size: 1.3rem;"> Already In Cart</a>
											</div>
										<?php
										}
										?>
									</div>
									<div class=" col-sm-3 col-md d-flex align-items-center justify-content-center ">
										<div>
											<a href="#" onclick="removeFromwishlist(<?php echo $wish_data['id'] ?>);"><i class="bi bi-x-square fs-2"></i></a>
										</div>
									</div>


								</div>
								<hr class="d-block border-dark border-2">
						<?php

							}
						}
						?>
					</div>
					<?php

					?>
				</div>
			</div>
		</div>
		<?php

		include "footer.php";
	} else {
		?>
			<div class="container">

				<div class="col-12 col-lg-12">
					<div class="row">
						<div class="col-12 emptyView"></div>
						<div class="col-12 text-center">
							<label class="form-label fs-1 fw-bold">You have no items in your wishlist yet.</label>
						</div>
						<div class="offset-lg-4 col-12 col-lg-4 d-grid mb-3">
							<a href="home.php" class="btn btn-warning fs-3 fw-bold">Start Shopping</a>
						</div>
					</div>
				</div>
			</div>

		<?php
	}
		?>



		<script src="script.js"></script>
		<script src="bootstrap.bundle.js"></script>
</body>

</html>