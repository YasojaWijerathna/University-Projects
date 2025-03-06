function changeView() {
   var signUpBox = document.getElementById("signupbox");
   var signInBox = document.getElementById("signinbox");
   signUpBox.classList.toggle("d-none");
   signInBox.classList.toggle("d-none");
}

function signUp() {

   var f = document.getElementById("fname");
   var l = document.getElementById("lname");
   var email = document.getElementById("email");
   var password = document.getElementById("password");
   var mobile = document.getElementById("mobile");
   var gender = document.getElementById("gender");

   var form = new FormData();
   form.append("f", f.value);
   form.append("l", l.value);
   form.append("e", email.value);
   form.append("p", password.value);
   form.append("m", mobile.value);
   form.append("g", gender.value);

   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;

         if (t == "success") {
            alert("You have been registered Successfully");
            
          setTimeout(() => {
           
            var signUpBox = document.getElementById("signupbox");
            var signInBox = document.getElementById("signinbox");

            signUpBox.classList.toggle("d-none");
            signInBox.classList.toggle("d-none");
          }, 1200);

         } else {
            alert(t);
         }
      }
   }
   r.open("POST", "signUpProcess.php", true);
   r.send(form);
}

function signIn() {
   var email = document.getElementById("email2");
   var password = document.getElementById("password2");
   var rememberMe = document.getElementById("rememberme")
   var f = new FormData();
   f.append("e", email.value);
   f.append("p", password.value);
   f.append("r", rememberMe.checked);
   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;
         if (t == "success") {
            window.location = "home.php";
         } else {
            alert(t);
         }
      }
   }
   r.open("POST", "signInProcess.php", true);
   r.send(f);
}

var bm;
function forgotPassword() {
   var email = document.getElementById("email2").value;
   var r = new XMLHttpRequest();
   var f = new FormData();
   f.append("e", email);
   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;
         if (t == "Success") {
            alert("Verfication code has been sent to your Email. Please check your inbox ");
            var m = document.getElementById("Modal");
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
   var f = new FormData();
   f.append("e", email.value);
   f.append("n", np.value);
   f.append("r", rnp.value);
   f.append("v", vcode.value);
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
   r.open("POST", "resetPasswordProcess.php", true);
   r.send(f);
}

function signOut() {
   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;

         if (t = "success") {
            window.location = "home.php";

         }
      }
   }
   r.open("GET", "signOutProcess.php", true);
   r.send();
}

function editProfile() {
   var Ads = document.getElementById("Ads");
   var profileEdit = document.getElementById("profileEdit");

   Ads.classList.toggle("d-md-block");
   profileEdit.classList.toggle("d-none");
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
   var fname = document.getElementById("fname");
   var lname = document.getElementById("lname");
   var mobile = document.getElementById("mobile");
   var line1 = document.getElementById("line1");
   var line2 = document.getElementById("line2");
   var province = document.getElementById("province");
   var district = document.getElementById("district");
   var city = document.getElementById("city");
   var pcode = document.getElementById("pcode");
   var image = document.getElementById("profileImage");
   var f = new FormData();
   f.append("fn", fname.value);
   f.append("ln", lname.value);
   f.append("mb", mobile.value);
   f.append("l1", line1.value);
   f.append("l2", line2.value);
   f.append("pr", province.value);
   f.append("ds", district.value);
   f.append("ct", city.value);
   f.append("pc", pcode.value);
   if (image.files.length == 0) {
      var confirmation = confirm("Are you sure, You don't want to update Profile Image");
    
   } else {
      f.append("image", image.files[0]);
   }
   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (this.readyState == 4) {
         var t = r.responseText;
        if(t=="success"){
         alert("Profile Updated Successfully")
         window.location.reload();
        }else{
         alert(t);
        }
      }
   }
   r.open("POST", "updateProfilePocess.php", true);
   r.send(f);
}

