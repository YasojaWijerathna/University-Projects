<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Sign In Portals</title>

    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="bootstrap.css">

    <link rel="icon" href="resources/images/logo.jpg">
</head>

<body class="main_body2 d-flex ">

    <div class="container-fluid vh-100  justify-content-center ">
        <div class="row align-content-center ">

            <div class="col-12">
                <div class="row">

                    <!-- header -->
                    <div class="col-12 p-5">
                        <div class="row">
                            <div class="col-12 p-3 "></div>
                        </div>
                    </div>
                    <!-- header -->

                    <!-- portal box -->
                    <label class="d-none" id="user">student</label>
                    <div class=" col-6 rounded-1 justify-content-center ">
                        <div class="row g-2  ">
                            <h2 style=" color: darkgreen ;  " class="ms-4 text-decoration-underline">Student Portal Sign In</h2>
                            <div class="col-12 ms-5">

                                <?php

                                require "connection.php";

                                $email = "";
                                $password = "";

                                if (isset($_COOKIE["email"])) {
                                    $email = $_COOKIE["email"];
                                }

                                if (isset($_COOKIE["password"])) {
                                    $password = $_COOKIE["password"];
                                }

                                ?>
                                <div class="row">
                                    <div class=" col-10 mt-4">
                                        <label class=" form-label heading">Username/Email</label>
                                        <input type="email" class=" form-control" placeholder="ex:- Yasoja@gmail.com" id="email2" value="<?php echo ($email); ?>">
                                    </div>

                                    <div class=" col-10 mt-3">
                                        <label class=" form-label heading">Password</label>
                                        <input type="password" class="form-control" placeholder="ex:- *********" id="password2" value="<?php echo ($password); ?>">
                                    </div>
                                </div>

                                <div class="row">
                                    <div class=" col-4">
                                        <div class="form-check">
                                            <input class="form-check-input " type="checkbox" id="rememberme">
                                            <label class="form-check-label ">
                                                Remember Me
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-6 text-end">
                                        <a href="#" class=" link-primary" onclick="forgotPassword();">Forgot Password ?</a>
                                    </div>
                                </div>

                                <div class="row mt-2 ">
                                    <div class="col-8 c d-grid mt-5 g-2 ms-5">
                                        <button class="btn btn-primary" style="height: 38px; " onclick="signIn();">Sign In</button>
                                    </div>

                                </div>
                            </div>

                        </div>
                    </div>

                    <!-- bakcgroundImg-->



                    <div class="col-lg-6 d-lg-block d-none ">
                        <div class="row">

                            

                        </div>
                    </div>
                    <!-- bakcgroundImg -->

                    <!-- modal 1 -->

                    <div class="modal" tabindex="-1" id="verificationModal">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Student Verification</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">

                                    <div class="row  d-none mb-3" id="msgDiv">
                                        <div class="col-12">
                                            <div class="row bg-info  " id="div2">
                                                <label id="msg"></label>
                                            </div>

                                        </div>
                                    </div>

                                    <div class="row ">
                                        <label class="form-label">Enter Your Verification Code</label>
                                        <input type="text" class="col-12 " id="vcode">
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" onclick="resendOTP();" >Resend Verification code</button>
                                    <button type="button" class="btn btn-primary" onclick="OTPverification();">Verify</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- modal 1 -->

                    <!-- modal 2 -->

                    <div class="modal" tabindex="-1" id="Modal2">
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
                                                <button class="btn btn-outline-secondary" type="button" id="npb" onclick="showPassword();">Show</button>
                                            </div>
                                        </div>

                                        <div class="col-6">
                                            <label class="form-label">Re-type Password</label>
                                            <div class="input-group mb-3">
                                                <input type="password" class="form-control" id="rnp" />
                                                <button class="btn btn-outline-secondary" type="button" id="rnpb" onclick="showPassword2();">Show</button>
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

                    <!-- modal 2-->

                    <!-- modal 3 -->

                    <?php
                    
                    $dateRs=Database::search("SELECT * FROM `st_portal`");
                    $expireDate=$dateRs->fetch_assoc();

                    ?>

                    <div class="modal" tabindex="-1" id="paymentModel">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Trial Period</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">

                                    <div class="row g-3">

                                        <div class="col-12">
                                            <p class="" id="paymentMsg" style="color: #f3865e;">Your account is under 30 days free trial period.Your portal will expire on 
                                            <?php echo($expireDate["expire_date"]);?> </p>
                                        </div>

                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-outline-primary" id="payhere-payment" onclick="PayNow();" >Pay Now</button>
                                    <button type="button" class="btn btn-outline-dark" onclick="continueToPortal();">Continue</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- modal 3 -->

                </div>
            </div>



        </div>
    </div>

    <script src="script.js"></script>
    <script src="bootstrap.bundle.js"></script>
    <script type="text/javascript" src="https://www.payhere.lk/lib/payhere.js"></script>
</body>

</html>