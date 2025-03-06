function adminVerify() {

    var email = document.getElementById("email").value;
    var passwored = document.getElementById("password").value;

    var r = new XMLHttpRequest();

    var f = new FormData();
    f.append("email", email);
    f.append("password", passwored);

    r.onreadystatechange = function () {

        if (r.readyState == 4) {
            var t = r.responseText;

            if (t == "success") {
                window.location = "adminPanel.php";
            } else {
                alert(t);
            }

        }
    }

    r.open("POST", "adminSignInProcess.php", true);
    r.send(f);

}

function showPasswordEye() {
    var np = document.getElementById("np");
    var npb = document.getElementById("npb");

    var icon = document.getElementById("picon");

    $('#picon').toggleClass('bi-eye-slash-fill bi-eye-fill');


    if (np.type == "password") {
        np.type = "text";
        npb.innerHTML = "Hide";
    } else {
        np.type = "password";
        npb.innerHTML = "Show";
    }
}

function changeUI() {
    var row = document.getElementById("teacherRow");
    var occupation = document.getElementById("occupation").value;

    if (occupation == 1) {
        row.classList.remove("d-none");
    } else {
        row.classList.add("d-none");
    }
}

function registration() {

    var fname = document.getElementById("fname").value;
    var lname = document.getElementById("lname").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("np").value;
    var line1 = document.getElementById("line1").value;
    var line2 = document.getElementById("line2").value;
    var city = document.getElementById("city").value;
    var gender = document.getElementById("gender").value;
    var occupation = document.getElementById("occupation").value;
    var grade = document.getElementById("grade").value;

    var f = new FormData();
    f.append("fname", fname);
    f.append("lname", lname);
    f.append("email", email);
    f.append("password", password);
    f.append("line1", line1);
    f.append("line2", line2);
    f.append("city", city);
    f.append("gender", gender);
    f.append("occupation", occupation);
    f.append("grade", grade);

    if (occupation == 1) {
        var subject = document.getElementById("subject").value;
        f.append("subject", subject);
    }

    var r = new XMLHttpRequest();

    r.onreadystatechange = function () {
        if (r.readyState == 4) {

            var t = r.responseText;
            alert(t);
        }
    }

    r.open("POST", "T_Ac_RegisterProcess.php", true);
    r.send(f);
}

function searchTeacher() {
    var Tno = document.getElementById("tno").value;
    var name = document.getElementById("tname").value;



    var r = new XMLHttpRequest();

    r.onreadystatechange = function () {

        if (r.readyState == 4) {
            var t = r.responseText
            document.getElementById("viewArea").innerHTML = t;

        }
    }

    r.open("GET", "teacherSearchProcess.php?tno=" + Tno + "&name=" + name, true);
    r.send();
}

function searchAcademic() {
    var ACno = document.getElementById("acno").value;
    var name = document.getElementById("acname").value;



    var r = new XMLHttpRequest();

    r.onreadystatechange = function () {

        if (r.readyState == 4) {
            var t = r.responseText
            document.getElementById("viewArea").innerHTML = t;

        }
    }

    r.open("GET", "academicSearchProcess.php?acno=" + ACno + "&name=" + name, true);
    r.send();
}

function logOut() {
    var user = document.getElementById("user").innerHTML;
    window.location = user + "SignIn.php";
}

var pm;
function signIn() {
    var email = document.getElementById("email2");
    var password = document.getElementById("password2");
    var rememberMe = document.getElementById("rememberme");
    var user = document.getElementById("user").innerHTML;

    var f = new FormData();
    f.append("e", email.value);
    f.append("p", password.value);
    f.append("u", user);
    f.append("r", rememberMe.checked);


    var r = new XMLHttpRequest();

    r.onreadystatechange = function () {
        if (r.readyState == 4) {
            var t = r.responseText;

            if (t == "unverified") {
                var modal = document.getElementById("verificationModal");
                vm = new bootstrap.Modal(modal);
                vm.show();
            }
            else if (t == "success") {
                window.location = user + "Portal.php";

            } else if (t == "Not payed") {

                var modal = document.getElementById("paymentModel");
                pm = new bootstrap.Modal(modal);
                pm.show();

            } else {
                alert(t);

            }
        }
    }

    r.open("POST", "signInProcess.php", true);
    r.send(f);
}