var sizeId = 0;
var productID = 0;
function sizeSelect(sid, pid) {
   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (this.readyState == 4) {
         document.getElementById("sizeWrap").innerHTML = r.responseText;
         sizeId = sid;
         productID = pid;
         setAvlQty(pid, sid);
         productPriceChange(pid, sid);
      }
   }

   r.open("GET", "sizeSelect.php?sid=" + sid + "&pid=" + pid, true);
   r.send();
}

function setAvlQty(pid, sid) {
   var r = new XMLHttpRequest();

   r.onreadystatechange = function () {
      if (this.readyState == 4) {
         var t = r.response;
         document.getElementById("qtyInputWrap").innerHTML = t;

      }
   }

   r.open("GET", "setAvlQuantity.php?sid=" + sid + "&pid=" + pid, true);
   r.send();
}

function productPriceChange(pid, sid) {
   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (this.readyState == 4) {
         var t = r.response;
         document.getElementById("amount").innerHTML = t;
      }
   }
   r.open("GET", "productPriceChange.php?sid=" + sid + "&pid=" + pid, true);
   r.send();
}

function QtyPriceChange(uprice) {
   var qty = document.getElementById("qty").value;
   var newAmount = uprice * qty;
   document.getElementById("amount").innerText = "Rs." + newAmount + ".00";
}


function buyNow(id, sid) {
   var qty = document.getElementById("quantity").value;
   alert(sid);
   var r = new XMLHttpRequest();

   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;
         var obj = JSON.parse(t);

         var mail = obj["umail"];
         var amount = obj["amount"];
         var orderId = obj["id"];
         alert(orderId);
         if (t == 1) {
            alert("Please login.");
            window.location = "index.php";
         } else if (t == 2) {
            alert("Please Update your profile");
            window.location = "userProfile.php";
         } else {

            // saveInvoice(orderId,id,mail,amount,qty,sid);
         }
      }
   }

   r.open("GET", "buyNowProcess.php?id=" + id + "&qty=" + qty + "&sid=" + sid, true);
   r.send();
}


function payNow(id) {
   var qty = document.getElementById("amount").value;

   var r = new XMLHttpRequest();

   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;
         var obj = JSON.parse(t);

         var mail = obj["umail"];
         var amount = obj["amount"];

         if (t == 1) {
            alert("Please login.");
            window.location = "index.php";
         } else if (t == 2) {
            alert("Please Update your profile");
            window.location = "userProfile.php";
         } else {
            // Payment completed. It can be a successful failure.
            payhere.onCompleted = function onCompleted(orderId) {
               console.log("Payment completed. OrderID:" + orderId);

               saveInvoice(orderId, id, mail, amount, qty);

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
               "merchant_id": obj["merchant_id"],    // Replace your Merchant ID
               "hash": obj["hash"],
               "return_url": "http://localhost/eshop/singleProductView.php?id=" + id,     // Important
               "cancel_url": "http://localhost/eshop/singleProductView.php?id=" + id,     // Important
               "notify_url": "http://sample.com/notify",
               "order_id": obj["id"],
               "items": obj["item"],
               "amount": amount,
               "currency": "LKR",
               "first_name": obj["fname"],
               "last_name": obj["lname"],
               "email": mail,
               "phone": obj["mobile"],
               "address": obj["address"],
               "city": obj["city"],
               "country": "Sri Lanka",
               "delivery_address": obj["address"],
               "delivery_city": obj["city"],
               "delivery_country": "Sri Lanka",
               "custom_1": "",
               "custom_2": ""
            };

            // Show the payhere.js popup, when "PayHere Pay" is clicked
            // document.getElementById('payhere-payment').onclick = function (e) {
            payhere.startPayment(payment);
            // };
         }
      }
   }

   r.open("GET", "buyNowProcess.php?id=" + id + "&qty=" + qty, true);
   r.send();
}

