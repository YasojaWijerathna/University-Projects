<?php

session_start();
require "connection.php";

?>
<!DOCTYPE HTML>
<html>

<head>
	<title>Home</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	
	<link rel="stylesheet" href="bootstrap.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
	<link rel="stylesheet" href="style.css">
	<link rel="icon" href="resources/logo.png">




</head>

<body>
	<?php include "header.php";  ?>


	<div id="page">


		<div class="container">
			<div class="row m-md-1">
				<div class="col-sm-12 text-center">
					<h2 class="introduction">Find the most suitable Footwear for your whole family to last a lifetime</h2>
				</div>
			</div>
		</div>


		<div style="padding: 3rem 0rem; clear: both;">
			<div class="container" id="link-img-div">
				<div class="row d-flex justify-content-center">
					<div class="col-md-6 text-center">
						<div class="page-direction">
							<a href="men.php" class="img-link "><img src="images/men.jpg" class="direction-img" alt=""></a>
							<div class="link-text">
								<h2><a href="men.php">Shop Men's Collection</a></h2>
							</div>
						</div>
					</div>
					<div class="col-md-6 text-center">
						<div class="page-direction">
							<a href="women.php" class="img-link"><img src="images/women.jpg" class="direction-img" alt=""></a>
							<div class="link-text">
								<h2><a href="women.php">Shop Women's Collection</a></h2>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div style="padding: 3rem 1rem 	; clear: both;">
			<div class="container ">
				<div class="row d-flex justify-content-center">
					<div class="col-sm-8  text-center home-intro">
						<h2>Newly Introduced Products</h2>
					</div>
				</div>

				<div class="row ml-0 mb-0 mr-0 d-flex justify-content-center">

					<?php
					$product_rs = Database::search("SELECT * FROM `product`  WHERE `gender_id`='1' AND `status_id`='1'  
					ORDER BY `datetime_added` DESC LIMIT 4  OFFSET 0  ");
					$product_num = $product_rs->num_rows;

					for ($z = 0; $z < $product_num; $z++) {
						$product_data = $product_rs->fetch_assoc();

						$price_rs = Database::search("SELECT `stock_price` FROM `stock` WHERE `status_id`='1' 
						AND `product_id`='" . $product_data["id"] . "' ORDER BY `p_size_id` ASC, `date_added` ASC LIMIT 1;");

						$price_date = $price_rs->fetch_assoc();
					?>

						<div class="col-md-4 col-lg-3 col-9 mb-4 text-center">
							<div class="product-card border">
								<a href="<?php echo "singleProduct.php?id=" . ($product_data["id"]) ?>" class="prod-img">
									<?php
									$image_rs = Database::search("SELECT * FROM `image` WHERE `product_id`='" . $product_data["id"] . "'  ");
									$image_data = $image_rs->fetch_assoc();
									?>
									<img src="<?php echo $image_data["code"]; ?>" class="img-fluid" style="height:280px ; width:100%">
								</a>
								<div class="description">
									<h2><a href="<?php echo "singleProduct.php?id=" . ($product_data["id"]) ?>"><?php echo $product_data["title"]; ?></a></h2>
									<span class="price">Rs.<?php echo $price_date["stock_price"]; ?>.00</span>
									<a href="<?php echo "singleProduct.php?id=" . ($product_data["id"]) ?>" class="btn btn-success btn1">Buy Now</a>
									<div class="row d-flex justify-content-center" style="margin: 0 -20px">

										<div class="col-8 ">
											<div class="row justify-content-center">
												<a href="#" onclick="addToCart(<?php echo ($product_data['id']); ?>);" class="btn btn2 btn-outline-secondary">
													<i class="bi bi-cart3 fs-5"></i>
												</a>

												<?php

												if (isset($_SESSION["u"])) {

													$wishlist_rs = Database::search("SELECT * FROM `wishlist` WHERE `product_id`='" . $product_data["id"] . "' AND 
												`user_email`='" . $_SESSION["u"]["email"] . "'");
													$wishlist_num = $wishlist_rs->num_rows;

													if ($wishlist_num == 1) {
												?>
														<a href="#" onclick='addToWishlist(<?php echo $product_data["id"]; ?>); ' class="btn btn2  btn-outline-dark">
															<i id="heart<?php echo $product_data["id"]; ?>" class="bi bi-suit-heart-fill text-dark fs-5"></i>
														</a>
													<?php
													} else {
													?>
														<a href="#" onclick='addToWishlist(<?php echo $product_data["id"]; ?>); ' class="btn btn2  btn-outline-dark">
															<i id="heart<?php echo $product_data["id"]; ?>" class="bi bi-suit-heart-fill text-danger"></i>
														</a>
													<?php
													}
												} else {
													?>
													<a href="#" onclick='addToWishlist(<?php echo $product_data["id"]; ?>); ' class="btn btn2  btn-outline-dark">
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
						</div>

					<?php
					}
					?>


					<div class="w-100"></div>
					<?php
					$product_rs = Database::search("SELECT * FROM `product`  WHERE `gender_id`='2' AND `status_id`='1'  
					ORDER BY `datetime_added` DESC LIMIT 4  OFFSET 0  ");
					$product_num = $product_rs->num_rows;

					for ($z = 0; $z < $product_num; $z++) {
						$product_data = $product_rs->fetch_assoc();

						$price_rs = Database::search("SELECT `stock_price` FROM `stock` WHERE `status_id`='1' 
						AND `product_id`='" . $product_data["id"] . "' ORDER BY `p_size_id` ASC, `date_added` ASC LIMIT 1;");

						$price_date = $price_rs->fetch_assoc();

					?>

						<div class="col-md-4 col-lg-3 col-9 mb-4 text-center mt-4">
							<div class="product-card border">
								<a href="<?php echo "singleProduct.php?id=" . ($product_data["id"]); ?>" class="prod-img">
									<?php
									$image_rs = Database::search("SELECT * FROM `image` WHERE `product_id`='" . $product_data["id"] . "'  ");
									$image_data = $image_rs->fetch_assoc();
									?> 
									<img src="<?php echo $image_data["code"]; ?>" class="img-fluid" style="height:280px ; width:100%">
								</a>
								<div class="description">
									<h2><a href="<?php echo "singleProduct.php?id=" . ($product_data["id"]) ?>"><?php echo $product_data["title"]; ?></a></h2>
									<span class="price">Rs.<?php echo $price_date["stock_price"]; ?>.00</span>
									<a href="<?php echo "singleProduct.php?id=" . ($product_data["id"]) ?>" class="btn btn1 btn-success">Buy Now</a>
									<div class="row d-flex justify-content-center" style="margin: 0 -15px">

										<div class="col-8">
											<a href="#" onclick="addToCart(<?php echo ($product_data['id']); ?>);" class="btn btn2 btn-outline-secondary">
												<i class="bi bi-cart3"></i>
											</a>

											<?php

											if (isset($_SESSION["u"])) {

												$wishlist_rs = Database::search("SELECT * FROM `wishlist` WHERE `product_id`='" . $product_data["id"] . "' AND 
											`user_email`='" . $_SESSION["u"]["email"] . "'");
												$wishlist_num = $wishlist_rs->num_rows;

												if ($wishlist_num == 1) {
											?>
													<a href="#" onclick='addToWishlist(<?php echo $product_data["id"]; ?>); ' class="btn btn2  btn-outline-dark">
														<i id="heart<?php echo $product_data["id"]; ?>" class="bi bi-suit-heart-fill text-dark"></i>
													</a>
												<?php
												} else {
												?>
													<a href="#" onclick='addToWishlist(<?php echo $product_data["id"]; ?>); ' class="btn btn2 btn-outline-dark">
														<i id="heart<?php echo $product_data["id"]; ?>" class="bi bi-suit-heart-fill text-danger"></i>
													</a>
												<?php
												}
											} else {
												?>
												<a href="#" onclick='addToWishlist(<?php echo $product_data["id"]; ?>); ' class="btn btn2 btn-outline-dark">
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

				</div>
			</div>
		</div>


	</div>


	<?php include "footer.php"; ?>

	<div class="modal" id="SignInPopUp" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p class="text-center mt-2 text-info fs-3">Please Sign In to an Account to continue</p>
				</div>

			</div>
		</div>
	</div>

	<script src="script.js"></script>
	<script src="bootstrap.js"></script>
	<script src="bootstrap.bundle.js"></script>
</body>

</html>