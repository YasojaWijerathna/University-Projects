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
    <title>BigFoot | Admin User Management</title>
    <link rel="stylesheet" href="bootstrap.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
    <link rel="stylesheet" href="style.css">
    <link rel="icon" href="resources/logo.png">
</head>

<body>
    <div class="container-fluid" style="padding: 0;">
        <?php include "adminSideMenu.php"; ?>


        <div class="col-sm-12 col-md-9 product-Management" style=" padding-top:1rem ;">

            <div class="breadcrumbs">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <p class="bread"><span><a href="adminDashboard.php">Dashboard</a></span> / <span>Product Registration</span></p>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row " style="margin:0 0 1rem 0;  ">

                <div class="col-12 d-flex justify-content-center">

                    <div class="col-lg-6 col-md-10 col-sm-12 mt-2   d-flex justify-content-around registration-heading">
                        <button class="btn btn-warning text-white" data-bs-toggle="modal" data-bs-target="#registerBrandModal">Add Brand</button>
                        <button class="btn btn-warning text-white" data-bs-toggle="modal" data-bs-target="#registerColorModal">Add Color</button>
                        <button class="btn btn-warning text-white" data-bs-toggle="modal" data-bs-target="#registerSizeModal">Add Size</button>
                    </div>
                </div>


            </div>

            <div class="container register-container">
                

                <div class="row border-1 border-top border-bottom border-dark" style="margin-bottom: 1rem; ">
                    <div class="col-md-8 mt-1 mb-1">
                        <h4>Add Product Details</h4>
                    </div>

                    <div class="col-12 mt-3 mb-4">
                        <div class="row d-flex justify-content-center" style="padding: 0 1rem;">
                            <div class=" col-lg-10 col-12">
                                <label class=" form-label"> Title</label>
                                <input type="text" class=" form-control" id="title" placeholder="<Nike> xxxx">
                            </div>

                            <?php
                            $brand_rs = Database::search("SELECT * FROM `brand`");
                            $gender_rs = Database::search("SELECT * FROM `gender`");
                            $color_rs = Database::search("SELECT * FROM `color`");

                            ?>

                            <div class=" col-lg-10 col-12 mt-3">
                                <label class=" form-label" for="brand" > Brand</label>
                                <Select class=" form-select" id="brand">
                                    <option value="0">Select Brand</option>
                                    <?php
                                    $brand_num = $brand_rs->num_rows;
                                    for ($x = 0; $x < $brand_num; $x++) {
                                        $brand_data = $brand_rs->fetch_assoc();
                                    ?>
                                        <option value="<?php echo $brand_data["id"]; ?>">
                                            <?php echo $brand_data["name"] ?></option>
                                    <?php
                                    }

                                    ?>
                                </Select>
                            </div>

                            <div class=" col-lg-5 col-12 mt-3">
                                <label class=" form-label" for="gender" > Gender</label>
                                <Select class=" form-select" id="gender">
                                    <option value="0">Select Gender</option>
                                    <?php
                                    $gender_num = $gender_rs->num_rows;
                                    for ($x = 0; $x < $gender_num; $x++) {
                                        $gender_data = $gender_rs->fetch_assoc();
                                    ?>
                                        <option value="<?php echo $gender_data["id"]; ?>">
                                            <?php echo $gender_data["gender_name"] ?></option>
                                    <?php
                                    }

                                    ?>
                                </Select>
                            </div>

                            <div class=" col-lg-5 col-12 mt-3">
                            <label class=" form-label" for="color" >Color</label>
                                <Select class=" form-select" id="color">
                                    <option value="0">Select Color</option>
                                    <?php
                                    $color_num = $color_rs->num_rows;
                                    for ($x = 0; $x < $color_num; $x++) {
                                        $color_data = $color_rs->fetch_assoc();
                                    ?>
                                        <option value="<?php echo $color_data["id"]; ?>">
                                            <?php echo $color_data["color"] ?></option>
                                    <?php
                                    }

                                    ?>
                                </Select>
                            </div>


                            <div class="col-lg-10 col-12 mt-3">
                                <label class=" form-label">Caption</label>
                                <textarea type="text" class=" form-control overflow-y-scroll" id="caption" placeholder="Insert  Product Caption"></textarea>
                            </div>

                            <div class="col-lg-10 col-12 mt-3">
                                <label class=" form-label">Description</label>
                                <textarea type="text" class=" form-control overflow-y-scroll" id="desc" placeholder="Insert  Product Description"></textarea>
                            </div>

                            <div class=" col-8 justify-content-center   col-md-6 d-flex mt-3">
                                <a  class="col-12 btn btn-primary" onclick="registerProduct();">Register Product</a>
                            </div>

                        </div>
                    </div>

                </div>

                <div class="row border-1  border-bottom border-dark" style="margin-bottom: 1rem; ">
                    <div class="col-md-8 mt-1">
                        <h4>Add Product Images</h4>
                    </div>

                    <div class="col-12 mt-3">
                        <div class="row">
                            <div class=" col-10 offset-3   col-md-6">

                                <div class="row d-md-flex   d-sm-block ">
                                    <div class="col-md-4 col-8 border border-primary rounded">
                                        <img src="resources/upload.png" class="img-fluid justify-content-center d-flex" style="width: 250px;" id="img0" />
                                    </div>
                                    <div class="col-md-4 col-8  border border-primary rounded">
                                        <img src="resources/upload.png" class="img-fluid" style="width: 250px;" id="img1">
                                    </div>
                                    <div class="col-md-4 col-8  border border-primary rounded">
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


            </div>
        </div>

    </div>

    <!-- Register Brand Modal -->
    <div class="modal fade" id="registerBrandModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Register Brand</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-2">
                        <label class="form-label">Brand Name</label>
                        <input type="text" id="brandName" class="form-control">
                    </div>
                </div>
                <div class="modal-footer">
                    <!-- <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button> -->
                    <button type="button" class="btn btn-primary" onclick="registerBrand()">REGISTER</button>
                </div>
            </div>
        </div>
    </div>
    <!-- Register Brand Modal -->

    <!-- Register Color Modal -->
    <div class="modal fade" id="registerColorModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Register Color</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-2">
                        <label class="form-label">Color</label>
                        <input type="text" id="colorM" class="form-control">
                    </div>
                </div>
                <div class="modal-footer">
                    <!-- <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button> -->
                    <button type="button" class="btn btn-primary" onclick="registerColor()">REGISTER</button>
                </div>
            </div>
        </div>
    </div>
    <!-- Register Color Modal -->

     <!-- Register Size Modal -->
     <div class="modal fade" id="registerSizeModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Register Size</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-2">
                        <label class="form-label">Size</label>
                        <input type="text" id="size" class="form-control">
                    </div>
                </div>
                <div class="modal-footer">
                    <!-- <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button> -->
                    <button type="button" class="btn btn-primary" onclick="registerSize()">REGISTER</button>
                </div>
            </div>
        </div>
    </div>
    <!-- Register Size Modal -->

    <script src="script.js"></script>
    <script src="bootstrap.bundle.js"></script>
</body>

</html>

<?php
}else{
    header("Location:adminLogin.php");
}
?>