var pm;
function OTPverification() {
    var email = document.getElementById("email2").value;
    var password = document.getElementById("password2").value;
    var vcode = document.getElementById("vcode").value;
    var user = document.getElementById("user").innerHTML;
    var rememberMe = document.getElementById("rememberme");

    var f = new FormData();
    f.append("e", email);
    f.append("p", password);
    f.append("vcode", vcode);
    f.append("u", user)
    f.append("r", rememberMe.checked);

    var r = new XMLHttpRequest();

    r.onreadystatechange = function () {
        if (r.readyState == 4) {
            var t = r.responseText;

            if (t == "success") {
                window.location = user + "potal.php";
            } else if (t == "Not payed") {
                vm.hide();

                var modal = document.getElementById("paymentModel");
                pm = new bootstrap.Modal(modal);
                pm.show();

            } else {
                document.getElementById("msgDiv").classList.add("d-none");
                document.getElementById("msg").innerHTML = t;

            }

        }
    }

    r.open("POST", "OTPVerificationProcess.php", true)
    r.send(f);

}


function resendOTP() {
    document.getElementById("msgDiv").classList.remove("d-none");

    var email = document.getElementById("email2").value;
    var user = document.getElementById("user").innerHTML;

    var f = new FormData();
    f.append("e", email);
    f.append("u", user)

    var r = new XMLHttpRequest();

    r.onreadystatechange = function () {
        if (r.readyState == 4) {
            var t = r.responseText;

            if (t == "New OTP sent") {
                document.getElementById("msgDiv").classList.add("d-none");
                document.getElementById("msg").innerHTML = t;
            } else {
                alert(t);
            }

        }

    }


    r.open("POST", "resendOtpProcess.php", true)
    r.send(f);

}

function continueToPortal() {
    var email = document.getElementById("email2").value;

    var r = new XMLHttpRequest();

    r.onreadystatechange = function () {
        if (r.readyState == 4) {
            var t = r.responseText;

            if (t == "fine") {
                window.location = "studentPortal.php";
            } else if (t == "expired") {
                document.getElementById("paymentMsg").innerHTML = "Your trial period for the portal has expired. Please make necessary payments to reactivate your Account";
            }


        }

    }


    r.open("GET", "paymentValidation.php?email=" + email, true)
    r.send();


}

function fileNameChange1() {
    var fileInput = document.getElementById('addNote');
    var filename = fileInput.files[0].name;

    var label = document.getElementById("fileName1");
    label.classList.remove("d-none");
    label.innerHTML = filename
}


function fileNameChange2() {
    var fileInput = document.getElementById('addAsgn');
    var filename = fileInput.files[0].name;

    var label = document.getElementById("fileName2");
    label.classList.remove("d-none");
    label.innerHTML = filename
}

function uploadAssignments() {
    var fileInput = document.getElementById('addAsgn');
    var Tno = document.getElementById("Tno").innerHTML;

    if (fileInput.files.length == 0) {
        alert("Please select a file to upload");
    } else {

        var f = new FormData();
        f.append("file", fileInput.files[0]);
        f.append("tno", Tno)

        var r = new XMLHttpRequest();

        r.onreadystatechange = function () {
            if (r.readyState == 4) {
                var t = r.responseText;
                alert(t);
                location.reload();

            }
        }

        r.open("POST", "addAssignmentProcess.php", true)
        r.send(f);

    }
}

function uploadNotes() {
    var fileInput = document.getElementById('addNote');
    var Tno = document.getElementById("Tno").innerHTML;

    if (fileInput.files.length == 0) {
        alert("Please select a file to upload");
    } else {

        var f = new FormData();
        f.append("file", fileInput.files[0]);
        f.append("tno", Tno)

        var r = new XMLHttpRequest();

        r.onreadystatechange = function () {
            if (r.readyState == 4) {
                var t = r.responseText;
                alert(t);
                location.reload();

            }
        }

        r.open("POST", "addNotesProcess.php", true)
        r.send(f);

    }
}

function submitMarks(x) {
    var Tno = document.getElementById("tno").innerHTML;
    var Sno = document.getElementById("sno" + x).innerHTML;
    var ASno = document.getElementById("asno" + x).innerHTML;
    var marks = document.getElementById("marks" + x).value;


    if (marks == "") {
        alert("Please enter marks for the assignment")
    } else {

        var f = new FormData();
        f.append("sno", Sno);
        f.append("tno", Tno);
        f.append("asno", ASno);
        f.append("marks", marks);

        var r = new XMLHttpRequest();

        r.onreadystatechange = function () {
            if (r.readyState == 4) {
                location.reload();
                alert(t);

            }
        }

        r.open("POST", "submitMarksToAcademic.php", true)
        r.send(f);

    }

}

