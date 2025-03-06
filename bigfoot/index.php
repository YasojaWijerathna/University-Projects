<?php
require "connection.php";
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>BigFoot</title>

    <link rel="stylesheet" href="bootstrap.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="style.css">

    <link rel="icon" href="resources/logo.png">
</head>

<body class="main-body">
    <div class="container-fluid vh-100 d-flex justify-content-center ">
        <div class="row align-content-center">


            <div class="col-12 ">
                <div class="row d-flex justify-content-center align-items-center p-3 pt-2 ">

                    <!-- signupbox -->

                    <div class="col-12 col-lg-6 border border-3  rounded-2 border-success" style="padding: 0 20px;" id="signupbox">
                        <div class="row g-2  p-2">

                            <div class=" col-12 text-center">
                                <p class="title2">Create New Account</p>
                            </div>

                            <div class="col-12">
                                <hr>
                            </div>
                            <div class=" col-12">
                                <label class=" form-label heading ">First Name</label>
                                <input type="text" class=" form-control" placeholder="ex:- Yasoja" id="fname">
                            </div>

                            <div class=" col-12">
                                <label class=" form-label heading">Last Name</label>
                                <input type="text" class=" form-control" placeholder="ex:- Wijerathna" id="lname">
                            </div>

                            <div class=" col-12">
                                <label class=" form-label heading">Email</label>
                                <input type="email" class=" form-control" placeholder="ex:- Yasoja@gmail.com" id="email">
                            </div>

                            <div class=" col-12">
                                <label class=" form-label heading">Password</label>
                                <input type=" password" class=" form-control" placeholder="ex:- *********" id="password">
                            </div>
                            <div class=" col-6">
                                <label class=" form-label heading">Mobile</label>
                                <input type="text" class=" form-control" placeholder="ex:- 0705702732" id="mobile">
                            </div>

                            <div class=" col-6">
                                <label class=" form-label heading">Gender</label>
                                <select class="  form-control" id="gender" style="min-height: 40px;">
                                <option value="0">Select Gender</option>

                                    <?php
                                    $rs = Database::search("SELECT * FROM `gender` ");
                                    $n = $rs->num_rows;
                                    for ($x = 0; $x < $n; $x++) {
                                        $d = $rs->fetch_assoc();
                                    ?>
                                        <option value="<?php echo $d["id"];  ?>"><?php echo $d["gender_name"] ?></option>

                                    <?php
                                    }
                                    ?>
                                </select>
                            </div>

                            <div class=" col-12    mt-4 mb-3   ">
                                <div class="row d-flex justify-content-center">
                                    <div class=" col-6 ">
                                        <button class="btn btn-primary rounded-4 text-white" style="height: 45px; width: 100%; " onclick="signUp();">Sign Up</button>
                                    </div>

                                    <div class=" col-6 mt-0 ">
                                        <button style="height: 45px; background-color:  #535454; width: 100%; " class="rounded-4 text-white" onclick="changeView();">Go to Sign In</button>
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>


                    <!-- signupbox -->

                    <!-- signinbox -->

                    <div class="col-10 col-lg-6 border border-3 d-none rounded-2 border-success " id="signinbox">
                        <div class="row g-2  d-flex justify-content-center">

                            <div class=" col-10 mb-1">
                                <p class="title2 text-center">Sign In to Account</p>
                            </div>

                            <div class="col-10 mb-2">
                                <hr>
                            </div>
                            <?php
                            $email = "";
                            $password = "";

                            if (isset($_COOKIE["email"])) {
                                $email = $_COOKIE["email"];
                            }

                            if (isset($_COOKIE["password"])) {
                                $password = $_COOKIE["password"];
                            }

                            ?>
                            <div class=" col-10 mt-4">
                                <label class=" form-label heading">Email</label>
                                <input type="email" class=" form-control" placeholder="ex:- Yasoja@gmail.com" id="email2" value="<?php echo ($email); ?>">
                            </div>

                            <div class=" col-10 mt-3">
                                <label class=" form-label heading">Password</label>
                                <input type=" password" class=" form-control" placeholder="ex:- *********" id="password2" value="<?php echo ($password); ?>">
                            </div>

                            <div class=" col-5 d-flex align-content-bottom ">
                                <div class="form-check">
                                    <input class="form-check-input " type="radio" style="height: 12px;" id="rememberme">
                                    <label class="form-check-label ">
                                        Remember Me
                                    </label>
                                </div>
                            </div>
                            <div class="col-5 text-end">
                                <a href="#" class=" text-primary" onclick="forgotPassword();">Forgot Password ?</a>
                            </div>

                            <div class="col-lg-5 col-8 mt-3  d-grid mt-4">
                                <div class="row">
                                    <button class="btn btn-primary" style="height: 38px; " onclick="signIn();">Sign In</button>
                                </div>

                                <div class="row mt-2">
                                    <button class="btn btn-danger" style="height: 38px; " onclick="changeView();">New to eShop? Join Now</button>
                                </div>
                            </div>


                        </div>
                    </div>


                    <!-- signinbox -->




                    <div class="col-lg-5 d-lg-block d-none" style="margin: 10px; padding: 15px;">
                        <div class="row">

                            <div class="col-12">
                                <div class="row">
                                    <span class="text-center title1 mt-0 ">Welcome to BigFoot</span><br>
                                </div>
                            </div>

                            <div class="col-12 mt-3">
                                <div class="row">
                                    <div class="col-12 logo"> </div>
                                </div>
                            </div>

                        </div>
                    </div>



                </div>
            </div>

            <!-- modal -->

            <div class="modal" tabindex="-1" id="Modal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Forgot Password</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">

                            <div class="row g-3">

                                <div class="col-6">
                                    <label class="form-label">New Password</label>
                                    <div class="input-group mb-3">
                                        <input type="password" class="form-control" id="np" />
                                        <button class="btn btn-outline-secondary ms-1" type="button" id="npb" onclick="showPassword();">Show</button>
                                    </div>
                                </div>

                                <div class="col-6">
                                    <label class="form-label">Re-type Password</label>
                                    <div class="input-group mb-3">
                                        <input type="password" class="form-control" id="rnp" />
                                        <button class="btn btn-outline-secondary ms-1" type="button" id="rnpb" onclick="showPassword2();">Show</button>
                                    </div>
                                </div>

                                <div class="col-12">
                                    <label class="form-label">Verification Code</label>
                                    <input type="text" class="form-control" id="vc" />
                                </div>

                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" onclick="resetPassword();">Reset</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- modal -->


        </div>
    </div>

    <script src="script.js"></script>
    <script src="bootstrap.js"></script>
</body>

</html>