function addToCart(id) {
   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;
         if (t == "User not found") {
            var m = document.getElementById("SignInPopUp");
            var bm = new bootstrap.Modal(m);
            bm.show();
         } else if (t == "New Product Added to Cart") {
            alert(t);
            setTimeout(() => {
               window.location.reload();
            }, 1200)
         }
      }
   }
   r.open("GET", "addToCartProcess.php?pid=" + id + "&sid=" + sizeId, true);
   r.send();
}

function removeFormCart(id) {
   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;
         if (t == "Product has been Romoved") {
            alert(t);
            setTimeout(() => {
               window.location.reload();
            }, 1200);
         } else {
            alert(t);
         }
      }
   }
   r.open("GET", "removeCartProcess.php?id=" + id, true);
   r.send();
}

function addToWishlist(id) {
   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;
         if (t == "added") {
            document.getElementById("heart" + id).style.className = "text-dark";
            window.location.reload();
         } else if (t == "removed") {
            document.getElementById("heart" + id).style.className = "text-danger";
            window.location.reload();
         } else if (t == "User not found") {
            var m = document.getElementById("SignInPopUp");
            var bm = new bootstrap.Modal(m);
            bm.show();
         } else {
            alert(t);
         }
      }
   }
   r.open("GET", "addToWishlistProcess.php?id=" + id, true);
   r.send();
}

function removeFromwishlist(id) {
   var r = new XMLHttpRequest();

   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;
         if (t == "success") {
            window.location.reload();
         } else {
            alert(t);
         }
      }
   }

   r.open("GET", "removeWishlistProces.php?id=" + id, true);
   r.send();
}

function basicSearch(x) {
   var txt = document.getElementById("basic_search_txt");
   var f = new FormData();
   f.append("t", txt.value);
   f.append("page", x);
   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;
         // alert(t);
         document.getElementById("page").innerHTML = t;
         
      }
   }
   r.open("POST", "basicSearchProcess.php", true);
   r.send(f);
}

function applyCoupon(total, cartNum) {
   var coupon = document.getElementById("couponInput");
   var f = new FormData();
   f.append("coupon", coupon.value);
   f.append("total", total);
   f.append("cart", cartNum);
   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (r.readyState == 4 && r.status == 200) {
         var resText = r.responseText;
         if (resText == "Invalid") {
            document.getElementById("invalidWrap").classList.toggle("d-none");
            setTimeout(() => {
               document.getElementById("invalidWrap").classList.toggle("d-none");
            }, 3000);
            document.getElementById("couponInput").value = "";
         } else {
            document.getElementById("totalWrap").innerHTML = resText;
            document.getElementById("couponInput").value = "";
         }
      }
   }
   r.open("POST", "applyCouponProcess.php", true);
   r.send(f);
}

function loadSalesChart() {
   var element = document.getElementById("salesChart");
   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;
         var array = JSON.parse(t);

         var date_array = [];
         var sale_array = [];

         date_array = array["date"];
         sale_array = array["sale"];

         var chart_labels = [];
         var chart_data = [];
         for (var x = 0; x < date_array.length; x++) {
            chart_labels.push(date_array[x]);
         }
         for (var y = 0; y < sale_array.length; y++) {
            chart_data.push(sale_array[y]);
         }
         var data = {
            labels: chart_labels,
            datasets: [{
               label: 'Sale of last 7 Days',
               data: chart_data,
               backgroundColor: [
                  'rgba(255, 99, 132, 0.2)',
                  'rgba(255, 159, 64, 0.2)',
                  'rgba(255, 205, 86, 0.2)',
                  'rgba(75, 192, 192, 0.2)',
                  'rgba(54, 162, 235, 0.2)',
                  'rgba(153, 102, 255, 0.2)',
                  'rgba(201, 203, 207, 0.2)'
               ],
               borderColor: [
                  'rgb(255, 99, 132)',
                  'rgb(255, 159, 64)',
                  'rgb(255, 205, 86)',
                  'rgb(75, 192, 192)',
                  'rgb(54, 162, 235)',
                  'rgb(153, 102, 255)',
                  'rgb(201, 203, 207)'
               ],
               borderWidth: 1
            }]
         };
         var config = {
            type: 'bar',
            data: data,
            options: {
               responsive: true,
               animation: false,
               maintainAspectRatio: false,
               scales: {
                  y: {
                     beginAtZero: true
                  }
               }
            },
         };
         new Chart(element, config);
      }
   }
   r.open("GET", "loadSalesChart.php", true);
   r.send();
}

