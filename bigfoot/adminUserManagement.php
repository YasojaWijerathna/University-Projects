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

        <div class="col-sm-12 col-md-9 product-Management">

            <div class="breadcrumbs">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <p class="bread"><span><a href="adminDashboard.php">Dashboard</a></span> / <span>User Management</span></p>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row " style="margin-bottom: 1rem;">

                <div class="col-12">
                    <div class="heading-row d-flex overflow-auto">
                        <div class="col-lg-3 col-sm-5 text-center">
                            <span>Full Name</span>
                        </div>
                        <div class="col-lg-3  col-sm-5 text-center">
                            <span>Email</span>
                        </div>
                        <div class="col-lg col-sm-3 text-center">
                            <span>Mobile</span>
                        </div>
                        <div class="col-lg col-sm-3 text-center">
                            <span>Joined Date</span>
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

                $selected_rs = Database::search("SELECT * FROM `user` ");
                $selected_num = $selected_rs->num_rows;
                $number_of_pages = ceil($selected_num / $results_per_page);

                $page_results = ($pageno - 1) * $results_per_page;

                $user_rs = Database::search("SELECT * FROM `user`   LIMIT " . $results_per_page . " OFFSET " . $page_results . "  ");
                $user_num = $user_rs->num_rows;

                for ($x = 0; $x < $user_num; $x++) {
                    $user_data = $user_rs->fetch_assoc();


                ?>
                    <div class="col-12 overflow-auto">
                        <div class="product-row d-flex border-bottom border-2 " style="border-bottom-left-radius: 0  ; border-bottom-right-radius: 0;">

                            <div class="col-lg-3 col-sm-5 d-flex justify-content-center">
                                <div class="d-flex align-items-center ">
                                    <span class=" fs-6" style=" word-break: break-all;"><?php echo $user_data["fname"] . " " . $user_data["lname"] ?></span>
                                </div>
                            </div>

                            <div class="col-lg-3 col-sm-5 d-flex justify-content-center">
                                <div class="d-flex align-items-center" style=" word-break: break-all;">
                                    <span class="fs-6"><?php echo $user_data["email"] ?></span>
                                </div>
                            </div>

                            <div class="col-lg col-sm-3 d-flex justify-content-center">
                                <div class="d-flex align-items-center">
                                    <span class="price  fs-6"><?php echo $user_data["mobile"] ?></span>
                                </div>
                            </div>

                            <div class="col-lg col-sm-3 d-flex justify-content-center">
                                <div class="d-flex align-items-center">
                                    <span class="price fs-6"><?php echo $user_data["joined_date"] ?></span>
                                </div>
                            </div>

                            <div class="col-lg col-sm-3  d-flex justify-content-center">
                                <div class="d-flex align-items-center">

                                    <?php
                                    if ($user_data["status_id"] == 2) {

                                    ?>
                                        <span class=" btn btn-outline-success fs-5  " href="#" onclick="changeUserStatus(<?php echo $user_data['mobile'] ?>);">Enable</span>
                                    <?php
                                    } else if ($user_data["status_id"] == 1) {

                                    ?>
                                        <span class=" btn btn-outline-danger fs-5" href="#" onclick="changeUserStatus(<?php echo $user_data['mobile']  ?>);">Disable</span>

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