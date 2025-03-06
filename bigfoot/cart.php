<?php
session_start();
require "connection.php";
?>
<!DOCTYPE HTML>
<html>

<head>
	<title>BigFoot | Cart</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


	<link rel="stylesheet" href="bootstrap.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
	<link rel="stylesheet" href="style.css">
	<link rel="icon" href="resources/logo.png">


</head>

<body>

	<?php include "header.php"; ?>

	<div class=" container-fluid" id="page">

		<?php
		if (isset($_SESSION["u"])) {

			$user = $_SESSION["u"]["email"];

		?>

			<p class="d-none" id="pageName">cart</p>
			<div class="breadcrumbs">
				<div class="container">
					<div class="row">
						<div class="col">
							<p class="bread"><span><a href="home.php">Home</a></span> / <span>Shopping Cart</span></p>
						</div>
					</div>
				</div>
			</div>


			<div class="container mt-1">

				<div class="row " style="padding-bottom: 6em">
					<div class="col-md-12">

						<?php
						$cart_rs = Database::search("SELECT * FROM `cart` WHERE `user_email`='" . $user . "' ");
						$cart_num = $cart_rs->num_rows;

						if ($cart_num == 0) {
						?>
							<div class="col-12 ">
								<div class="row">
									<div class="col-12 emptyCart"></div>

									<div class="col-12 mb-2 text-center">
										<label class="form-label fs-1 fw-bold">You have no items in your Cart</label>
									</div>

									<div class="offset-lg-4 col-12 col-lg-4 mb-4 d-grid">
										<a href="home.php" class="btn btn-outline-info fs-3 fw-bold">Start Shopping</a>
									</div>
								</div>
							</div>


						<?php
						} else {


						?>

							<div class="heading-row d-flex overflow-auto">
								<div class="col-lg-5 col-md-4 col-sm-7 text-center">
									<span>Product Details</span>
								</div>
								<div class="col-lg col-md-2 col-sm-3 text-center">
									<span>Size</span>
								</div>
								<div class="col-lg col-md-2 col-sm-3 text-center">
									<span>Price</span>
								</div>
								<div class="col-lg col-md-2 col-sm-3 text-center">
									<span>Quantity</span>
								</div>
								<div class="col-lg  col-md-2  col-sm-3 text-center">
									<span>Total</span>
								</div>
								
								<div class="col-lg col-md-2 col-sm-3 text-center  ">
									<span>Remove</span>
								</div>
							</div>



							<?php
							$total = 0;
							$discount = 0;
							$netTotal = 0;
							for ($x = 0; $x < $cart_num; $x++) {
								$cart_data = $cart_rs->fetch_assoc();

								$product_rs = Database::search("SELECT * ,`product`.`id` AS `pid` FROM `stock`
								INNER JOIN `p_size` ON `stock`.`p_size_id`=`p_size`.`id`  
								INNER JOIN `product` ON `stock`.`product_id`=`product`.`id`
								WHERE  `stock_no`='" . $cart_data["stock_no"] . "'
								 ORDER BY `p_size_id` ASC, `date_added` ASC LIMIT 1;");
								$product_data = $product_rs->fetch_assoc();

								$p_total = $product_data["stock_price"] * $cart_data["qty"];
								$total = $total + $product_data["stock_price"] * $cart_data["qty"];
								$netTotal = $total - $discount;

								$image_rs = Database::search("SELECT * FROM `image` WHERE `product_id`='" . $product_data["pid"] . "'  ");
								$image_data = $image_rs->fetch_assoc();



							?>
								<div class="product-row d-flex overflow-auto ">
									<div class="col-lg-5 col-md-4 col-sm-7 d-flex align-items-center mb-0 " style=" padding: 0 5px; display: inline-flex;">

										<div class="product-img col-4">
											<img src="<?php echo $image_data["code"]  ?>" alt="" style="width:90px ; height: 100px;">
										</div>

										<div class="col-6 d-flex ms-2 align-items-center">
											<h3 style="font-size:medium;"><?php echo $product_data["title"] ?></h3>
										</div>


									</div>
									<div class="col-12 d-none">
										<input type="text" class="stock_no" value="<?php echo $product_data["stock_no"] ?>">
									</div>

									<div class="col-lg col-md-2 col-sm-3 d-flex justify-content-center">
										<div class="d-flex align-items-center">
											<span class="price  fs-5"><?php echo $product_data["size"] ?></span>
										</div>
									</div>

									<div class="col-lg col-md-2 col-sm-3 d-flex justify-content-center">
										<div class="d-flex align-items-center">
											<span class="price  fs-5">Rs.<?php echo $product_data["stock_price"] ?>.00</span>
										</div>
									</div>

									<div class="col-lg col-md-2 col-sm-3 d-flex justify-content-center">
										<div class="d-flex align-items-center justify-content-center">
											<input type="text" style="width: 35%; " class="form-control input-number text-center quantity" value="<?php echo $cart_data["qty"] ?>" min="1" max="100">
										</div>
									</div>

									<div class="col-lg col-md-2  col-sm-3 d-flex justify-content-center">
										<div class="d-flex align-items-center">
											<span class="price fs-5">Rs.<?php echo $p_total ?>.00</span>
										</div>
									</div>

							

									<div class="col-lg col-md-2 col-sm-3 d-flex justify-content-center">
										<div class="d-flex align-items-center">
											<a href="#" onclick="removeFormCart(<?php echo $cart_data['id'] ?>);"><i class="bi bi-x-square fs-2"></i></a>
										</div>
									</div>
								</div>
								<hr class="d-block border-dark border-2" style="margin-top: 0;">
							<?php

							}

							?>
					</div>
					<?php

					?>
					<div class="row ">
						<div class="col-12 ">
							<div class="row d-flex justify-content-sm-center">

								<div class="col-md-7 col-lg-8 col-sm-12">
									<div class="  discount">
										<p class="fs-5">Discount Coupon</p>

										<div class="row d-flex justify-content-center align-items-center" style="padding:0; margin: 5px;">
											<div class="col-lg-7 d-flex justify-content-center" style="padding:0;">
												<input type="text" class="input rounded-5" id="couponInput">
											</div>
											<div class="col-lg-5 d-flex mt-lg-0 mt-2  justify-content-center" style="padding:0; ">
												<a class="btn btn-success rounded-4 text-white" onclick="applyCoupon(<?php echo ($total) ?>,<?php echo ($cart_num) ?>)">Apply Coupon</a>
											</div>
										</div>

										<div class="row d-none justify-content-center" id="invalidWrap" style="padding:0; margin: 10px 5px; ">
											<div class="col-lg-7 d-flex justify-content-center" style="padding:0;">
												<input type="text" class="invalid rounded-3 text-center  text-white " id="couponInput" value="Invalid Coupon">
											</div>
											<div class="col-lg-5 ">

											</div>
										</div>


									</div>
								</div>

								<div class=" col-md-5 col-lg-4 col-sm-12 mt-sm-3 ">
									<div class="total" id="totalWrap">

										<div class="sub">
											<div class="row">
												<div class="col-6 text-left">
													<p>Subtotal:</p>
													<p>No of Products:</p>
													<p>Discount:</p>
												</div>
												<div class="col-6 text-center">
													<p>Rs.<?php echo $total ?>.00</p>
													<p><?php echo $cart_num ?></p>
													<p>Rs.<?php echo $discount ?>.00</p>
													<p id="discount" class="d-none"><?php echo $discount ?></p>
												</div>

											</div>
										</div>

										<div class="grand-total text-center">
											<div class="row">
												<div class="col-6 text-left">
													<p>Total :</p>

												</div>
												<div class="col-6 text-center">
													<p>Rs.<?php echo $netTotal ?>.00</p>

												</div>

											</div>
										</div>

									</div>

									<div class="col-12 d-flex justify-content-center">
										<a href="#" onclick="gotoShipping(<?php echo ($cart_num) ?>);" class="btn btn-success mt-3">Proceed to Shipping</a>
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

		<?php
		} else {
		?>
			<div class="container">
				<div class="heading-row d-flex">
					<div class="col-5 text-center">
						<span>Product Details</span>
					</div>
					<div class="col text-center">
						<span>Price</span>
					</div>
					<div class="col text-center">
						<span>Quantity</span>
					</div>
					<div class="col text-center">
						<span>Total</span>
					</div>
					<div class="col text-center">
					</div>
					<div class="col text-center  ">
						<span>Remove</span>
					</div>
				</div>

				<div class="col-12 ">
					<div class="row">
						<div class="col-12 emptyCart"></div>

						<div class="col-12 mb-2 text-center">
							<label class="form-label fs-1 fw-bold">You are not Logged into Account</label>
						</div>

						<div class="offset-lg-4 col-12 col-lg-4 mb-4 d-grid">
							<a href="index.php" class="btn btn-outline-info fs-3 fw-bold">Please Sign In or Sign Up</a>
						</div>
					</div>
				</div>
			</div>
		<?php
		}

		?>
	</div>
	<?php include "footer.php"; ?>


	<script src="script.js"></script>
	<script src="bootstrap.bundle.js"></script>
</body>

</html>