function loadBrandChart() {
   var element = document.getElementById("brandChart");

   var r = new XMLHttpRequest();

   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;

         var array = JSON.parse(t);

         var brand_array = [];
         var saleQty_array = [];

         brand_array = array["brand"];
         saleQty_array = array["saleQty"];

         var chart_labels = [];
         var chart_data = [];
         for (var x = 0; x < brand_array.length; x++) {
            chart_labels.push(brand_array[x]);

         }
         for (var y = 0; y < saleQty_array.length; y++) {
            chart_data.push(saleQty_array[y]);
         }

         var data = {
            labels: chart_labels,
            datasets: [{
               label: 'Product Sold By Brand',
               data: chart_data,
               backgroundColor: [
                  'rgb(255, 99, 132)',
                  'rgb(54, 162, 235)',
                  'rgb(255, 205, 86)',
                  'rgb(75, 192, 192)',
                  'rgb(153, 102, 255)'

               ],
               hoverOffset: 4
            }]
         };
         var config = {
            type: 'pie',
            data: data,
            options: {
               responsive: true,
               animation: false,
               maintainAspectRatio: false,
            }
         };

         new Chart(element, config);

      }
   }

   r.open("GET", "loadBrandChart.php", true);
   r.send();
}

function changeStockStatus(pid, sid) {
   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;
         if (t = "success") {
            window.location.reload();
         }
      }
   }
   r.open("GET", "changeStockStatus.php?pid=" + pid + "&sid=" + sid, true);
   r.send();
}

function changeUserStatus(mobile) {
   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;
         if (t = "success") {
            window.location.reload();
         }
      }
   }
   r.open("GET", "changeUserStatus.php?mobile=" + mobile, true);
   r.send();
}

function changeProductImage() {
   var image = document.getElementById("imageuploader");
   image.onchange = function () {
      var file_count = image.files.length;
      if (file_count <= 3) {
         for (var x = 0; x < file_count; x++) {
            var file = this.files[x];
            var url = window.URL.createObjectURL(file);
            document.getElementById("img" + x).src = url;
         }
      } else {
         alert(file_count + " files. You are proceed to upload only 3 or less than 3 files.");
      }
   }
}

function registerProduct() {
   var title = document.getElementById("title");
   var brand = document.getElementById("brand");
   var gender = document.getElementById("gender");
   var color = document.getElementById("color");
   var caption = document.getElementById("caption");
   var desc = document.getElementById("desc");
   var image = document.getElementById("imageuploader");

   var f = new FormData();
   f.append("b", brand.value);
   f.append("t", title.value);
   f.append("g", gender.value);
   f.append("cl", color.value);
   f.append("ca", caption.value);
   f.append("desc", desc.value);

   var file_count = image.files.length;
   for (var x = 0; x < file_count; x++) {
      f.append("image" + x, image.files[x]);
   }
   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;
         if (t == "Product Added Successfully") {
            alert(t);
            setTimeout(() => {
               window.location.reload();
            }, 1500);
         } else {
            alert(t);
         }
      }
   }
   r.open("POST", "registerProductProcess.php", true);
   r.send(f);
}
function registerBrand() {
   var brand = document.getElementById("brandName").value;
   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;
         if (t == "success") {
            alert("Registration Success");
            setTimeout(() => {
               window.location.reload();

            }, 1500);
         } else {
            alert(t);
            setTimeout(() => {
               window.location.reload();

            }, 1500);
         }
      }
   }
   r.open("GET", "registerBrandProcess.php?brand=" + brand, true);
   r.send();
}
function registerColor() {
   var color = document.getElementById("colorM").value;
   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;

         if (t == "success") {
            alert("Registration Success");
            setTimeout(() => {
               window.location.reload();
            }, 1500);
         } else {
            alert(t);
            setTimeout(() => {
               window.location.reload();
            }, 1500);
         }
      }
   }
   r.open("GET", "registerColorProcess.php?color=" + color, true);
   r.send();
}
function registerSize() {
   var size = document.getElementById("size").value;
   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;
         if (t == "success") {
            alert("Registration Success");
            setTimeout(() => {
               window.location.reload();
            }, 1500);
         } else {
            alert(t);
            setTimeout(() => {
               window.location.reload();
            }, 1500);
         }
      }
   }
   r.open("GET", "registerSizeProcess.php?size=" + size, true);
   r.send();
}

