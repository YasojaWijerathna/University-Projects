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
    <div class="container-fluid vh-100 d-flex justify-content-center" style="padding: 28px;">
        <div class="row align-items-center">


            <div class="col-12 p-3">
                <div class="row d-flex align-items-center justify-content-center">


                    <!-- signinbox -->

                    <div class="col-12 col-lg-8  " id="adminLogin">
                        <div class="row g-2  d-flex justify-content-center">

                            <div class=" col-11 mb-0 mt-2">
                                <p class="adminTitle text-center mb-0">Admin Login</p>
                            </div>

                            <div class="col-11 mb-2 mt-0">
                                <hr class="mt-1 border-dark">
                            </div>

                            <div class=" col-11 mt-4">
                                <label class=" form-label heading">Username</label>
                                <input type="text" class=" form-control" placeholder="ex:- Yasoja@gmail.com" id="username">
                            </div>

                            <div class=" col-11 mt-3">
                                <label class=" form-label heading">Password</label>
                                <input type=" password" class=" form-control" placeholder="ex:- *********" id="password">
                            </div>



                            <div class="col-6 d-flex justify-content-center  d-flex  mt-4">
                                
                                    <button class="btn btn-warning text-white" style="height: 38px; width: 100%;" onclick="adminLogin();">Sign In</button>
                               
                            </div>


                        </div>
                        <div class="row mt-3 ">

                        </div>
                    </div>


                    <!-- signinbox -->





                </div>
            </div>




        </div>
    </div>

    <script src="script.js"></script>
    <script src="bootstrap.js"></script>
</body>

</html>