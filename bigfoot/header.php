
<body>
	<div class="container-fluid  mb-0">
		<nav role="navigation" style="width:100%;">

			<div style="margin:40px  0px 10px  0px;">
				<div class="row d-flex justify-content-center mt-2">


					<div class="col-sm-12 col-md-6 head-logo-div">
						<div class="d-block ">
							<a href="home.php" > <img src="resources/bigfoot_logo.png" alt=""  class="header-logo"></a>
						</div>
					</div>
					<?php
					$pageName = basename($_SERVER['PHP_SELF']);
					?>
						<div class=" col-md-4 d-flex justify-content-end">
							<div class="input-group">
								<input type="text" class="form-control rounded-5 " style="height: 50px;" id="basic_search_txt" placeholder="Search">
								<button class="btn  rounded-5 ml-2" style="height: 40px; background-color: #88c8bc;" onclick="basicSearch(0);"><i class="bi bi-search text-white"></i></button>
							</div>
						</div>
				</div>
				<div class="row mt-4 d-flex justify-content-center">
					<div class="col-sm-12 col-md-10  " style="margin-left: 10px;">
						<ul class="header-dropdown">
							<?php
							if ($pageName == "home.php") {
							?>
								<li><a href="home.php " style="color: #098023;">Home</a></li>
							<?php
							} else {
							?>
								<li><a href="home.php">Home</a></li>
							<?php
							}

							if ($pageName == "men.php") {
							?>
								<li class="active"><a href="men.php"  style="color: #098023;" >Men</a></li>
							<?php
							} else {
							?>
								<li><a href="men.php">Men</a></li>
							<?php
							}


							if ($pageName == "women.php") {
							?>
								<li class="active"><a href="women.php"  style="color: #098023;">Women</a></li>
							<?php
							} else {
							?>
								<li><a href="women.php">Women</a></li>
							<?php
							}
							?>
							
							<?php
							if (isset($_SESSION["u"])) {
							?>
								<li class="position-absolute dropdown">

									<a href="#" class="dropdown-toggle" data-bs-toggle="dropdown" 
									 role="button" aria-expanded="false"><?php echo $_SESSION["u"]["fname"] ?>'s</a>
									<ul class="user-dropdown dropdown-menu rounded-2" >
										<?php
										if ($pageName == "myProfile.php") {
										?>
											<li class=" active"><a href="myProfile.php">My Profile</a></li>
										<?php
										} else {
										?>
											<li><a href="myProfile.php">My Profile</a></li>
										<?php
										}

										if ($pageName == "wishlist.php") {
										?>
											<li class="active"><a href="wishlist.php">Wishlist</a></li>
										<?php
										} else {
										?>
											<li><a href="wishlist.php">Wishlist</a></li>
										<?php
										}

										if ($pageName == "home.php") {
										?>
											<li class="active"><a href="orderHistory.php">Purchase History</a></li>
										<?php
										} else {
										?>
											<li><a href="orderHistory.php">Purchase History</a></li>
										<?php
										}
										?>
										<li><a href="#" onclick="signOut();">Sign Out</a></li>
									</ul>

								</li>

							<?php
							} else {
							?>
								<li><a href="index.php">Sign In or Register</a></li>
							<?php
							}
							?>

							<li style="float: right;"><a href="cart.php"> Cart<i class="bi bi-cart2"></i></a></li>
						</ul>
					</div>
				</div>
			</div>

		</nav>
	</div>

	<div class="col-12 mt-0 border border-top border-primary mb-3">
	</div>
	
</body>

