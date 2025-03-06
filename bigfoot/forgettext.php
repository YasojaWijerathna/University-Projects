<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BigFoot | Invoice</title>
    <link rel="stylesheet" href="bootstrap.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="style.css">

    <link rel="icon" href="resources/logo.png">

    <style>
        .container {
            max-width: 720px;
        }

        .head-logo-div {

            .header-logo {
                height: 90px;
                width: 360px;
                margin-bottom: 10px;
            }

            .side-menu-logo {
                height: 90px;
                width: 360px;
                margin-bottom: 0px;
            }
        }
    </style>
</head>

<body style="padding: 0.5rem 3rem;">


    <div class="container ">
        <div class="col-10 border border-2 rounded-3">
            <div class="row" id="invoice-body">

                <div class="col-12  head-logo-div border-bottom border-3 mb-3 ">
                    <div class="d-flex justify-content-center ">
                        <a href="home.php"> <img src="resources/bigfoot_logo.png" alt="" class="invoice-logo"></a>
                    </div>
                </div>

                <div class="col-12 mb-3">
                    <div class="row">
                        <div class=" col-md-6 ms-4">
                            <h3 style="color: #098023;" class="fw-bold text-decoration-underline">Please use this code for verification</h3>
                        </div>

                    </div>

                    <div class="row mt-5 d-flex justify-content-center">
                        <div class="col-md-5 col-sm-6 col-lg-3  border border-2 rounded-2" style="background-color: #dcdbdb;">
                            <span class="text-center fs-4 fw-bold">Invoice #</span>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

</body>

</html>