function searchStudentAsssignemnt() {
    var ASno = document.getElementById("Asno").value;
    var Sno = document.getElementById("Sno").value;
    var name = document.getElementById("Sname").value;
    var tno = document.getElementById("tno").innerHTML;

    var execute = null;

    if (ASno != "") {
        execute = true;
    } else if (Sno != "") {
        execute = true;
    } else if (name != "") {
        execute = true
    } else {
        execute = false;
        alert("Please enter value to search")
    }

    if (execute) {

        var r = new XMLHttpRequest();

        r.onreadystatechange = function () {

            if (r.readyState == 4) {
                var t = r.responseText
                document.getElementById("viewArea").innerHTML = t;


            }
        }

        r.open("GET", "studentAsgnSearch.php?asno=" + ASno + "&name=" + name + "&sno=" + Sno + "&tno=" + tno, true);
        r.send();
    }
}

var bm;
function forgotPassword() {

    var email = document.getElementById("email2").value;
    var user = document.getElementById("user").innerHTML;

    var r = new XMLHttpRequest();

    var f = new FormData();
    f.append("e", email);
    f.append('u', user);

    r.onreadystatechange = function () {
        if (r.readyState == 4) {

            var t = r.responseText;

            if (t == "New OTP sent") {
                alert("Verfication code has been sent to your Email. Please check your inbox ");
                var m = document.getElementById("Modal2");
                bm = new bootstrap.Modal(m);
                bm.show();
            } else {
                alert(t);
            }
        }
    }

    r.open("POST", "forgotPassword.php", true);
    r.send(f);

}

function showPassword() {
    var np = document.getElementById("np");
    var npb = document.getElementById("npb");


    if (np.type == "password") {
        np.type = "text";
        npb.innerHTML = "Hide";
    } else {
        np.type = "password";
        npb.innerHTML = "Show";
    }
}

function showPassword2() {
    var rnp = document.getElementById("rnp");
    var rnpb = document.getElementById("rnpb");


    if (rnp.type == "password") {
        rnp.type = "text";
        rnpb.innerHTML = "Hide";
    } else {
        rnp.type = "password";
        rnpb.innerHTML = "Show";
    }
}

function resetPassword() {
    var email = document.getElementById("email2");
    var np = document.getElementById("np");
    var rnp = document.getElementById("rnp");
    var vcode = document.getElementById("vc");
    var user = document.getElementById("user").innerHTML;


    var f = new FormData();
    f.append("e", email.value);
    f.append("n", np.value);
    f.append("r", rnp.value);
    f.append("v", vcode.value);
    f.append('u', user);


    var r = new XMLHttpRequest();

    r.onreadystatechange = function () {

        if (r.readyState == 4) {

            var t = r.responseText;

            if (t == "Success") {
                alert("Your Password Updated");
                bm.hide();
            } else {
                alert(t);
            }

        }
    }

    r.open("POST", "resetPassword.php", true);
    r.send(f);
}
function studentRegistration() {

    var fname = document.getElementById("fname").value;
    var lname = document.getElementById("lname").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("np").value;
    var line1 = document.getElementById("line1").value;
    var line2 = document.getElementById("line2").value;
    var city = document.getElementById("city").value;
    var gender = document.getElementById("gender").value;
    var dob = document.getElementById("dob").value;
    var mobile = document.getElementById("mobile").value;
    var grade = document.getElementById("grade").value;



    var f = new FormData();
    f.append("fname", fname);
    f.append("lname", lname);
    f.append("email", email);
    f.append("password", password);
    f.append("line1", line1);
    f.append("line2", line2);
    f.append("city", city);
    f.append("gender", gender);
    f.append("grade", grade);
    f.append("dob", dob);
    f.append("mobile", mobile);

    var r = new XMLHttpRequest();

    r.onreadystatechange = function () {
        if (r.readyState == 4) {

            var t = r.responseText;
            alert(t);
        }
    }

    r.open("POST", "studentRegisterProcess.php", true);
    r.send(f);
}

function releaseMarks(y, x) {
    var ACno = document.getElementById("ano").innerHTML;
    var Tno = document.getElementById("teacher" + y).innerHTML;
    var ASno = document.getElementById("asgn" + y + x).innerHTML;
    var Sno = document.getElementById("sno" + y + x).innerHTML;


    var r = new XMLHttpRequest();

    r.onreadystatechange = function () {
        if (r.readyState == 4) {
            var t = r.responseText;
            location.reload();
            // alert(t);

        }
    }

    r.open("GET", "releaseMark.php?Ano=" + ACno + "&Tno=" + Tno + "&Sno=" + Sno + "&ASno=" + ASno, true);
    r.send();

}