var pid;
function getProductID(id) {
   pid = id;
}

function addStock() {
   var size = document.getElementById("size").value;
   var qty = document.getElementById("qty").value;
   var price = document.getElementById("st_price").value;

   var f = new FormData();
   f.append("size", size);
   f.append("qty", qty);
   f.append("price", price);
   f.append("pid", pid);

   var r = new XMLHttpRequest();

   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;
         if (t == "success") {
            alert("Stock Added Successfully");
            setTimeout(() => {
               window.location.reload();

            }, 1500);
         } else {
            alert(t);
            setTimeout(() => {
               window.location.reload();

            }, 1500);
         }
      }
   }

   r.open("POST", "addStockProcess.php", true);
   r.send(f);
}

function registerProduct() {
   var title = document.getElementById("titleU");
   var caption = document.getElementById("caption");
   var desc = document.getElementById("desc");
   var image = document.getElementById("imageuploader");

   var f = new FormData();
   f.append("t", title.value);
   f.append("ca", caption.value);
   f.append("desc", desc.value);
   f.append("pid", pid);

   var file_count = image.files.length;

   for (var x = 0; x < file_count; x++) {
      f.append("image" + x, image.files[x]);
   }

   var r = new XMLHttpRequest();

   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;
         if (t == "Product Updated Successfully") {
            alert(t);
            setTimeout(() => {
               window.location.reload();

            }, 1500);
         } else {
            alert(t);
         }
      }
   }

   r.open("POST", "updateProductProcess.php", true);
   r.send(f);
}


var shippingCart;
function gotoShipping(num) {
   var page = document.getElementById("pageName").innerHTML;
   if (page == "singleProduct" && sizeId == 0) {
      alert("Please select a size");
   } else {
      var discount = document.getElementById("discount").innerHTML;
      var qty_array = document.getElementsByClassName("quantity");
      var stock_no_array = document.getElementsByClassName("stock_no");
      var stock_no = [];
      var qty = [];
      for (var x = 0; x < num; x++) {
         qty.push(qty_array[x].value);
         stock_no.push(stock_no_array[x].value);
      }
      
      var f = new FormData();
      f.append("qty", JSON.stringify(qty));
      f.append("discount", discount);
      f.append("num", num);
      f.append("size_id", sizeId);
      f.append("pid", productID);
      f.append("page", page);
      f.append("stock_no", JSON.stringify(stock_no));
      var r = new XMLHttpRequest();
      r.onreadystatechange = function () {
         if (r.readyState == 4) {
            var t = r.responseText;
         
            shippingCart = t;
            window.location = "Shipping.php";
            window.localStorage.setItem("cart-div", shippingCart);
         }
      }
      r.open("POST", "loadShippingPage.php", true);
      r.send(f);
   }
}

function setVisibleShippingCart() {
   document.getElementById("shipping-cart").innerHTML = localStorage.getItem("cart-div");
   setTimeout(() => {
      localStorage.removeItem("cart-div");
   }, 900000);
}



