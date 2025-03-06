<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Sign In </title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="bootstrap.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

    <link rel="icon" href="resources/images/logo.jpg">
</head>

<body style="background-color:#A25EB4 ;">


    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">

                        <div class=" mt-md-4 pb-3">

                            <h2 class="fw-bold mb-2 text-uppercase">Admin Log In</h2>

                            <div class="mt-3">

                                <div class="form-outline form-white mb-4">
                                    <input type="email" id="email" class="form-control form-control-lg" />
                                    <label class="form-label" for="email">Email</label>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <input type="password" id="password" class="form-control form-control-lg" />
                                    <label class="form-label" for="password">Password</label>
                                </div>


                                <button class="btn btn-outline-info btn-lg px-5" type="submit" onclick="adminVerify();">Login</button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>

<script src="script.js"></script>
</body>

</html>