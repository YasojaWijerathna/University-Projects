<?php
session_start();
require "connection.php";
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BigFoot | Shipping</title>

    <link rel="stylesheet" href="bootstrap.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="style.css">
    <link rel="icon" href="resources/logo.png">
</head>

<body onload="setVisibleShippingCart()">
    <?php

    if (isset($_SESSION["u"])) {
        $user = $_SESSION["u"]["email"];

        $address_rs = Database::search("SELECT *,`city`.`id` AS `cid`,`district`.`id` AS `did`,`province`.`id` AS `prid`  FROM `user_has_address`
        INNER JOIN `city` ON user_has_address.city_id=city.id
        INNER JOIN `district` ON city.district_id=district.id 
		INNER JOIN `province` ON district.province_id=province.id WHERE `user_email`='" . $user . "' ");
        $address_num = $address_rs->num_rows;
       
        $province_rs = Database::search("SELECT * FROM `province` ");
        $district_rs = Database::search("SELECT * FROM `district` ");
        $city_rs = Database::search("SELECT * FROM `city` ");

    ?>
        <div class="container-fluid">

            <?php include "header.php";
            ?>

            <div class="breadcrumbs">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <p class="bread"><span><a href="home.php">Home</a></span> / <span>Shipping</span></p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="container-fluid" style="margin: 5px 0 3rem 0 ; padding: 0 5px;">
                <div class="container  justify-content-lg-start">
                    <div class="row d-flex justify-content-sm-center">
                        <div class="col-lg-7 col-10 rounded-3" style="padding: 10px 15px; background-color: #dcdbdb;">

                            <div class="row">
                                <h2>Shpping</h2>
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <div class="form-group">
                                        <label for="fname">First Name</label>
                                        <input type="text" id="fname" class="form-control" placeholder="Your firstname">
                                    </div>
                                </div>
                                <div class="col-md-6 ">
                                    <div class="form-group">
                                        <label for="lname">Last Name</label>
                                        <input type="text" id="lname" class="form-control" placeholder="Your lastname">
                                    </div>
                                </div>

                                <div class="col-md-12 mb-3">
                                    <div class="form-group">
                                        <label for="email">Email</label>
                                        <input type="text" id="email" class="form-control" placeholder="07xxxxxxxx">
                                    </div>
                                </div>

                                <div class="col-md-12 mb-3">
                                    <div class="form-group">
                                        <label for="mobile">Mobile</label>
                                        <input type="text" id="mobile" class="form-control" placeholder="abc@gmail.com">
                                    </div>
                                </div>

                            </div>

                            <div class="row mb-3 ">


                                <div class="col-md-6 ">
                                    <div class="form-check" style="margin: 0 0.8rem;">
                                        <input class="form-check-input" id="savedAddress" checked onchange="addressChange()"  name="address" type="radio" style=" height: 1.2em;">
                                        <label class="form-check-label"   style=" height: 1.2em; vertical-align: bottom;">
                                            Select Saved Address
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-check" style="margin: 0 0.8rem;">
                                        <input class="form-check-input" id="addNewAddress" onchange="addressChange()" name="address" type="radio" style=" height: 1.2em; ">
                                        <label class="form-check-label"  style=" height: 1.2em; vertical-align: bottom;">
                                            Ship to a new address
                                        </label>
                                    </div>
                                </div>


                            </div>

                            <div class="row  d-flex justify-content-center mt-3" id="selectAddress">
                                <div class="col-lg-10 col-md-11 col-sm-12 border border-1 rounded-3" style="padding: 0; background-color: white;">
                                    <p style="font-size: 16px; font-weight: 700; margin: 0.5rem 0 0 0.5rem; vertical-align: middle;">Saved Addresses</p>
                                    <hr style="margin: 0 0 10px 0;">


                                    <?php

                                    for ($x = 0; $x < $address_num; $x++) {
                                        $address_data = $address_rs->fetch_assoc();

                                    ?>
                                        <div class="row d-flex ps-4" >

                                            <input type="radio" style="height: 20px;" onchange="addShipping()" name="selectAddress"  class="col-1 form-check-input " id="address<?php echo ($x); ?>">

                                            <label for="address<?php echo ($x); ?>" class=" form-check-label col-11 p-0">
                                                <div class="col-12" style="padding: 0 0.5rem; margin: 0 0 10px 0;">
                                                    <div class="col-12 ">
                                                        <p class="mb-0">Address : <?php echo ($address_data["line1"] . "," . $address_data["line2"]) ?></p>
                                                        <span id="selectLine1"><?php echo($address_data["line1"]) ?></span>
                                                        <span id="selectLine2"><?php echo($address_data["line2"]) ?></span>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4 col-sm-12 mt-0 ">
                                                            <span >City: <?php echo ($address_data["cname"]) ?></span>
                                                            <span id="selectAddressCity" class="d-none"><?php echo ($address_data["cid"]) ?></span>
                                                        </div>
                                                        <div class="col-md-4 col-sm-12 mt-0 ">
                                                            <span>District: <?php echo ($address_data["dname"]) ?></span>
                                                            <span id="selectAddressDistrict" class="d-none"> <?php echo ($address_data["did"]) ?></span>
                                                        </div>
                                                        <div class="col-md-4  col-sm-12 mt-0 ">
                                                            <span>Province: <?php echo ($address_data["pname"]) ?></span>
                                                            <span id="selectAddressProvince" class="d-none"> <?php echo ($address_data["prid"]) ?></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </label>
                                        </div>
                                    <?php
                                    }
                                    ?>

                                </div>
                            </div>

                            <div class="row d-none" id="newAddress">
                                <div class="col-md-12 mb-3">
                                    <div class="form-group">
                                        <label for="line1">Line 1</label>
                                        <input type="text" id="line1" class="form-control">
                                    </div>
                                </div>
                                <div class="col-md-12 ">
                                    <div class="form-group">
                                        <label for="line2">Line 2</label>
                                        <input type="text" id="line2" class="form-control">
                                    </div>
                                </div>

                                <div class="col-md-6 mb-3">
                                    <div class="form-group">
                                        <label for="province" class=" form-label">Province</label>
                                        <Select class=" form-select" id="province">
                                            <option value="0">Select Province</option>

                                            <?php
                                            $province_num = $province_rs->num_rows;
                                            for ($x = 0; $x < $province_num; $x++) {
                                                $province_data = $province_rs->fetch_assoc();
                                            ?>
                                                <option value="<?php echo $province_data["id"]; ?>"> <?php echo $province_data["pname"] ?></option>
                                            <?php
                                            }

                                            ?>

                                        </Select>
                                    </div>
                                </div>

                                <div class="col-md-6 mb-3">
                                    <div class="form-group">
                                        <label class=" form-label">District</label>
                                        <Select class=" form-select"  onchange="addShipping()" id="district">
                                            <option value="0">Select District</option>

                                            <?php
                                            $district_num = $district_rs->num_rows;
                                            for ($x = 0; $x < $district_num; $x++) {
                                                $district_data = $district_rs->fetch_assoc();
                                            ?>
                                                <option value="<?php echo $district_data["id"]; ?>"> <?php echo $district_data["dname"] ?></option>
                                            <?php
                                            }

                                            ?>
                                        </Select>
                                    </div>
                                </div>

                                <div class="col-md-6 mb-3">
                                    <div class="form-group">
                                        <label class=" form-label">City</label>
                                        <Select class=" form-select" id="city">
                                            <option value="0">Select City</option>

                                            <?php
                                            $city_num = $city_rs->num_rows;
                                            for ($x = 0; $x < $city_num; $x++) {
                                                $city_data = $city_rs->fetch_assoc();
                                            ?>
                                                <option value="<?php echo $city_data["id"]; ?>"> <?php echo $city_data["cname"] ?></option>
                                            <?php
                                            }

                                            ?>
                                        </Select>
                                    </div>
                                </div>
                                <div class="col-md-6 ">
                                    <div class="form-group">
                                        <label for="pcode">Postal Code</label>
                                        <input type="text" id="pcode" class="form-control" placeholder="Ex:1xxxx">
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class="col-lg-4 col-sm-10 col-md-10  mt-4 mt-lg-0" id="shipping-cart-div">
                            <div class="row">
                                <div class="col-12 rounded-3" id="shipping-cart" style="  background-color: #dcdbdb; ">
                                
                                </div>
                                <div class="col-12 d-flex justify-content-center" >
                                    <a href="#" style="width: 70%;" id="payhere-payment" onclick="checkout();" class="btn btn-success mt-3">Checkout</a>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        <?php include "footer.php";
    } else {
        header("Location:index.php");
    }
        ?>
        
        <script src="script.js"></script>
        <script src="bootstrap.bundle.js"></script>
        <script type="text/javascript" src="https://www.payhere.lk/lib/payhere.js"></script>
</body>

</html>