function addressChange() {
   var selectAddress = document.getElementById("selectAddress");
   var newAddress = document.getElementById("newAddress");
   selectAddress.classList.toggle("d-none");
   newAddress.classList.toggle("d-none");

}
function addShipping() {
   var savedAddress = document.getElementById("savedAddress");
   var addNewAddress = document.getElementById("addNewAddress");
   var districtID;
   if (savedAddress.checked) {
      districtID = document.getElementById("selectAddressDistrict").innerHTML;
   } else if (addNewAddress.checked) {
      districtID = document.getElementById("district").value;
   }
   var subtotal = document.getElementById("subtotal").innerHTML;
   var discount = document.getElementById("discount").innerHTML;
   var f = new FormData();
   f.append("did", districtID);
   f.append("subtotal", subtotal);
   f.append("discount", discount);
   f.append("total", total);

   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;
         document.getElementById("cost-breakdown").innerHTML = t;
      }
   }
   r.open("POST", "addShipping.php", true);
   r.send(f);
}

function checkout() {
   var savedAddress = document.getElementById("savedAddress");
   var addNewAddress = document.getElementById("addNewAddress");

   var fname = document.getElementById("fname").value;
   var lname = document.getElementById("lname").value;
   var email = document.getElementById("email").value;
   var mobile = document.getElementById("mobile").value;
   var shipping = document.getElementById("shipping").innerHTML;
   var discount = document.getElementById("discount").innerHTML;


   var newAddress;
   var line1;
   var line2;
   var cityID;
   var districtID;
   var provinceID;
   if (savedAddress.checked) {
      newAddress = 1;

      line1 = document.getElementById("selectLine1").innerHTML;
      line2 = document.getElementById("selectLine2").innerHTML;
      cityID = document.getElementById("selectAddressCity").innerHTML;
      districtID = document.getElementById("selectAddressDistrict").innerHTML;
      provinceID = document.getElementById("selectAddressProvince").innerHTML;
   } else if (addNewAddress.checked) {
      newAddress = 2;

      line1 = document.getElementById("line1").value;
      line2 = document.getElementById("line2").value;
      cityID = document.getElementById("city").value;
      districtID = document.getElementById("district").value;
      provinceID = document.getElementById("province").value;
      postalcode = document.getElementById("pcode").value;
   } else {
      alert("Please select an address or include a new one");
      return;
   }

   var qty_array = document.getElementsByClassName("qty");
   var stock_no_array = document.getElementsByClassName("stock_no");

   var num = stock_no_array.length;
   var stock_no = [];
   var qty = [];


   for (var x = 0; x < num; x++) {
      qty.push(qty_array[x].value);
      stock_no.push(stock_no_array[x].value);
   }


   var f = new FormData();
   f.append("fname", fname);
   f.append("lname", lname);
   f.append("email", email);
   f.append("mobile", mobile);
   f.append("newAddress", newAddress);
   f.append("line1", line1);
   f.append("line2", line2);
   f.append("city", cityID);
   f.append("district", districtID);
   f.append("province", provinceID);
   f.append("shipping", shipping);
   f.append("discount", discount);
   f.append("num", num);
   f.append("qty", JSON.stringify(qty));
   f.append("stock_no", JSON.stringify(stock_no));

   if (addNewAddress.checked) {
      f.append("pcode", postalcode);
   } else {
      f.append("pcode", null);
   }
   var r = new XMLHttpRequest();

   r.onreadystatechange = function () {
      if (r.readyState == 4 && r.status == 200) {
         var res = r.responseText;


         if (res == "Error") {
            alert(res);
            window.location = "home.php";
         } else if (res == "fname") {
            alert("Please enter your First Name");

         } else if (res == "lname") {
            alert("Please enter your Last Name");

         } else if (res == "email") {
            alert("Please enter your Email ");

         } else if (res == "mobile") {
            alert("Please enter your Mobile ");

         } else if (res == "line1") {
            alert("Please enter line 1 of your address");

         } else if (res == "line2") {
            alert("Please enter line 2 of your address");

         } else if (res == "city") {
            alert("Please select a city");

         } else if (res == "district") {
            alert(" Please select a district");

         } else if (res == "province") {
            alert("Please select a province");

         } else if (res == "pcode") {
            alert("Please enter your Postal Code");

         } else {
            var obj = JSON.parse(res);

            var email = obj["email"];
            var amount = obj["amount"];
            var orderId = obj["oid"];
            // Payment completed. It can be a successful failure.
            payhere.onCompleted = function onCompleted(orderId) {
               console.log("Payment completed. OrderID:" + orderId);

               alert("Payment Successful");

               saveInvoice(orderId, obj["stock_array"], amount, obj["num"], email,
                  obj["qty_array"], obj["discount"], obj["shipping"]);

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
               "merchant_id": obj["merchant_id"],    // Replace your Merchant ID
               "hash": obj["hash"],
               "return_url": "http://localhost/bigfoot/Shipping.php",     // Important
               "cancel_url": "http://localhost/bigfoot/Shipping.php",       // Important
               "notify_url": "http://sample.com/notify",
               "order_id": orderId,
               "items": obj["title"],
               "amount": amount,
               "currency": "LKR",
               "first_name": obj["fname"],
               "last_name": obj["lname"],
               "email": email,
               "phone": obj["mobile"],
               "address": obj["line1"] + "," + obj["line2"],
               "city": obj["city"],
               "country": "Sri Lanka",
               "delivery_address": obj["line1"] + "," + obj["line2"],
               "delivery_city": obj["city"],
               "delivery_country": "Sri Lanka"

            };
            // Show the payhere.js popup, when "PayHere Pay" is clicked
            // document.getElementById('payhere-payment').onclick = function (e) {
            payhere.startPayment(payment);
            // };
         }

      }
   }
   r.open("POST", "checkoutProcess.php", true);
   r.send(f);
}

