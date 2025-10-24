<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">

        <script src="https://www.google.com/recaptcha/api.js" async defer></script>

        <style>
        body {
            background: #f8fdfc;
        }
        .signup-card {
            max-width: 800px;
            border-radius: 1rem;
            box-shadow: 0 6px 20px rgba(0,0,0,0.1);
        }
        .signup-card img {
            max-width: 100%;
            border-radius: 1rem 0 0 1rem;
        }
        .form-floating > label {
            color: #6c757d;
        }
        .btn-primary {
            background-color: #198754;
            border: none;
        }
        .btn-primary:hover {
            background-color: #157347;
        }
    </style>
    </head>

    <body>       
        <!-- All Form Control Toasts - start  -->
        <div class="toast-container position-fixed top-0 start-50 translate-middle-x p-3">
            <div id="name_toast" class="toast align-items-center text-bg-danger border-0">
                <div class="d-flex">
                    <div class="toast-body">Invalid User Name...</div>
                    <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>
                </div>
            </div>
            <div id="email_toast" class="toast align-items-center text-bg-danger border-0">
                <div class="d-flex">
                    <div class="toast-body">Invalid Email...</div>
                    <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>
                </div>
            </div>
            <div id="password_toast" class="toast align-items-center text-bg-danger border-0">
                <div class="d-flex">
                    <div class="toast-body">Invalid Password...</div>
                    <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>
                </div>
            </div>
            <div id="phone_toast" class="toast align-items-center text-bg-danger border-0">
                <div class="d-flex">
                    <div class="toast-body">Invalid Phone...</div>
                    <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>
                </div>
            </div>
            <div id="country_toast" class="toast align-items-center text-bg-danger border-0">
                <div class="d-flex">
                    <div class="toast-body">Select Country...</div>
                    <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>
                </div>
            </div>
            <div id="email_exists_toast" class="toast align-items-center text-bg-danger border-0">
                <div class="d-flex">
                    <div class="toast-body">Email Already Exists...</div>
                    <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>
                </div>
            </div>
        </div> 
        <!-- All Form Control Toasts - end  -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    <body>       
        <!-- All Form Control Toasts - start  -->
        <div class="toast-container position-fixed top-0 start-50 translate-middle-x p-3">
            <div id="name_toast" class="toast align-items-center text-bg-danger border-0">
                <div class="d-flex">
                    <div class="toast-body">Invalid User Name...</div>
                    <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>
                </div>
            </div>
            <div id="email_toast" class="toast align-items-center text-bg-danger border-0">
                <div class="d-flex">
                    <div class="toast-body">Invalid Email...</div>
                    <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>
                </div>
            </div>
            <div id="password_toast" class="toast align-items-center text-bg-danger border-0">
                <div class="d-flex">
                    <div class="toast-body">Invalid Password...</div>
                    <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>
                </div>
            </div>
            <div id="phone_toast" class="toast align-items-center text-bg-danger border-0">
                <div class="d-flex">
                    <div class="toast-body">Invalid Phone...</div>
                    <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>
                </div>
            </div>
            <div id="country_toast" class="toast align-items-center text-bg-danger border-0">
                <div class="d-flex">
                    <div class="toast-body">Select Country...</div>
                    <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>
                </div>
            </div>
            <div id="email_exists_toast" class="toast align-items-center text-bg-danger border-0">
                <div class="d-flex">
                    <div class="toast-body">Email Already Exists...</div>
                    <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>
                </div>
            </div>
        </div> 
        <!-- All Form Control Toasts - end  -->

        <!-- ############# -------Header Start ------------ ########### -->
        <c:import url="header.jsp" />
        <!-- ############# -------Header End ------------ ########### -->

        <!-- ############# -------Main Body Start ------------ ########### -->
        <div class="container">
            <!--server-side form validation report -->
            <!-- <input type="hidden" id="signup_failed" value="${param.error_message}"> -->
            <c:if test="${not empty param.error_message}">
                <div class="row">
                    <div class="col">
                        <div class="alert alert-danger" role="alert">
                            ${param.error_message}... (Please Enable JavaScript)
                        </div>
                    </div>
                </div>
                
            </c:if>

            <div class="row justify-content-center">
                <div class="col-5 py-3 px-4 border border-secondary shadow rounded w-75">
                    <h3 class="text-primary">User SignUp</h3>
                    <hr class="mb-4">
                    <div class="d-flex justify-content-around" >
                    <div >
                        <img src="static/media/images/doctor.png" alt="" class="w-75">
                    </div>
                    <div class="w-50">
                    <form action="signup.do" method="post" id="signup_form">
                        <div class="form-floating mb-3">
                            <input type="text" name="name" id="name" required placeholder="Enter Valid Name"
                                class="form-control">
                            <label for="name">Enter Full Name</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="email" name="email" id="email" required placeholder="Enter Valid Email"
                                class="form-control">
                            <label for="email">Enter Your Email</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="password" name="password" id="password" required placeholder="Enter Valid Password"
                                class="form-control">
                            <label for="password">Enter Password</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="tel" name="phone" id="phone" required placeholder="Enter Valid Phone"
                                class="form-control">
                            <label for="phone">Enter Your Phone</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="date" name="dob" id="dob" required placeholder="Enter DoB"
                                class="form-control">
                            <label for="dob">Date Of Birth</label>
                        </div>
                        <div class="form-floating mb-3">
                            <select name="gender" id="gender" class="form-select" placeholder="Select Gender">
                                <option disabled value="0">Select</option>
                                <option value="1">Male</option>
                                <option value="2">Female</option>
                                <option value="3">Other</option>
                            </select>
                            <label for="userType_id">Gender</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" name="address" id="address" required placeholder="Enter Your Address"
                                class="form-control">
                            <label for="phone">Address</label>
                        </div>
                         <!-- <div class="form-floating mb-3">
                            <input type="text" name="city" id="city" required placeholder="Enter Your City"
                                class="form-control">
                            <label for="phone">Enter Your City</label>
                        </div>  -->
                        <div class="form-floating mb-3">
                            <select disabled name="country_id" id="country_id" class="form-select" placeholder="Select Country">
                                <option value="1">India</option>
                            </select>
                            <label for="country_id">Your Country</label>
                        </div>

                        <div class="form-floating mb-3">    
                            <select name="userType_id" id="userType_id" class="form-select" placeholder="Select User Type">
                                <option disabled value="0">Select</option>
                                <option value="1">Patient</option>
                                <option value="2">Doctor</option>
                                <option value="3">Manufacturer</option>
                            </select>
                            <label for="userType_id">User Type</label>
                        </div>
                                                
                        <div class="d-flex justify-content-center mb-3">
                            <div class="g-recaptcha" data-sitekey="6LdYlqQrAAAAAHJEOnrrTx8k18y8og_Wbm1bV2hp"></div>
                        </div>

                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-primary d-flex align-items-center">SignUp</button>
                        </div>

                        <div class="d-flex justify-content-center">
                            <a href="login.jsp" class="loginRedirect text-decoration-none">I already have an account! Login</a>
                        </div>
                    </form>
                    </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ############# -------Main Body End ------------ ########### -->

        <!-- ############# -------Footer Start ------------ ########### -->

        <!-- ############# -------Footer End ------------ ########### -->

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>

        <!-- JS ---- -->
        <!-- <script>
            const signup_form = document.querySelector('#signup_form');
            const elements = document.querySelectorAll('.form-control');
            const country_id = document.querySelector('#country_id');
            let toasts = Array.from(document.querySelectorAll('.toast'));

            // ------ onblur field vaidation - start --------------------------
            toasts = toasts.map((next)=>{
                return bootstrap.Toast.getOrCreateInstance(next);
            });

            const patterns = [
                new RegExp(/^[A-Za-z ]{4,10}$/),
                new RegExp(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/),
                new RegExp(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/),
                new RegExp(/^[6-9][0-9]{9}$/)
            ];

            const sendCheckEmailExists = async () => {
                let response = await fetch('check_email_exists.do?email='+elements[1].value);
                let result = await response.text();

                return result;
            };

            const checkEmailExists = () => {
                sendCheckEmailExists().then((data)=>{
                    if(data == 'true') {
                        toasts[5].show();
                        elements[1].classList.add('is-invalid');
                    } else {

                    }
                }).catch((err)=>{
                    console.log(err);
                });
            };

            elements.forEach((next, index) => {
                next.index = index;
                next.addEventListener('blur', (event) => {
                    let elm = event.target;
                    if (!patterns[elm.index].test(elm.value)) {
                        elm.classList.add('is-invalid');
                        toasts[elm.index].show();
                    } else {
                        elm.classList.remove('is-invalid');
                        if(elm.id == 'email')
                            checkEmailExists();
                    }
                });

                next.addEventListener('focus', (event) => {
                    event.target.classList.remove('is-invalid');
                });
            });

            country_id.addEventListener('blur', () => {
                if(country_id.value == 0) {
                    country_id.classList.add('is-invalid');
                    toasts[4].show();
                } else 
                    country_id.classList.remove('is-invalid');
            });
            // ------ onblur field vaidation - end --------------------------

            // ---------------- form submit validation - start -----------------
            signup_form.addEventListener('submit', (event) => {
                event.preventDefault();

                let flag = true;

                elements.forEach((elm) => {
                    if (!patterns[elm.index].test(elm.value)) {
                        elm.classList.add('is-invalid');
                        toasts[elm.index].show();
                        flag = false;
                    } 
                });

                if(country_id.value == 0) {
                    country_id.classList.add('is-invalid');
                    toasts[4].show();
                    flag = false;
                }

                if(flag)
                    signup_form.submit();
            });
            // ---------------- form submit validation - end -----------------

            // ----------------- server-side form validation report - start --------------
            let signup_failed = document.querySelector('#signup_failed');

            // ----------------- server-side form validation report - end --------------
            // let signupFailed = signup_failed.value.trim();
            // if(signupFailed != '') {
            //     for(i=0;i<signupFailed.length;i++) 
            //         toasts[signupFailed[i]].show();
            // }
        </script> -->
    </body>

    </html>