function searchMarksAsgn() {
    var ACno = document.getElementById("ano").innerHTML;
    var Tno = document.getElementById("Tno").value;
    var name = document.getElementById("Tname").value;

    var r = new XMLHttpRequest();

    r.onreadystatechange = function () {
        if (r.readyState == 4) {
            var t = r.responseText;
            document.getElementById("viewArea").innerHTML = t;
        }
    }

    r.open("GET", "marksAsgnSearch.php?Ano=" + ACno + "&Tno=" + Tno + "&name=" + name, true);
    r.send();
}

function uploadAnswerSheet() {
    var fileInput = document.getElementById("uploadAnswer");
    var Sno = document.getElementById("sno").innerHTML;
    var Tno = document.getElementById("tno").innerHTML;
    var ASno = document.getElementById("asno").innerHTML;

    if (fileInput.files.length == 0) {
        alert("Please select a file to upload");
    } else {

        var f = new FormData();
        f.append("file", fileInput.files[0]);
        f.append("tno", Tno);
        f.append("sno", Sno);
        f.append("asno", ASno);

        var r = new XMLHttpRequest();

        r.onreadystatechange = function () {
            if (r.readyState == 4) {
                var t = r.responseText;
                alert(t);
                location.reload();

            }
        }

        r.open("POST", "addAnswerSheetProcess.php", true)
        r.send(f);

    }
}

function searchAsssignemntMarks() {
    var tname = document.getElementById("Tname").value;
    var ASno = document.getElementById("Asno").value;
    var Sno = document.getElementById("Sno").innerHTML;

    if (tname == "" && ASno == "") {
        alert("Please type Assignment No or Teacher Name")
    } else {

        var r = new XMLHttpRequest();

        r.onreadystatechange = function () {
            if (r.readyState == 4) {
                var t = r.responseText;
                document.getElementById("viewArea").innerHTML = t;

            }
        }

        r.open("GET", "studentMarksSearch.php?Asno=" + ASno + "&name=" + tname + "&sno=" + Sno, true)
        r.send();

    }
}

function updateStudentPortal(Sno, payment_id) {

    var r = new XMLHttpRequest();

    r.onreadystatechange = function () {
        if (r.readyState == 4) {
            var t = r.responseText;
            if (t == "success") {
                window.location = "studentPortal.php";
            } else {
                alert(t);
            }
        }
    }

    r.open("GET", "portalUpdate.php?payment=" + payment_id + "&sno=" + Sno, true)
    r.send();

}


function PayNow() {

    var r = new XMLHttpRequest();

    r.onreadystatechange = function () {
        if (r.readyState == 4) {
            var t = r.responseText;
            if (t == "no") {
                alert("Please Sign In first")
            } else {
                var paymentObj = JSON.parse(t);
                pm.hide();


                // Payment completed. It can be a successful failure.
                payhere.onCompleted = function onCompleted(orderId) {
                    console.log("Payment completed. OrderID:" + orderId);

                    updateStudentPortal(paymentObj["Sno"], paymentObj["order_id"]);

                    // Note: validate the payment and show success or failure page to the customer
                };

                // Payment window closed
                payhere.onDismissed = function onDismissed() {
                    // Note: Prompt user to pay again or show an error page
                    console.log("Payment dismissed");
                };

                // Error occurred
                payhere.onError = function onError(error) {
                    // Note: show an error page
                    console.log("Error:" + error);
                };

                // Put the payment variables here
                var payment = {
                    "sandbox": true,
                    "merchant_id": paymentObj["merchant_id"],    // Replace your Merchant ID
                    "return_url": "http://localhost/LMS/studentSignIn.php",     // Important
                    "cancel_url": "http://localhost/LMS/studentSignIn.php",     // Important
                    "notify_url": "http://sample.com/notify",
                    "order_id": paymentObj["order_id"],
                    "items": paymentObj["fname"] + " Portal Payment",
                    "amount": paymentObj["amount"],
                    "currency": "LKR",
                    "hash": paymentObj["hash"], // *Replace with generated hash retrieved from backend
                    "first_name": paymentObj["fname"],
                    "last_name": paymentObj["lname"],
                    "email": paymentObj["email"],
                    "phone": paymentObj["mobile"],
                    "address": paymentObj["address"],
                    "city": paymentObj["city"],
                    "country": "Sri Lanka",
                    "delivery_address": "",
                    "delivery_city": "",
                    "delivery_country": "",
                    "custom_1": "",
                    "custom_2": ""

                };

                // Show the payhere.js popup, when "PayHere Pay" is clicked
                document.getElementById('payhere-payment').onclick = function (e) {
                    payhere.startPayment(payment);
                };
            }


        }
    }
    r.open("GET", "portalPaymentProcess.php", true);
    r.send();

}