function saveInvoice(orderId, stock_array, amount, num, email, qty_array, discount, shipping) {
   var f = new FormData();
   f.append("oid", orderId);
   f.append("stock_array", JSON.stringify(stock_array));
   f.append("qty_array", JSON.stringify(qty_array));
   f.append("amount", amount);
   f.append("discount", discount);
   f.append("num", num);
   f.append("email", email);
   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (r.readyState == 4) {
         var t = r.responseText;
         if (t == "added") {
            window.location = "invoice.php?oid=" + orderId + "&qty=" + JSON.stringify(qty_array) + "&discount="
               + discount + "&shipping=" + shipping;

         } else {
            alert(t);
         }
      }
   }
   r.open("POST", "saveInvoice.php", true);
   r.send(f);
}

function loadProductByBrand(brandID, genderID) {
   var r = new XMLHttpRequest();
   r.onreadystatechange = function () {
      if (r.readyState == 4 && r.status == 200) {
         var t = r.responseText;
         document.getElementById("productContent").innerHTML = t;
      }
   }
   r.open("GET", "loadProductByBrand.php?bid=" + brandID + "&gid=" + genderID, true);
   r.send();
}
function printInvoice() {
   var restorepage = document.body.innerHTML;
   var page = document.getElementById("invoice-body").innerHTML;
   document.body.innerHTML = page;
   window.print();
   window.location.reload()
}

function adminLogin() {
   var username = document.getElementById("username");
   var password = document.getElementById("password");

   var f=new FormData();
   f.append("username",username.value);
   f.append("password",password.value);

   var r = new XMLHttpRequest();

   r.onreadystatechange = function () {
      if (r.readyState == 4 && r.status == 200) {

         var t = r.responseText;

         if (t == "success") {
            window.location = "adminDashboard.php";
         } else {
            alert(t);
         }
      }
   }
   r.open("POST", "adminLoginProcess.php", true);
   r.send(f);
}