function checkResultsSearch() {
    var Sno = document.getElementById("sno").value;
    var Sname = document.getElementById("sname").value;
    var Tno = document.getElementById("tno").value;
    var Subject = document.getElementById("subject").value;

    // alert(Subject);
    // alert(Sno);
    // alert(Tno);
    // alert(Subject);
    var r = new XMLHttpRequest();

    r.onreadystatechange = function () {
        if (r.readyState == 4) {
            var t = r.responseText;

            if (t == "Please type a Student No or Student Name or Teacher No or select a Subject ") {
                alert(t)
            } else {
                document.getElementById("viewArea").innerHTML = t;
                // alert(t);
            }
        }
    }

    r.open("GET", "checkResultsSearchProcess.php?Sname=" + Sname + "&Sno=" + Sno + "&Tno=" + Tno + "&Subject=" + Subject, true)
    r.send();

}


function loadStudentDetail(x) {
    var Sno = document.getElementById("sno" + x).innerHTML;
    window.location = "studentDetailsUpdate.php?Sno=" + Sno;

}

function updateStudentDetails() {
    var Sno = document.getElementById("sno").value;
    var oldEmail = document.getElementById("oldEmail").innerHTML;
    var oldPassword = document.getElementById("oldPassword").innerHTML;
    var newPassword = document.getElementById("np").value;
    var newEmail = document.getElementById("email").value;
    var oldGrade = document.getElementById("oldGrade").innerHTML;
    var newGrade = document.getElementById("grade").value;

    var fname = document.getElementById("fname").value;
    var lname = document.getElementById("lname").value;
    var mobile = document.getElementById("mobile").value;
    var line1 = document.getElementById("line1").value;
    var line2 = document.getElementById("line2").value;
    var city = document.getElementById("city").value;



    var f = new FormData();
    f.append("Sno", Sno);
    f.append("oldEmail", oldEmail);
    f.append("oldPassword", oldPassword);
    f.append("newEmail", newEmail);
    f.append("newPassword", newPassword);
    f.append("oldGrade", oldGrade);
    f.append("newGrade", newGrade);
    f.append("fname", fname);
    f.append("lname", lname);
    f.append("mobile", mobile);
    f.append("line1", line1);
    f.append("line2", line2);
    f.append("city", city);

    var r = new XMLHttpRequest();

    r.onreadystatechange = function () {
        if (r.readyState == 4) {
            var t = r.responseText;

            alert(t);

        }
    }

    r.open("POST", "updateStudentDeatilsProcess.php", true)
    r.send(f);
}

function updateGradeChange(Sno, payment_id) {

    var r = new XMLHttpRequest();

    r.onreadystatechange = function () {
        if (r.readyState == 4) {
            var t = r.responseText;
            if (t == "success") {
                window.location = "studentPortal.php";
            } else {
                alert(t);
            }
        }
    }

    r.open("GET", "gradeChangeUpdate.php?payment=" + payment_id + "&sno=" + Sno, true)
    r.send();

}

function PayNow2() {

    var r = new XMLHttpRequest();

    r.onreadystatechange = function () {
        if (r.readyState == 4) {
            var t = r.responseText;
            if (t == "no") {
                alert("Please Sign In first")
            } else {

                var paymentObj = JSON.parse(t);




                // Payment completed. It can be a successful failure.
                payhere.onCompleted = function onCompleted(orderId) {
                    console.log("Payment completed. OrderID:" + orderId);

                    updateGradeChange(paymentObj["Sno"], paymentObj["order_id"]);

                    // Note: validate the payment and show success or failure page to the customer
                };

                // Payment window closed
                payhere.onDismissed = function onDismissed() {
                    // Note: Prompt user to pay again or show an error page
                    console.log("Payment dismissed");
                };

                // Error occurred
                payhere.onError = function onError(error) {
                    // Note: show an error page
                    console.log("Error:" + error);
                };

                // Put the payment variables here
                var payment = {
                    "sandbox": true,
                    "merchant_id": paymentObj["merchant_id"],    // Replace your Merchant ID
                    "return_url": "http://localhost/LMS/studentSignIn.php",     // Important
                    "cancel_url": "http://localhost/LMS/studentSignIn.php",     // Important
                    "notify_url": "http://sample.com/notify",
                    "order_id": paymentObj["order_id"],
                    "items": paymentObj["fname"] + " Grade Upgrade Payment",
                    "amount": paymentObj["amount"],
                    "currency": "LKR",
                    "hash": paymentObj["hash"], // *Replace with generated hash retrieved from backend
                    "first_name": paymentObj["fname"],
                    "last_name": paymentObj["lname"],
                    "email": paymentObj["email"],
                    "phone": paymentObj["mobile"],
                    "address": paymentObj["address"],
                    "city": paymentObj["city"],
                    "country": "Sri Lanka",

                };

                // Show the payhere.js popup, when "PayHere Pay" is clicked
                document.getElementById('payhere-payment').onclick = function (e) {
                    payhere.startPayment(payment);
                };
            }


        }
    }
    r.open("GET", "gradeChangePayment.php", true);
    r.send();

}

function editProfile() {
    var user = document.getElementById("user").innerHTML;

    window.location = "updateProfile.php?user=" + user;

}

function changeImage() {
    var view = document.getElementById("viewImg");
    var file = document.getElementById("profileImage");

    file.onchange = function () {
        var file1 = this.files[0];
        var url = window.URL.createObjectURL(file1);
        view.src = url;
    }

}

function updateProfile() {

    var user = document.getElementById("user").innerHTML;

    var fname = document.getElementById("fname");
    var lname = document.getElementById("lname");
    var email = document.getElementById("email");
    var password = document.getElementById("np");

    var line1 = document.getElementById("line1");
    var line2 = document.getElementById("line2");
    var city = document.getElementById("city");
    var image = document.getElementById("profileImage");

    var f = new FormData();
    f.append("u",user);
    f.append("fn", fname.value);
    f.append("ln", lname.value);
    f.append("e",email.value);
    f.append("p",password.value);
    f.append("l1", line1.value);
    f.append("l2", line2.value);
    f.append("c", city.value);


    if (user == "student") {
        var mobile = document.getElementById("mobile");
        var dob = document.getElementById("dob");
        var Sno = document.getElementById("userNo").innerHTML;

        f.append("m",mobile.value);
        f.append("dob",dob.value);
        f.append("Sno",Sno);

    } else if (user == "teacher") {
        var Tno = document.getElementById("userNo").innerHTML;
        f.append("Tno",Tno);

    } else if (user == "academic") {
        var Ano = document.getElementById("userNo").innerHTML;
        f.append("Ano",Ano);
    }


    if (image.files.length == 0) {
        var confirmation = confirm("Are you sure, You don't want to update Profile Image");

        if (confirmation) {
            alert("You have not selected any image");
        }
    } else {
        f.append("image", image.files[0]);
    }

    var r = new XMLHttpRequest();

    r.onreadystatechange = function () {
        if (this.readyState == 4) {
            var t = r.responseText;
            alert(t);
        }
    }

    r.open("POST", "updateProfileProcess.php", true);
    r.send(f);

}

function editProfile2() {
   
    window.location = "updateProfile2.php";

}

function updateProfile2() {



    var fname = document.getElementById("fname");
    var lname = document.getElementById("lname");
    var email = document.getElementById("email");
    var oldEmail=document.getElementById("oldEmail");
    var password = document.getElementById("np");
    var line1 = document.getElementById("line1");
    var line2 = document.getElementById("line2");
    var city = document.getElementById("city");
    var image = document.getElementById("profileImage");

    var f = new FormData();
  
    f.append("fn", fname.value);
    f.append("ln", lname.value);
    f.append("e",email.value);
    f.append("oe",oldEmail.innerHTML);
    f.append("p",password.value);
    f.append("l1", line1.value);
    f.append("l2", line2.value);
    f.append("c", city.value);


    if (image.files.length == 0) {
        var confirmation = confirm("Are you sure, You don't want to update Profile Image");

        if (confirmation) {
            alert("You have not selected any image");
        }
    } else {
        f.append("image", image.files[0]);
    }

    var r = new XMLHttpRequest();

    r.onreadystatechange = function () {
        if (this.readyState == 4) {
            var t = r.responseText;
            alert(t);
        }
    }

    r.open("POST", "updateProfileProcess2.php", true);
    r.send(f);

}